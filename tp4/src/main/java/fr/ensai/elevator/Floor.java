package fr.ensai.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a floor in a hotel elevator simulation.
 * <p>
 * Each Floor can have people waiting for elevators, spawn new people
 * probabilistically, and request elevators to pick up waiting people.
 */
public class Floor {
    private static final Logger logger = LogManager.getLogger(Floor.class);

    private int number;
    private List<Person> waitingPeople;
    private double spawnProbability;

    private static Random random = new Random();

    /**
     * Constructs a new Floor with the given floor number.
     * The spawn probability differs depending on whether you are on the ground
     * floor or not.
     * 
     * @param number the number of the floor
     */
    public Floor(int number) {
        this.number = number;
        this.waitingPeople = new ArrayList<>();
        this.spawnProbability = (number == 0) ? Config.getDouble("hotel.spawn-probability.ground")
                : Config.getDouble("hotel.spawn-probability.other");
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * Removes and returns the next person in the waiting queue.
     * 
     * @return the next Person to board an elevator, or null if no one is waiting
     */
    public Person boardNextPerson() {
        if (this.waitingPeople.isEmpty()) {
            return null;
        }
        return this.waitingPeople.remove(0);
    }

    /**
     * Attempts to spawn a new person on this floor based on the spawn probability.
     * If a person is spawned, the floor requests an elevator.
     * 
     * @param elevators the list of elevators available in the hotel
     */
    public void trySpawnPerson(List<Elevator> elevators) {

        if (random.nextDouble() < this.spawnProbability) {

            Person p = new Person(this.number);
            this.waitingPeople.add(p);
            logger.info("Floor {}: Person {}{} call an Elevator", this.number, p.getNickname(), p.getTargetFloor());
            this.requestElevator(elevators);
        }
    }

    /**
     * Press the button to call an elevator.
     * If an elevator is already on its way to the floor, nothing happen.
     * Otherwise, requests the least busy elevator to stop at this floor.
     * 
     * @param elevators the list of elevators available in the hotel
     */
    public void requestElevator(List<Elevator> elevators) {
        int min = Config.getInt("hotel.elevator.capacity") + 1;
        int leastbusy = 0;
        boolean coming = false;
        int l = elevators.size();
        for (int i = 0; i < l; i++) {
            if (elevators.get(i).containDestination(this.number)) {
                coming = true;
                break;
            }
            else{
                int s = elevators.get(i).getDestinationQueueSize();
                if (s <= min) {
                    min = s;
                    leastbusy = i;
                }
            }
        }
        if (!coming) {
            elevators.get(leastbusy).addDestination(this.number);
        }
    }

    /**
     * Displays the current status of this floor in the console, including
     * floor number, elevator contents, and waiting people.
     * 
     * @param elevators the list of elevators to display on this floor
     */
    public void display(List<Elevator> elevators) {

        int floorColWidth = Config.getInt("hotel.display.column-width.floor");
        int elevatorColWidth = Config.getInt("hotel.display.column-width.elevator");
        int personsColWidth = Config.getInt("hotel.display.column-width.persons");

        System.out.printf("| %" + floorColWidth + "d ", this.number);

        for (Elevator elevator : elevators) {
            String content = elevator.displayAtFloor(this.number);
            System.out.printf("| %-" + elevatorColWidth + "s ", content);
        }

        System.out.printf("| %-" + personsColWidth + "s |\n", this.waitingPeopleStr());
    }

    /**
     * Returns a string representing all people currently waiting on this floor.
     * 
     * @return a space-separated list of Person objects waiting on this floor
     */
    public String waitingPeopleStr() {
        StringBuilder sb = new StringBuilder();
        for (Person p : this.waitingPeople) {
            sb.append(p).append(" ");
        }
        return sb.toString();
    }
}