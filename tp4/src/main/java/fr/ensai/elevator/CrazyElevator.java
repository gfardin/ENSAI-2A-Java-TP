package fr.ensai.elevator;

import java.util.Random;

import java.util.ArrayList;
import java.util.List;

public class CrazyElevator extends Elevator {

    private static Random random = new Random();

    /**
     * Constructs a new CrazyElevator with the specified parameters.
     * 
     * @param id         the unique identifier for the elevator
     * @param startFloor the floor where the elevator starts
     * @param capacity   the maximum number of passengers the elevator can carry
     */
    public CrazyElevator(int id, int startFloor, int capacity) {
        super(id, startFloor, capacity);
    }

    @Override
    /**
     * Moves the crazy elevator randomly.
     */
    public void move() {
        int behaviour = random.nextInt(3);        
        if (!destinationQueue.isEmpty()){
            if (behaviour == 0) {
                this.currentFloor = destinationQueue.removeFirst();
            } else if (behaviour == 1) {
                destinationQueue.remove(0);
                if (!destinationQueue.isEmpty()){
                    this.currentFloor = destinationQueue.removeFirst();
                }
            } else {
                destinationQueue.remove(0);
            }
        }
    }

    @Override
    /**
     * Unloads passengers whose target floor matches the current floor (half the time).
     * Updates the lastUnloaded list.
     * 
     * @param floor the Floor where passengers will exit
     * @return the number of passengers unloaded
     */
    public int unloadPassengers(Floor floor) {
        int behaviour = random.nextInt(2);
        if (behaviour == 0) {
            this.lastUnloaded.clear();
        } else {       
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
        }
        return this.lastUnloaded.size();
    }

    /**
     * Loads passengers waiting on the specified floor until the elevator is full.
     * Adds their target floors to the destination queue.
     * If the elevator is full, send passengers to another dimension (they vanish)
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
        if (this.isFull()) {
            this.passengers.clear();
        }
    }


}