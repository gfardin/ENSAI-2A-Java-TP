package fr.ensai.mediaplayer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a song with essential attributes.
 */
public class Song extends Media {
    private Artist singer;
    private String lyrics;
    private Artist author;
    private Artist composer;
    private List<MusicalGenre> genres;

    /**
     * Constructs a new Song object.
     *
     * @param title    The title of the song.
     * @param singer   The singer of the song.
     * @param year     The year the song was released.
     * @param duration The duration of the song in seconds.
     * @param lyrics   The lyrics of the song.
     * @param author   The author of the song.
     * @param composer The composer of the song.
     * @param genres   The list of musical genres for the song.
     */
    public Song(String title, Artist singer, int year, int duration, String lyrics, Artist author, Artist composer,
            List<MusicalGenre> genres) {

        super(title, duration, year);
        this.singer = singer;
        this.lyrics = lyrics;
        this.author = author;
        this.composer = composer;
        this.genres = genres;
    }

    /**
     * String representation of the Song.
     */
    @Override
    public String toString() {
        return "Song " + this.title + " by " + this.singer;
    }

    /**
     * Indicates whether some other object is "equal to" this one. Two Song
     * objects are considered equal if they have the same title, singer, and year.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || this.getClass() != o.getClass())
            return false;
        Song otherSong = (Song) o;
        return this.year == otherSong.year &&
                Objects.equals(this.title, otherSong.title) &&
                Objects.equals(this.singer, otherSong.singer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.title, this.singer, this.year);
    }

    /**
     * Plays the Song by printing the lyrics with a small delay between each word.
     */
    @Override
    public void play() {
        System.out.println("*".repeat(50));
        System.out.println("* " + this);
        System.out.println("*".repeat(50));

        if (this.lyrics != null) {

            List<String> words = Stream.of(this.lyrics.split(" ")).collect(Collectors.toList());

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
        } else {
            System.out.println("No lyrics available.");
        }
    }
}
