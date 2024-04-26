package ATC;

/**
 * class used to implement observer
 */
public interface AircraftListener {
    void update(Aircraft aircraft, Aircraft.AircraftState aircraftState);
}
