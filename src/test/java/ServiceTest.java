import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.service.EntityServiceImpl;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTest {

    @Mock
    EntityDao entityDao;

    @InjectMocks
    private EntityServiceImpl service = new EntityServiceImpl();

    @Before
    public void init(){
        Mockito.when(entityDao.find(1)).thenReturn(new LogicalEntity("resultEntity"));
        List<LogicalEntity> mockList = new ArrayList<>();
        mockList.add(new LogicalEntity("testEntity1"));
        mockList.add(new LogicalEntity("testEntity2"));
        Mockito.when(entityDao.getAll()).thenReturn(mockList);
        Mockito.when(entityDao.save(any(LogicalEntity.class))).thenReturn(2);
    }

    @Test
    public void testGet(){
        assertThat(service.getEntity(1).getName(), is("resultEntity") );
    }

    @Test
    public void testGetAll(){
        assertThat(service.getAllEntities().size(), is(2));
    }

    @Test
    public void testPost(){
        Integer resultId = service.postEntity(new LogicalEntity("postedEntity"));
        assertThat(resultId, is(2));
    }

    @Test
    public void testDelete() {
        service.deleteEntity(2);
    }

    @Test
    public void testUpdate(){
        Response response = service.updateEntity(1, new LogicalEntity("updateEntity"));
        assertThat(response.getStatus(), is(200));
    }


}
