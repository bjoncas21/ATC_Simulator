package ATC;

import java.util.ArrayList;
import java.util.List;

/**
 * control tower, observes aircrafts, holds a list of aircrafts and pf runways
 */
public class ControlTower implements AircraftListener{

    private static ControlTower instance = null;
    private List<Runway> runways;

    /**
     * constructor
     */
    private ControlTower(){
        this.runways = new ArrayList<>();
    }

    /**
     * getter for control tower
     * @return instance of control tower
     */
    public static ControlTower getInstance(){
        if(instance == null){
            instance = new ControlTower();
        }
        return instance;
    }

    /**
     * adds a runway to the list
     * @param runway runway
     */
    public synchronized void addRunway(Runway runway){
        runways.add(runway);
    }

    /**
     * getter for runways list
     * @return list of runways
     */
    public List<Runway> getRunways() {
        return runways;
    }

    /**
     * updates the control tower on the aircrafts state
     * @param aircraft aircraft
     * @param aircraftState state
     */
    @Override
    public void update(Aircraft aircraft, Aircraft.AircraftState aircraftState) {
        switch (aircraftState) {
            case GROUND:
                System.out.println(aircraft.getName() + " is now on the ground.");
                break;
            case TAKEOFF:
                break;
            case AIR:
                System.out.println(aircraft.getName() + " is in flight.");
                break;
            case LANDING:
                break;
            default:
        }
    }
}
