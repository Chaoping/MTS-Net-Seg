package main.java.ubco.cosc520.graph;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        double[][] gd = {
                {1,5,10},
                {2,6,15},
                {5,10,17},
                {6,12,20},
                {20,1,99},
                {21,5,4}
        };
		CreateSimulatedData sim=new CreateSimulatedData(gd);
		sim.firstStep(sim.rawData);

	}

}
