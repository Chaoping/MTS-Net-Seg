package main.java.ubco.cosc520.graph;
//package RAT2;

//import RAT2.CreateSimulatedData;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        double[][] gd = {
                {1,5,10},
                {2,10,15},
                {3.5,10,17},
                {3,89,20},
                {4,1000,99},
                {31,90,4}
        };
		CreateSimulatedData sim=new CreateSimulatedData(gd);
		sim.firstStep(sim.rawData);
		
		sim.getSimulationColumn(sim.simulatedData.getColumnDimension()-1);
		for(double[] row:sim.D.getData()) {
			for(double i:row) {
				System.out.print(i+" ");
			}
			System.out.println("");
		}
		
		System.out.println("-----------------");
		
		for(double[] row:sim.simulatedData.getData()) {
			for(double i:row) {
				System.out.print(i+" ");
			}
			System.out.println("");
		}
		
	}

}

