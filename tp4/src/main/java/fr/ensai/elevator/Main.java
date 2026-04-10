package fr.ensai.elevator;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {

        // --------------------------------------------------------------------
        // Create Floors, Elevators and Hotel
        // --------------------------------------------------------------------

        final int elevatorCapacity = Config.getInt("hotel.elevator.capacity");
        final int nbSteps = Config.getInt("steps.count");
        final int msBetweenSteps = Config.getInt("steps.delay");
        final int nbFloor = Config.getInt("hotel.number.floor");
        final int nbElevator = Config.getInt("hotel.number.elevator");


        List<Floor> floors = new ArrayList<>();
        for (int i = 0; i < nbFloor; i++) {
            floors.add(new Floor(i));
        }

        List<Elevator> elevators = new ArrayList<>();
        for (int i = 0; i < nbElevator; i++) {
            elevators.add(new Elevator(i, 0, elevatorCapacity));
        }


        Hotel hotel = new Hotel(floors, elevators);

        // --------------------------------------------------------------------
        // Run simulation
        // --------------------------------------------------------------------

        for (int step = 1; step <= nbSteps; step++) {
            logger.info("\nStep: {}\n--------", step);

            hotel.update();
            hotel.spawnPerson();
            hotel.display(step);
            Thread.sleep(msBetweenSteps);
        }
    }

}