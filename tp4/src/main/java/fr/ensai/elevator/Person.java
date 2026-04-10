package fr.ensai.elevator;

import java.util.Random;

/**
 * Represents a person in the hotel elevator simulation.
 */
public class Person {

    private String nickname;
    private int startFloor;
    private int targetFloor;

    private static final String CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static int NEXT_CHAR_INDEX = 0;

    private static Random random = new Random();

    /**
     * Constructs a new Person starting at the specified floor.
     * 
     * @param startFloor the floor number where the person starts
     */
    public Person(int startFloor) {
        this.nickname = Person.generateNickname();
        this.startFloor = startFloor;
        this.targetFloor = Person.generateTargetFloor();
    }

    /**
     * Generates a sequential nickname from the predefined character set.
     * Loops back to the beginning when the end of the set is reached.
     * 
     * @return a unique nickname character
     */
    private static String generateNickname() {
        NEXT_CHAR_INDEX = (NEXT_CHAR_INDEX == 25) ? 0 : NEXT_CHAR_INDEX;
        return String.valueOf(CHARS.charAt(NEXT_CHAR_INDEX++));
    }

    /**
     * Generates a random target floor.
     * 
     * @return the target floor number
     */
    private static int generateTargetFloor() {
        int nbFloor = Config.getInt("hotel.number.floor");
        return random.nextInt(nbFloor);
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getStartFloor() {
        return this.startFloor;
    }

    public int getTargetFloor() {
        return this.targetFloor;
    }

    @Override
    public String toString() {
        return this.nickname + this.targetFloor;
    }
}