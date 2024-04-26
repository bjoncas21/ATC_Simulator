package ATC;

import Skeleton.SimulationInput;

/**
 * creates aircraft of type private
 */
public class PrivateAircraft extends Aircraft {
    /**
     * constructor
     * @param name name of aircraft
     * @param input sim input
     */
    public PrivateAircraft(String name, SimulationInput input) {
        super("Private " + name, input);
    }

}
