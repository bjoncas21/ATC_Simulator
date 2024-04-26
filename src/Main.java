import ATC.ControlTower;
import ATC.GUI;
import Skeleton.SimulationInput;
import Skeleton.StatisticsContainer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    /**
     * Runs the aviation simulation with the given input and returns the statistics
     * produced from the test run. Simplifies the testing process.
     *
     * @param input The input to run the test with.
     * @return The statistics of the test run.
     **/
    public static StatisticsContainer runTest(SimulationInput input) {
        // Initialize the stats singleton here so the input can
        // be ignored in future calls
        StatisticsContainer stats = StatisticsContainer.getInstance(input);

        // Initialize the control tower singleton
        ControlTower controlTower = ControlTower.getInstance();

        // Start the simulation
        Matrix.run(input);

        return stats;
    }

    /**
     * See method above for details.
     **/
    public static StatisticsContainer runTest(ArrayList<ArrayList<String>> input) {
        return runTest(new SimulationInput(input));
    }

    /**
     * gui i
     * @param args command line arguments
     */
    public static void main(String[] args) {

        SwingUtilities.invokeLater(GUI::new);

        SimulationInput si = new SimulationInput();
        si.addInput("Time", List.of("10")); // The time to run in seconds
        si.addInput("ActionsPerSecond", List.of("1"));
        si.addInput("NumberOfRunways", List.of("3")); // Number of runways in the simulation
        si.addInput("NumberOfAircraft", List.of("5")); // Number of aircraft in the simulation

        // Run the simulation
        StatisticsContainer stats = runTest(si);

        // Post the finalized statistics
        stats.printStatisticsContainer();

    }
}
