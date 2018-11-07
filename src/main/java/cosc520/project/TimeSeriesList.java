package cosc520.project;

import java.util.List;

public interface TimeSeriesList {
    void add(double[] ts);
    double[] getByIndex(int idx);
    List<double[]> getList();
    TimeSeriesList truncate(int start, int end);

    double[][] toDoubleMatrix();
}
