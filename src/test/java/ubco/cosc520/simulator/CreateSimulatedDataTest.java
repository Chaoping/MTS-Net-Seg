package ubco.cosc520.simulator;

import org.junit.Test;
import ubco.cosc520.simulator.CreateSimulatedData;

public class CreateSimulatedDataTest {

    // TODO Auto-generated method stub
    @Test
    public void test(){
        double[][] gd = {
                {1, 5, 10},
                {2, 10, 15},
                {3.5, 10, 17},
                {3, 89, 20},
                {4, 1000, 99},
                {31, 90, 4}
        };
        CreateSimulatedData sim = new CreateSimulatedData(gd);

        //Random - Not sure what we can assert here.
        for (double[] row : sim.getSimulatedData()) {
            for (double i : row) {
                System.out.print(i + " ");
            }
            System.out.println("");
        }
    }
}