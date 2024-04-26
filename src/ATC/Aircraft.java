package ATC;

import Skeleton.*;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

/**
 * represents an aircraft, handles all of its states and corresponding actions
 */
public class Aircraft extends Unit implements AircraftListener {

    private AircraftState state;
    private final ControlTower controlTower;
    private int flights = 0;
    private float duration;
    private int randomNumber;
    Random random = new Random();

    /**
     * constructor for aircraft, begins on ground
     * @param name name of aircraft
     * @param input sim input
     */
    public Aircraft(String name, SimulationInput input) {
        super(name, input);
        this.controlTower = ControlTower.getInstance();
        this.state = AircraftState.GROUND;
        this.getStats().addStatistic("Flights", new WorkerStatistic("Flights"));
        }

    /**
     * getter for name
     * @return name of aircraft
     */
    @Override
    public String getName() {
        return super.getName();
    }

    /**
     * depending on the current state, aircraft does the corresponding actions and moves to the next state
     */
    @Override
    public void performAction() {
        List<Runway> runways = controlTower.getRunways();
        try {
            switch (state) {
                case GROUND:
                    System.out.println(this.getName() + " is ready to take off ");
                    this.state = AircraftState.TAKEOFF;
                    break;
                case TAKEOFF:
                    takeOff(runways);
                    flights++;
                    break;
                case AIR:
                    randomNumber = 2000 + random.nextInt(4001);  // 6000 - 2000 + 1 = 4001
                    try {
                        Thread.sleep(randomNumber);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    System.out.println(this.getName() + " is ready to land ");
                    this.state = AircraftState.LANDING;
                    break;
                case LANDING:
                    land(runways);
                    break;
            }
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println(e);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        controlTower.update(this, state);
    }

    /**
     * protocol for takeoff, requests an available runway semaphore and proceeds, then releases it
     * @param runways runways
     * @throws InterruptedException
     */
    protected void takeOff(List<Runway> runways) throws InterruptedException {
        for (Runway runway : runways) {
            if (runway.tryAcquire()) {
                System.out.println(super.getName() + " is taking off from " + runway.getId());
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                this.state = AircraftState.AIR;
                runway.release();
                return;
            }
        }
    }

    /**
     * protocol for landing, requests an available runway semaphore and proceeds, then releases it
     * @param runways runways
     * @throws InterruptedException
     */
    protected void land(List<Runway> runways) throws InterruptedException {
        for (Runway runway : runways) {
            if (runway.tryAcquire()) {
                System.out.println(super.getName() + " is landing on " + runway.getId());
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                this.state = AircraftState.GROUND;
                runway.release();
                return;
            }
        }
    }

    /**
     * used to submit statistics gathered
     */
    @Override
        public void submitStatistics() {
            this.getStats().getStatistic("Flights").addValue(flights);
        }

    /**
     * upadates control tower of state
     * @param aircraft aircraft
     * @param aircraftState state
     */
    @Override
    public void update(Aircraft aircraft, AircraftState aircraftState) {
    }

    /**
     * possible states of aircraft
     */
    public enum AircraftState {
        GROUND, TAKEOFF, AIR, LANDING
    }
}




