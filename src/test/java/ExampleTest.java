import org.junit.Test;
import org.sguydye.sfservice.model.ExampleModel;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExampleTest {
    @Test
    public void testProperty() {
        ExampleModel model = new ExampleModel("String", 12);
        assertThat(model, hasProperty("string"));
        assertThat(model, hasProperty("anInt", is(12)));
    }

}
