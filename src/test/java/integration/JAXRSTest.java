package integration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.*;
import org.junit.runner.RunWith;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.*;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;

@Ignore
@Transactional
@Rollback
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class JAXRSTest {

    private final static String ENDPOINT_ADDRESS = "local://testservice";
    private Server server;
    private List<Object> providers;

    @Autowired
    private EntityService entityService;

    @Before
    public void initialize() throws Exception {
        providers = new ArrayList<>();
        startServer();
    }

    private void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setServiceBean(entityService);
        JacksonJsonProvider jsonProvider = new JacksonJsonProvider();
        providers.add(jsonProvider);
        sf.setProviders(providers);
        sf.setAddress(ENDPOINT_ADDRESS);
        server = sf.create();
    }

    @After
    public void destroy() throws Exception {
        server.stop();
        server.destroy();
    }

    @Test
    public void fullTest() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);

        client.path("/entity");
        Response postResponse = client.post(new LogicalEntity("testEntity"));
        assertThat(postResponse.getStatus(), is(200));
        Integer id = postResponse.readEntity(Integer.class);

        assertThat(client.get().getStatus(), is(200));

        client.replacePath("/entity/" + id);
        assertThat(client.get().getStatus(), is(200));
        LogicalEntity response = client.get(LogicalEntity.class);
        assertThat(response.getName(), is("testEntity"));

        Response putResponse = client.put(new LogicalEntity("changedEntity"));
        assertThat(putResponse.getStatus(), is(200));
        assertThat(client.get(LogicalEntity.class).getName(), is("changedEntity"));

        Response deleteResponse = client.delete();
        assertThat(deleteResponse.getStatus(), is(204));
        assertThat(client.get().getStatus(), is(204));
        fail();
    }

}
