package ca.ubco.cosc520;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.EnumConverter;
import com.beust.jcommander.converters.FileConverter;
import java.io.File;
import lombok.Getter;

public class Args {

  public enum BREAKPOINT_STRATEGY {
    LINEAR,
    CONSTANT,
    EXPONENTIAL,
    NORMALIZED_EXPONENTIAL
  }

  public enum COMPARATOR_STRATEGY {
    CORRELATION,
    PVALUE
  }

  public enum DIFFERENCE_STRATEGY {
    UNION_DENOMINATOR,
    POSSIBLE_EDGE_DENOMINATOR
  }

  private static class BreakpointEnumConverter extends EnumConverter<BREAKPOINT_STRATEGY> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public BreakpointEnumConverter(String optionName,
        Class<BREAKPOINT_STRATEGY> clazz) {
      super(optionName, clazz);
    }
  }

  private static class ComparatorEnumConverter extends EnumConverter<COMPARATOR_STRATEGY> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public ComparatorEnumConverter(String optionName,
        Class<COMPARATOR_STRATEGY> clazz) {
      super(optionName, clazz);
    }
  }

  private static class DifferenceEnumConverter extends EnumConverter<DIFFERENCE_STRATEGY> {

    /**
     * Constructs a new converter.
     *
     * @param optionName the option name for error reporting
     * @param clazz the enum class
     */
    public DifferenceEnumConverter(String optionName,
        Class<DIFFERENCE_STRATEGY> clazz) {
      super(optionName, clazz);
    }
  }

  @Parameter(
    names = "-file",
    converter = FileConverter.class,
    required = true,
    description = "The file to process"
  )
  @Getter
  private File file;

  @Parameter(
      names = "-breakpointV",
      description = "V paramter for tuning breakpoint penalty"
  )
  @Getter
  private double breakpointV = 1.0;

  @Parameter(names = "-comparatorV", description = "Value to use when comparing matrices")
  @Getter
  private double comparatorV = 0.01;

  @Parameter(
      converter = BreakpointEnumConverter.class,
      names = "-breakpointStrategy",
      description = "The strategy to use for calculating breakpoint penalty"
  )
  @Getter
  private BREAKPOINT_STRATEGY breakpointStrategy = BREAKPOINT_STRATEGY.NORMALIZED_EXPONENTIAL;


  @Parameter(
      converter = ComparatorEnumConverter.class,
      names = "-comparatorStrategy",
      description = "The strategy to use for comparing timeseries"
  )
  @Getter
  private COMPARATOR_STRATEGY comparatorStrategy = COMPARATOR_STRATEGY.PVALUE;


  @Parameter(
      converter = DifferenceEnumConverter.class,
      names = "-differenceStrategy",
      description = "The strategy to use for calculating difference between graphs"
  )
  @Getter
  private DIFFERENCE_STRATEGY differenceStrategy = DIFFERENCE_STRATEGY.POSSIBLE_EDGE_DENOMINATOR;

  @Parameter(
      names = "-start",
      description = "Truncate the timeseries to start at this value (inclusive) before running."
  )
  @Getter
  private Integer start;

  @Parameter(
      names = "-end",
      description = "Truncate the timeseries to end at this value (inclusive) before running."
  )
  @Getter
  private Integer end;

}
