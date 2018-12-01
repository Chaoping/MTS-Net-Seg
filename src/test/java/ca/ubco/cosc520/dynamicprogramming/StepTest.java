package ca.ubco.cosc520.dynamicprogramming;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class StepTest {

  @Test
  public void testInitialization() {
    Step step = new Step(0, 0, 1);
    assertThat(step.getValue(), is(0.0));
    assertThat(step.getPath().size(), is(1));
  }

  @Test
  public void testSetValue() {
    Step step = new Step(0, 0, 1);
    step.setValue(10.1);
    assertThat(step.getValue(), is(10.1));
  }

  @Test
  public void testAddToPath() {
    Step step = new Step(0, 0, 1);
    step.addToPath(new Interval(0, 1));
    assertThat(step.getPath().size(), is(2));
    assertThat(step.getPath().get(1).getStart(), is(0));
    assertThat(step.getPath().get(1).getEnd(), is(1));

    step.addToPath(new Interval(1, 2));
    assertThat(step.getPath().size(), is(3));
    assertThat(step.getPath().get(2).getStart(), is(1));
    assertThat(step.getPath().get(2).getEnd(), is(2));
  }

  @Test
  public void testSetPathAfterInitialization() {
    Step step = new Step(0, 0, 1);
    List<Interval> path = new ArrayList<>();
    path.add(new Interval(0, 1));
    path.add(new Interval(0, 2));

    step.setPath(path);
    assertThat(step.getPath(), is(path));
  }

  @Test
  public void testSetPathAfterPathIsPopulated() {
    Step step = new Step(0, 0,1 );
    List<Interval> path = new ArrayList<>();
    path.add(new Interval(0, 1));
    path.add(new Interval(0, 2));

    step.addToPath(new Interval(0, 10));
    step.setPath(path);

    assertThat(step.getPath(), is(path));
  }
}