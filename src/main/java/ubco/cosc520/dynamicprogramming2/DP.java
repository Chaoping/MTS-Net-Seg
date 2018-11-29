package ubco.cosc520.dynamicprogramming2;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import lombok.NonNull;
import ubco.cosc520.dynamicprogramming.Step;
import ubco.cosc520.graph.Graph;
import ubco.cosc520.graph.TwoGraphOperator;

/**
 * Produces the Dynamic Programming Path.
 */
public class DP {

  private final TwoGraphOperator<Double> distanceCalculator;

  @Inject
  public PathMapper(@NonNull TwoGraphOperator<Double> distanceCalculator) {
    this.distanceCalculator = distanceCalculator;
  }

  /**
   * Performs the dynamic programming algorithm on a table of {@link Graph} objects.
   * @param graphs The table of {@link Graph} objects
   * @return A {@link List} of {@link Integer}s representing the position of the splits.
   */
  public List<Interval> dynamicProgramming(@NonNull Graph[][] graphs) {
	  int numTimePoints = graphs.length;

	  // Dynamic programming table stores Step(w), the maximum value up to w
	  List<OPT> dptable = new ArrayList<>();

	  for (int i = 0; i < numTimePoints; i++) {
	      dptable.add(new OPT());
	  }
	  
	  //initialization
	  dptable.get(0).setValue(0);
	  dptable.get(0).addToPath(new Interval(0,0));
	  
	  for(int i = 1; i < numTimePoints; i++) {
		  
		  //default
		  double val = 0;
		  double netVal = 0;
		  List<Interval> path = new ArrayList<Interval>();
		  path.add(new Interval(0,i));
		  
		  
		  for(int j = 0; j < i; j++) {
			  Interval lastInterval = dptable.get(j).getPath().get(dptable.get(j).getPath().size()-1);
			  double newSegVal = dptable.get(j).getNetValue() + 
					  distanceCalculator.operate(graphs[lastInterval.start][lastInterval.end], graphs[j + 1][i])+
					  bp(1.0, dptable.get(j).getPath().size() + 1, numTimePoints - 2);
			  
			  if(newSegVal > val) {
				  val = newSegVal;
				  netVal = dptable.get(j).getNetValue() + 
						  distanceCalculator.operate(graphs[lastInterval.start][lastInterval.end], graphs[j + 1][i]);
				  path = new ArrayList<>(dptable.get(j).getPath());
		          path.add(new Interval(j+1, i));
			  }
					  
		  }
		  dptable.get(i).setPath(path);
		  dptable.get(i).setValue(val);
		  dptable.get(i).setNetValue(netVal);;
		  
	  }
	  
	  
	  
	  
	  return dptable.get(numTimePoints-1).getPath();
  }

  private double bp(double v, int depth, int n) {
    return Math.exp(v * depth / n);
  }
}



