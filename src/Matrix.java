import ATC.Aircraft;
import ATC.ControlTower;
import ATC.PrivateAircraft;
import ATC.Runway;
import Skeleton.SimulationInput;
import java.util.ArrayList;
import java.util.List;

/**
 * runs the simulation
 */
public class Matrix {
    /**
     * creates instance of control tower, aircraft, and runways, joins threads
     * @param input sim input
     */
    public static void run(SimulationInput input) {
        // Initialize the control tower
        ControlTower controlTower = ControlTower.getInstance();  // Assuming singleton pattern

        // Retrieve the number of aircraft and runways from input
        int numberOfAircraft = input.getIntegerInput("NumberOfAircraft");
        int numberOfRunways = input.getIntegerInput("NumberOfRunways");

        // Initialize runways
        List<Runway> runways = new ArrayList<>();
        for (int i = 0; i < numberOfRunways; i++) {
            runways.add(new Runway("ATC.Runway " + (i + 1)));
            controlTower.addRunway(runways.get(i));
        }

        // Create and start threads for each aircraft
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < numberOfAircraft; i++) {
            Aircraft aircraft = new PrivateAircraft("ATC.Aircraft " + (i + 1), input);
            Thread thread = new Thread(aircraft);
            threads.add(thread);
            thread.start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Main thread interrupted.");
            }
        }
    }
}
