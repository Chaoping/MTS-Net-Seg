package ubco.cosc520;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import ubco.cosc520.timeseries.TimeSeriesList;
import ubco.cosc520.timeseries.TimeSeriesListImpl;

public class DataLoader {

  public static TimeSeriesList fromFile(String filename) {

    String data;
    try {
      Path path;
      path = Paths.get(DataLoader.class.getClassLoader()
          .getResource("series.csv").toURI());

      Stream<String> lines = Files.lines(path);
      data = lines.collect(Collectors.joining("\n"));
      lines.close();

    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }

    List<double[]> allTimeSeries = new ArrayList<>();

    for (String line : data.split("\n")) {

      String[] stringValues = line.split(",");
      double[] doubles = new double[stringValues.length];
      for (int i = 0; i < stringValues.length; i++) {
        doubles[i] = Double.parseDouble(stringValues[i]);
      }
      allTimeSeries.add(doubles);
    }
    TimeSeriesList timeSeriesList = new TimeSeriesListImpl();
    for (double[] doubles : allTimeSeries) {
      timeSeriesList.add(doubles);
    }
    return timeSeriesList;
  }
}
