package ATC;

import Skeleton.SimulationInput;

/**
 * creates aircraft of type commercial
 */
public class CommercialAircraft extends Aircraft {
    /**
     * constructor for commercial aircraft
     * @param name name of aircraft
     * @param input sim input
     */
    public CommercialAircraft(String name, SimulationInput input) {
        super("Commercial " + name, input);
    }

}
