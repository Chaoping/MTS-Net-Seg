package ubco.cosc520;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import lombok.Getter;

public class Args {

  @Parameter(
      names = "-file",
      converter = FileConverter.class,
      required = true,
      description = "The file to process"
  )
  @Getter
  private File file;

  @Parameter(names = "-v", description = "v paramter for tuning exponential breakpoint penalty")
  @Getter
  private double v = 1.0;

  @Parameter(names = "-pValue", description = "P-Value to use when comparing similarity matrices")
  @Getter
  private double pValue = 0.01;

  @Parameter(names = "--help", help = true)
  @Getter
  private boolean help;
}
