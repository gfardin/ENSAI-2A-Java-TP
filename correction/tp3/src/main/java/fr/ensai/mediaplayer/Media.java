package fr.ensai.mediaplayer;

/**
 * Represents a media item with common attributes.
 */
abstract class Media {
    protected String title;
    protected int duration;
    protected int year;

    /**
     * Constructs a new Media object.
     *
     * @param title    The title of the media.
     * @param duration The duration of the media in seconds.
     * @param year     The year the media was released.
     */
    public Media(String title, int duration, int year) {
        this.title = title;
        this.duration = duration;
        this.year = year;
    }

    public String getTitle() {
        return this.title;
    }

    public int getDuration() {
        return this.duration;
    }

    /**
     * Abstract method to simulate playing the media item.
     */
    public abstract void play();

}