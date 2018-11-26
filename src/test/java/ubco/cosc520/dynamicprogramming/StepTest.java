package ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class StepTest {

  @Test
  public void testInitialization() {
    Step step = new Step();
    assertThat(step.getValue(), is(0.0));
    assertThat(step.getPath().size(), is(0));
  }

  @Test
  public void testSetValue() {
    Step step = new Step();
    step.setValue(10.1);
    assertThat(step.getValue(), is(10.1));
  }

  @Test
  public void testAddToPath() {
    Step step = new Step();
    step.addToPath(1);
    assertThat(step.getPath().size(), is(1));
    assertThat(step.getPath().get(0), is(1));

    step.addToPath(2);
    assertThat(step.getPath().size(), is(2));
    assertThat(step.getPath().get(1), is(2));
  }

  @Test
  public void testSetPathAfterInitialization() {
    Step step = new Step();
    List<Integer> path = new ArrayList<>();
    path.add(1);
    path.add(2);

    step.setPath(path);
    assertThat(step.getPath(), is(path));
  }

  @Test
  public void testSetPathAfterPathIsPopulated() {
    Step step = new Step();
    List<Integer> path = new ArrayList<>();
    path.add(1);
    path.add(2);

    step.addToPath(10);
    step.setPath(path);

    assertThat(step.getPath(), is(path));
  }
}