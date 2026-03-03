package fr.ensai.mediaplayer;

import java.util.Arrays;
import java.util.List;

/**
 * Represents a podcast.
 */
public class Podcast extends Media {
    private String host;
    private String topic;
    private String subtitles;

    /**
     * Constructs a new Podcast object.
     *
     * @param title     The title of the podcast.
     * @param host      The host of the podcast.
     * @param topic     The topic of the podcast.
     * @param duration  The duration of the podcast in seconds.
     * @param year      The year the podcast was released.
     * @param subtitles The subtitles of the podcast.
     */
    public Podcast(String title, String host, String topic, int year, int duration, String subtitles) {
        super(title, duration, year);
        this.host = host;
        this.topic = topic;
        this.subtitles = subtitles;
    }

    /**
     * String representation of the Song.
     */
    @Override
    public String toString() {
        return "Podcast " + this.title + " by " + this.host;
    }

    /**
     * Plays the Podcasts by printing the subtitles with a small delay between each
     * word.
     */
    @Override
    public void play() {
        System.out.println("*".repeat(50));
        System.out.println("* " + this);
        System.out.println("*".repeat(50));

        List<String> words = Arrays.asList(this.subtitles.split(" "));

        for (String word : words) {
            System.out.print(word + " ");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread was interrupted");
            }
        }
        System.out.println();
    }
}