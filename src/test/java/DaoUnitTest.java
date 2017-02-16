import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.sguydye.sfservice.dao.impl.EntityDaoImpl;
import org.sguydye.sfservice.model.LogicalConstraint;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.model.LogicalField;
import org.sguydye.sfservice.util.ConstraintType;
import org.sguydye.sfservice.util.FieldType;
import org.sguydye.sfservice.util.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Matchers.*;

@Ignore
@RunWith(MockitoJUnitRunner.class)
public class DaoUnitTest {

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    private Session session;

    @InjectMocks
    private EntityDaoImpl entityDao = new EntityDaoImpl();

    private LogicalEntity entity;
    @Before
    public void init(){
        Mockito.when(sessionFactory.getCurrentSession()).thenReturn(session);
        Mockito.when(session.save(any(LogicalEntity.class))).thenReturn(1);
        entity = new LogicalEntity("testEntity");
        Set<LogicalField> fieldSet = new HashSet<>();
        fieldSet.add(new LogicalField("testField", FieldType.BOOLEAN));
        List<LogicalConstraint> constraints = new ArrayList<>();
        constraints.add(new LogicalConstraint(ConstraintType.UNIQUE));
        entity.setFields(fieldSet);
        entity.setConstraints(constraints);
    }

    @Test
    public void testSave(){
        Integer entityId = entityDao.save(entity);
        assertThat(entityId, is(not(0)));
        entityDao.save(new LogicalEntity("anotherOne"));
    }

    @Test
    public void testUpdate(){
        entity.setName("updated");
        entityDao.update(entity);
        entityDao.update(new LogicalEntity("anotherOne"));
    }

    @Test
    public void testDelete() {
        entityDao.delete(entity);
    }

    @Test
    public void testFind(){
        LogicalEntity entity =  entityDao.find(1).orElseThrow(EntityNotFoundException::new);
    }

}
