package main.java.ubco.cosc520.graph;
//package RAT2;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.stat.correlation.PearsonsCorrelation;

public class CreateSimulatedData {

	// assumet original dataset is a mtrix
	// the create a new matrx;
	
	RealMatrix simulatedData;
	RealMatrix D;
	RealMatrix rawData;
	
	public CreateSimulatedData(double[][] rawData) {
		// TODO Auto-generated constructor stub
		this.rawData=new Array2DRowRealMatrix(rawData);
	}

	public double[] getSimulationColumn(int columnIndex) {
		if(columnIndex==0) {
			//the first column of the simulated datamatrix is simply the first column of
			//the rawdatamatrix
			simulatedData.setColumn(0, rawData.getColumn(0));
			return simulatedData.getColumn(0);
		}else {
			//前一列的数据
			double[] preceding=getSimulationColumn(columnIndex-1);
			int lengthOfPreceding=preceding.length;
			//create hashmap to store the value in the preceding column and its index
			HashMap<Double,Integer> storage=new HashMap<>();
			for(int i=0;i<lengthOfPreceding;i++) 
				storage.put(preceding[i], i);

			double numOfBins=15;
			//create bins
			HashMap<Integer,LinkedList<Double>> bins=new HashMap<>();
			for(int i=0;i<numOfBins;i++)
				bins.put(i, new LinkedList<>());
			//sort the preceding column and calculate range
			double[] sortedPreceding=Arrays.copyOf(preceding, lengthOfPreceding);
			Arrays.sort(sortedPreceding);
			double range=sortedPreceding[lengthOfPreceding-1]-sortedPreceding[0];
			double interval=(range/numOfBins);
			//put each element into corresponding bin from 0 to 14
			for(int i=0;i<lengthOfPreceding;i++) {
				for(double s=sortedPreceding[0],key=0;s<sortedPreceding[lengthOfPreceding-1];key++,s+=interval) {
					//if current equal to last element,which is the max element,
					//then add it into the last bin
					if(sortedPreceding[i]==sortedPreceding[lengthOfPreceding-1]) {
						bins.get((int)(numOfBins-1)).add(sortedPreceding[i]);
						continue;
					}//end of if
					//if current is not the max element, add it to corresponding bin	
					if(sortedPreceding[i]>=s&&sortedPreceding[i]<(s+interval)) {
						bins.get((int)key).add(sortedPreceding[i]);
					}//end of if
				}//end of inner for
			}//end of out for
			
			double[] current=new double[lengthOfPreceding];
			for(int i=0;i<lengthOfPreceding;i++) {
				double[] diffColumn=D.getColumn(columnIndex-1);
				double precedingValue=preceding[i];
				//try to find the corresponding bin
//				double indexOfBin=-1;
				LinkedList<Double> rightBin=null;
				for (Entry<Integer, LinkedList<Double>> entry : bins.entrySet()) {
				    int key = entry.getKey();
				    LinkedList<Double> list = entry.getValue();
				    if(list.contains(precedingValue)) {
//				    	indexOfBin=key;
				    	rightBin=list;
				    	break;
				    }//end of if
				}//end of for
				//random pick a value from the right bin and find its index in orginal column
				Random rand = new Random();
				double randomValue = rightBin.get(rand.nextInt(rightBin.size()));
				int randomIndex=storage.get(randomValue);
				//find value from difference column
				double diffValue=diffColumn[randomIndex];
				current[i]=precedingValue+diffValue;
			}
			simulatedData.setColumn(columnIndex, current);
			return current;
		}

	}
	
	public void firstStep(RealMatrix inputM) {
		//create difference matrix and empty simulated data matrix
		int numOfRow=inputM.getRowDimension();
		int numOfColumn=inputM.getColumnDimension();
		//given a m X n input matrix, we create a m X n-1 difference matrix
		this.D=inputM.createMatrix(numOfRow, numOfColumn-1);
		this.simulatedData=inputM.createMatrix(numOfRow, numOfColumn);
		
		for(int i=0;i<numOfColumn-1;i++) {
			double[] columnD=new double[numOfRow];
			double[] current=inputM.getColumn(i);
			double[] next=inputM.getColumn(i+1);
			for(int j=0;j<current.length;j++)
				columnD[j]=next[j]-current[j];
			this.D.setColumn(i, columnD);
		}
	}

	static int isLinked(double[][] rawInput, int start, int end, int i, int j) {
		//start and end inclusive
		double[][] convertedMatrix = new double[end - start + 1][2];
		if (end - start < 2) {
			return 1;
		} else {
			for (int c = 0,s=start; c < end - start + 1; c++,s++) {
				convertedMatrix[c][0] = rawInput[i][s];
				convertedMatrix[c][1] = rawInput[j][s];
			} // end of for of constructing converted matrix

			PearsonsCorrelation pc = new PearsonsCorrelation(convertedMatrix);
			RealMatrix cMatrix = pc.getCorrelationMatrix();
			RealMatrix pMatrix = pc.getCorrelationPValues();

			// test part,output the calculating result of correalation and correlation
			// pvalue
			System.out.println(pMatrix.getEntry(0, 1) + " " + pMatrix.getEntry(1, 0));
			System.out.println(cMatrix.getEntry(0, 1) + " " + cMatrix.getEntry(1, 0));

			// 0.01 is significance level
			if (pMatrix.getEntry(0, 1) > 0.01)
				return 0;
			else
				return 1;
		}
	}// end of isLInked

}

