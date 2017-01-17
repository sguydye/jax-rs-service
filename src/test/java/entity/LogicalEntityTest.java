package entity;

import org.junit.Before;
import org.junit.Test;
import org.sguydye.sfservice.model.LogicalConstraint;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.model.LogicalField;
import org.sguydye.sfservice.util.ConstraintType;
import org.sguydye.sfservice.util.FieldType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogicalEntityTest {
    private LogicalEntity entity;

    @Before
    public void initialize(){
        entity = new LogicalEntity("testEntity");
        entity.setDbName("testDb");
        Set<LogicalField> fieldSet = new HashSet<>();
        fieldSet.add(new LogicalField("field1", FieldType.BOOLEAN));
        fieldSet.add(new LogicalField("field2", FieldType.VARCHAR));
        List<LogicalConstraint> constraints = new ArrayList<>();
        constraints.add(new LogicalConstraint(ConstraintType.NOTNULL));
        constraints.add(new LogicalConstraint(ConstraintType.UNIQUE));
        entity.setFields(fieldSet);
        entity.setConstraints(constraints);
    }

    @Test
    public void testGetters(){
        assertThat(entity, hasProperty("name", is("testEntity")));
        assertThat(entity, hasProperty("dbName", is("testDb") ));
        assertThat(entity, hasProperty("id", is(nullValue())));
        assertThat(entity, hasProperty("fields"));
        assertThat(entity.getFields().size(), is(2));
        assertThat(entity, hasProperty("constraints"));
        assertThat(entity.getConstraints().size(), is(2));
    }

    @Test
    public void testSetters(){
        entity.setName("Hello");
        assertThat(entity.getName(), is("Hello"));
        entity.setDbName("DB");
        assertThat(entity.getDbName(), is("DB"));
        entity.setId(1);
        assertThat(entity.getId(), is(1));
        Set<LogicalField> fields = new HashSet<>();
        entity.setFields(fields);
        assertThat(entity.getFields(), is(fields));
        List<LogicalConstraint> constraintList = new ArrayList<>();
        entity.setConstraints(constraintList);
        assertThat(entity.getConstraints(), is(constraintList));
    }
}
