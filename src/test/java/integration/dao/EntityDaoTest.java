package integration.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sguydye.sfservice.dao.EntityDao;
import org.sguydye.sfservice.model.LogicalConstraint;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.model.LogicalField;
import org.sguydye.sfservice.util.ConstraintType;
import org.sguydye.sfservice.util.FieldType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-test.xml")
public class EntityDaoTest {

    @Autowired
    private EntityDao entityDao;

    @Rollback
    @Before
    public void initialize() {
        LogicalEntity testEntity = new LogicalEntity("testEntity");
        Set<LogicalField> fieldSet = new HashSet<>();
        fieldSet.add(new LogicalField("testField", FieldType.DATETIME));
        List<LogicalConstraint> constraintList = new ArrayList<>();
        constraintList.add(new LogicalConstraint(ConstraintType.NOTNULL));
        testEntity.setDbName("testDb");
        testEntity.setFields(fieldSet);
        testEntity.setConstraints(constraintList);
        entityDao.save(testEntity);
        entityDao.save(new LogicalEntity("testEntity2"));
    }

    @Test
    public void testFind() {
        LogicalEntity entity = entityDao.find(1);
        assertThat(entity, is(not(nullValue())));

        entity = entityDao.findByName("testEntity").get(0);
        assertThat(entity.getName(), is("testEntity"));
    }

    @Test
    public void testDelete() {
        List<LogicalEntity> entityList = entityDao.findByName("testEntity");
        Integer id = entityList.get(0).getId();
        entityList.forEach(logicalEntity -> {
            entityDao.delete(logicalEntity);
        });
        assertThat(entityDao.find(id), is(nullValue()));
        assertThat(entityDao.findByName("testEntity"), is(emptyCollectionOf(LogicalEntity.class)));
    }

    @Test
    public void testGetAll() {
        List<LogicalEntity> entityList = entityDao.getAll();
        assertThat(entityList.size(), is(greaterThan(2)));
    }

    @Test
    public void testUpdate() {
        LogicalEntity entity = entityDao.findByName("testEntity2").get(0);
        Integer id = entity.getId();
        String newName = "newName";
        entity.setName(newName);
        entityDao.update(entity);
        assertThat(entityDao.find(id).getName(), is(newName));

        entity = entityDao.findByName("testEntity").get(0);
        id = entity.getId();
        Integer constraintSize = entity.getConstraints().size();
        entity.getConstraints().add(new LogicalConstraint(ConstraintType.NOTNULL));
        entityDao.update(entity);
        assertThat(entityDao.find(id).getConstraints().size(), is(constraintSize + 1));
    }
}
