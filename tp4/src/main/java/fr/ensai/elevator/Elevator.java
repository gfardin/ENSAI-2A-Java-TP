package fr.ensai.elevator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents an elevator in a hotel simulation.
 * <p>
 * Each Elevator has an ID, capacity, current floor, a list of passengers, a
 * queue of destination floors, and a record of last unloaded passengers. It
 * can move between floors, load and unload passengers, and maintain its queue
 * of destinations.
 */
public class Elevator {

    private static final Logger logger = LogManager.getLogger(Elevator.class);

    protected int id;
    protected int capacity;
    protected int currentFloor;
    protected List<Integer> destinationQueue;
    protected List<Person> passengers;
    protected List<Person> lastUnloaded;

    /**
     * Constructs a new Elevator with the specified parameters.
     * 
     * @param id         the unique identifier for the elevator
     * @param startFloor the floor where the elevator starts
     * @param capacity   the maximum number of passengers the elevator can carry
     */
    public Elevator(int id, int startFloor, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.currentFloor = startFloor;
        this.destinationQueue = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.lastUnloaded = new ArrayList<>();
    }

    public int getId() {
        return this.id;
    }

    public int getCurrentFloor() {
        return this.currentFloor;
    }

    /**
     * Checks if the elevator has the specified floor in its destination queue.
     * 
     * @param floorNumber the floor to check
     * @return true if the floor is in the queue, false otherwise
     */
    public boolean containDestination(int floorNumber) {
        return this.destinationQueue.contains(floorNumber);
    }

    /**
     * Returns the size of the destination queue.
     * 
     * @return number of floors in the queue
     */
    public int getDestinationQueueSize() {
        return this.destinationQueue.size();
    }

    /**
     * Returns a string representation of the destination queue.
     * 
     * @return comma-separated list of floor numbers, or "-" if the queue is empty
     */
    public String getDestinationQueueStr() {
        return this.destinationQueue.stream()
                .map(String::valueOf)
                .reduce((a, b) -> a + ", " + b)
                .orElse("-");
    }

    /**
     * Adds a new floor to the destination queue if it is not already present.
     * 
     * @param floor the floor number to add
     */
    public void addDestination(int floor) {
        if (!this.destinationQueue.contains(floor)) {
            this.destinationQueue.add(floor);
            logger.info("\tElevator {}: destinationQueue {}", this.id, this.getDestinationQueueStr());
        }
    }

    /**
     * Unloads passengers whose target floor matches the current floor.
     * Updates the lastUnloaded list.
     * 
     * @param floor the Floor where passengers will exit
     * @return the number of passengers unloaded
     */
    public int unloadPassengers(Floor floor) {
        this.lastUnloaded.clear();

        List<Person> remaining = new ArrayList<>();

        for (Person p : this.passengers) {
            if (p.getTargetFloor() == floor.getNumber()) {
                this.lastUnloaded.add(p);
                logger.info("Floor {}: {}{} leaves Elevator {}",
                        floor.getNumber(),
                        p.getNickname(),
                        p.getTargetFloor(),
                        this.id);
            } else {
                remaining.add(p);
            }
        }
        this.passengers = remaining;
        return this.lastUnloaded.size();
    }

    /**
     * Loads passengers waiting on the specified floor until the elevator is full.
     * Adds their target floors to the destination queue.
     * 
     * @param floor the Floor where passengers board the elevator
     */
    public void loadPassengers(Floor floor) {

        while (!this.isFull()) {
            Person person = floor.boardNextPerson();
            if (person == null)
                break;

            logger.info("Floor {}: {}{} enter Elevator {}",
                    floor.getNumber(),
                    person.getNickname(),
                    person.getTargetFloor(),
                    this.id);
            this.passengers.add(person);
            this.addDestination(person.getTargetFloor());
        }
    }

    /**
     * Moves the elevator to the next floor in its destination queue.
     * Removes that floor from the queue.
     */
    public void move() {
        if (!destinationQueue.isEmpty())
            this.currentFloor = destinationQueue.removeFirst();
    }

    /**
     * Returns a string representation of the elevator at a specific floor,
     * showing passengers inside and last unloaded passengers.
     * 
     * @param floorNumber the floor number to display
     * @return formatted string of passengers and unloaded people at that floor
     */
    public String displayAtFloor(int floorNumber) {

        if (this.currentFloor != floorNumber) {
            return "";
        }

        String inside = passengers.stream()
                .map(Person::toString)
                .collect(Collectors.joining(" "));

        String padded = String.format("%-" + (capacity * 3 - 1) + "s", inside);

        String unloaded = lastUnloaded.stream()
                .map(Person::toString)
                .collect(Collectors.joining(" "));

        return "[" + padded + "]" +
                (unloaded.isBlank() ? "" : " " + unloaded);
    }

    /**
     * Tells if an elevator is full.
     */
    public boolean isFull() {
        return this.passengers.size() >= this.capacity;
    }
}