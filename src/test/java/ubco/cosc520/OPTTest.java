package ubco.cosc520;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class OPTTest {

  @Test
  public void testInitialization() {
    OPT opt = new OPT();
    assertThat(opt.getValue(), is(0.0));
    assertThat(opt.getPath().size(), is(0));
  }

  @Test
  public void testSetValue() {
    OPT opt = new OPT();
    opt.setValue(10.1);
    assertThat(opt.getValue(), is(10.1));
  }

  @Test
  public void testAddToPath() {
    OPT opt = new OPT();
    opt.addToPath(1);
    assertThat(opt.getPath().size(), is(1));
    assertThat(opt.getPath().get(0), is(1));

    opt.addToPath(2);
    assertThat(opt.getPath().size(), is(2));
    assertThat(opt.getPath().get(1), is(2));
  }

  @Test
  public void testSetPathAfterInitialization() {
    OPT opt = new OPT();
    List<Integer> path = new ArrayList<>();
    path.add(1);
    path.add(2);

    opt.setPath(path);
    assertThat(opt.getPath(), is(path));
  }

  @Test
  public void testSetPathAfterPathIsPopulated() {
    OPT opt = new OPT();
    List<Integer> path = new ArrayList<>();
    path.add(1);
    path.add(2);

    opt.addToPath(10);
    opt.setPath(path);

    assertThat(opt.getPath(), is(path));
  }
}