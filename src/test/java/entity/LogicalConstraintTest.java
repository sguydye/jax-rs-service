package entity;

import org.junit.Before;
import org.junit.Test;
import org.sguydye.sfservice.model.LogicalConstraint;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.model.LogicalField;
import org.sguydye.sfservice.util.ConstraintType;
import org.sguydye.sfservice.util.FieldType;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogicalConstraintTest {

    private LogicalConstraint constraint;

    @Before
    public void initialize(){
        constraint = new LogicalConstraint(ConstraintType.UNIQUE);
        constraint.setEntity(new LogicalEntity("testEntity"));
        Set<LogicalField> fieldSet = new HashSet<>();
        fieldSet.add(new LogicalField("field1", FieldType.BOOLEAN));
        fieldSet.add(new LogicalField("field2", FieldType.DATETIME));
        constraint.setFields(fieldSet);
    }

    @Test
    public void testGetters(){
        assertThat(constraint, hasProperty("id", is(nullValue())));
        assertThat(constraint, hasProperty("type", is(ConstraintType.UNIQUE)));
        assertThat(constraint.getEntity(), hasProperty("name", is("testEntity")));
        assertThat(constraint.getFields().size(), is(2));
    }

    @Test
    public void testSetters(){
        constraint.setType(ConstraintType.NOTNULL);
        assertThat(constraint, hasProperty("type", is(ConstraintType.NOTNULL)));
        LogicalEntity entity = new LogicalEntity();
        constraint.setEntity(entity);
        assertThat(constraint.getEntity(), is(entity));
        Set<LogicalField> fields = new HashSet<>();
        constraint.setFields(fields);
        assertThat(constraint.getFields(), is(fields));
    }
}
