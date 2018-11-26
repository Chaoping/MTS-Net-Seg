package ubco.cosc520.matrix;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import javax.inject.Inject;
import org.apache.commons.math3.linear.RealMatrix;

public class MatrixRandomizer implements SingleMatrixOperator {

  private final SingleMatrixOperator differenceMatrixCalculator;
  private final Random random;

  private RealMatrix outputData;
  private RealMatrix differenceMatrix;
  private RealMatrix rawData;

  private static final int NUMBER_OF_BINS = 15;

  @Inject
  public MatrixRandomizer(SingleMatrixOperator differenceMatrixCalculator, Random random) {
    this.differenceMatrixCalculator = differenceMatrixCalculator;
    this.random = random;
  }

  @Override
  public RealMatrix operate(RealMatrix inputData) {
    rawData = inputData;

    int numOfRow = rawData.getRowDimension();
    int numOfColumn = rawData.getColumnDimension();

    outputData = rawData.createMatrix(numOfRow, numOfColumn);
    differenceMatrix = differenceMatrixCalculator.operate(rawData);

    getSimulationColumn(outputData.getColumnDimension() - 1);
    return outputData;
  }

  private double[] getSimulationColumn(int columnIndex) {

    if (columnIndex == 0) {
      //the first column of the simulated datamatrix is simply the first column of
      //the rawdatamatrix
      outputData.setColumn(0, rawData.getColumn(0));
      return outputData.getColumn(0);
    }

    //ǰһ�е�����
    double[] preceding = getSimulationColumn(columnIndex - 1);
    int lengthOfPreceding = preceding.length;
    //create hashmap to store the value in the preceding column and its index
    HashMap<Double, Integer> storage = new HashMap<>();
    for (int i = 0; i < lengthOfPreceding; i++) {
      storage.put(preceding[i], i);
    }

    //create bins
    HashMap<Integer, List<Double>> bins = new HashMap<>();
    for (int i = 0; i < NUMBER_OF_BINS; i++) {
      bins.put(i, new LinkedList<>());
    }

    //sort the preceding column and calculate range
    double[] sortedPreceding = Arrays.copyOf(preceding, lengthOfPreceding);
    Arrays.sort(sortedPreceding);
    double range = sortedPreceding[lengthOfPreceding - 1] - sortedPreceding[0];
    double interval = range / NUMBER_OF_BINS;
    //put each element into corresponding bin
    for (int i = 0; i < lengthOfPreceding; i++) {
      for (double s = sortedPreceding[0], key = 0; s < sortedPreceding[lengthOfPreceding - 1];
          key++, s += interval) {
        //if current equal to last element,which is the max element,
        //then add it into the last bin
        if (sortedPreceding[i] == sortedPreceding[lengthOfPreceding - 1]) {
          bins.get(NUMBER_OF_BINS - 1).add(sortedPreceding[i]);
          continue;
        }
        //if current is not the max element, add it to corresponding bin
        if (sortedPreceding[i] >= s && sortedPreceding[i] < (s + interval)) {
          bins.get((int) key).add(sortedPreceding[i]);
        }
      }
    }

    double[] current = new double[lengthOfPreceding];
    for (int i = 0; i < lengthOfPreceding; i++) {
      double[] diffColumn = differenceMatrix.getColumn(columnIndex - 1);
      double precedingValue = preceding[i];
      //try to find the corresponding bin
//				double indexOfBin=-1;
      List<Double> rightBin = null;
      for (Entry<Integer, List<Double>> entry : bins.entrySet()) {
        List<Double> list = entry.getValue();
        if (list.contains(precedingValue)) {
//				    	indexOfBin=key;
          rightBin = list;
          break;
        }
      }

      //random pick a value from the right bin and find its index in original column
      double randomValue = rightBin.get(random.nextInt(rightBin.size()));
      int randomIndex = storage.get(randomValue);
      //find value from difference column
      double diffValue = diffColumn[randomIndex];
      current[i] = precedingValue + diffValue;
    }
    outputData.setColumn(columnIndex, current);
    return current;
  }

}
