package entity;

import org.junit.Before;
import org.junit.Test;
import org.sguydye.sfservice.model.LogicalEntity;
import org.sguydye.sfservice.model.LogicalField;
import org.sguydye.sfservice.util.FieldType;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class LogicalFieldTest {

    private LogicalField field;

    @Before
    public void initialize() {
        field = new LogicalField("testField", FieldType.VARCHAR);
        field.setMantissa(new Byte("5"));
        field.setLength(new Byte("5"));
        field.setEntity(new LogicalEntity("testEntity"));
    }

    @Test
    public void testGetters(){
        assertThat(field, hasProperty("id", is(nullValue())));
        assertThat(field, hasProperty("name", is("testField")));
        assertThat(field, hasProperty("type", is(FieldType.VARCHAR)));
        assertThat(field, hasProperty("entity" ));
        assertThat(field, hasProperty("length", is(Byte.valueOf("5"))));
        assertThat(field, hasProperty("mantissa", is(Byte.valueOf("5"))));
    }

    @Test
    public void testSetters(){
        field.setName("Name");
        assertThat(field.getName(), is("Name"));
        field.setType(FieldType.BOOLEAN);
        assertThat(field.getType(), is(FieldType.BOOLEAN));
        field.setLength(new Byte("3"));
        assertThat(field.getLength(),is(Byte.valueOf("3")));
        field.setMantissa(new Byte("3"));
        assertThat(field.getMantissa(), is(Byte.valueOf("3")));
        LogicalEntity testEntity = new LogicalEntity();
        field.setEntity(testEntity);
        assertThat(field.getEntity(), is(testEntity));
        assertThat(field.toString(), containsString(field.getName()));
    }
}
