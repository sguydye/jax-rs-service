import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.service.EntityServiceImpl;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

@RunWith(MockitoJUnitRunner.class)
public class JAXRSTest {

    private final static String ENDPOINT_ADDRESS = "local://testservice";
    private Server server;
    private List<Object> providers;

    @Mock
    private EntityDao entityDao;

    @InjectMocks
    private EntityServiceImpl entityService;

    @Before
    public void initialize() throws Exception {
        providers = new ArrayList<>();
        Mockito.when(entityDao.find(1)).thenReturn(new LogicalEntity("testEntity"));
        List<LogicalEntity> mockList = new ArrayList<>();
        mockList.add(new LogicalEntity("Entity1"));
        mockList.add(new LogicalEntity("Entity2"));
        Mockito.when(entityDao.getAll()).thenReturn(mockList);
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
    public void testGet() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept("application/json");
        client.path("/entity/1");
        assertThat(client.get().getStatus(), is(200));
        LogicalEntity response =  client.get(LogicalEntity.class);
        assertThat(response.getName(), is("testEntity"));
    }

    @Test
    public void testGetAll() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept(MediaType.APPLICATION_JSON);
        client.path("/entity");
        assertThat(client.get().getStatus(), is(200));
        List<LogicalEntity> response = (List<LogicalEntity>) client.getCollection(LogicalEntity.class);
        assertThat(response.size(), is(2));
    }

    @Test
    public void testPost() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/entity");
        Response response =  client.post(new LogicalEntity("postEntity"));
        assertThat(response.getStatus(), is(204));
    }

    @Test
    public void testPut() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path("/entity/1");
        Response response = client.put(new LogicalEntity("changedEntity"));
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void testDelete() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS, providers);
        client.accept(MediaType.APPLICATION_JSON);
        client.path("/entity/1");
        Response response = client.delete();
        assertThat(response.getStatus(), is(204));
    }


}
