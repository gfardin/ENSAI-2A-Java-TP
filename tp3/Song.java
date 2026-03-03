package fr.ensai.mediaplayer;

/**
 * Represents a song with essential attributes.
 */
public class Song {
    private String singer;
    private String title;
    private int year;
    private int duration;
    private String lyrics;
    private String author;
    private String composer;

    /**
     * Constructs a new Song object.
     *
     * @param title    The title of the song.
     * @param singer   The singer of the song.
     * @param title    The title of the song.
     * @param year     The year the song was released.
     * @param duration The duration of the song in seconds.
     * @param lyrics   The lyrics of the song.
     * @param author   The author of the song.
     * @param composer The composer of the song.
     */
    public Song(String title, String singer, int year, int duration, String lyrics, String author, String composer) {
        this.title = title;
        this.singer = singer;
        this.year = year;
        this.duration = duration;
        this.lyrics = lyrics;
        this.author = author;
        this.composer = composer;
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

}