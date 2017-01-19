import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.sguydye.sfservice.service.EntityService;
import org.sguydye.sfservice.service.EntityServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class JAXRSTest{

    private final static String ENDPOINT_ADDRESS = "local://entity";
    private static Server server;

    @BeforeClass
    public static void initialize() throws Exception {
        startServer();
    }

    private static void startServer() throws Exception {
        JAXRSServerFactoryBean sf = new JAXRSServerFactoryBean();
        sf.setResourceClasses(EntityServiceImpl.class);

        List<Object> providers = new ArrayList<Object>();
        // add custom providers if any
        sf.setProviders(providers);

        sf.setResourceProvider(EntityService.class,
        new SingletonResourceProvider(new EntityServiceImpl() {
        }, true));
        sf.setAddress(ENDPOINT_ADDRESS);

        server = sf.create();
    }

    @AfterClass
    public static void destroy() throws Exception {
        server.stop();
        server.destroy();
    }
/*
    @Test
    public void testGetBookViaPipe() {
        WebClient client = WebClient.create(ENDPOINT_ADDRESS);
        client.accept("application/json");
        client.path("somepath");
        Book book = client.get(Book.class);
        assertEquals(123L, book.getId());
    }*/
}
