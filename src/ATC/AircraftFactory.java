package ATC;

import Skeleton.SimulationInput;

/**
 * factory class to help create objects of different types of aircrafts
 */
public class AircraftFactory {
    /**
     * creates aircrafts
     * @param name name of aircraft
     * @param input sim input
     * @return
     */
    public static Aircraft createAircraft(String name, SimulationInput input) {
        try {
            // Attempt to get the aircraft type from the input
            String type = "Commercial"; // Ensure that this key matches what's expected in your input configuration

            // Create different types of aircraft based on the type read from the input
            switch (type) {
                case "Commercial":
                    return new CommercialAircraft(name, input);
                case "Private":
                    return new PrivateAircraft(name, input);
                default:
                    throw new IllegalArgumentException("Unknown aircraft type: " + type);
            }
        } catch (RuntimeException e) {
            // Handle the case where the input does not contain the necessary information
            System.err.println(e.getMessage());
            return null; // or handle more gracefully depending on your error handling strategy
        }
    }
}
