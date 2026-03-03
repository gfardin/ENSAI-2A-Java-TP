package fr.ensai.mediaplayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a playlist of media items.
 */
public class Playlist {
    private String name;
    private List<Media> medias;
    private int totalDuration;

    /**
     * Constructs a new Playlist object.
     *
     * @param name The name of the playlist.
     */
    public Playlist(String name) {
        this.name = name;
        this.medias = new ArrayList<>();
        this.totalDuration = 0;
    }

    /**
     * Constructs a new Playlist object by copying another playlist.
     *
     * @param originalPlaylist The playlist to copy.
     */
    public Playlist(Playlist originalPlaylist) {
        this.name = originalPlaylist.name;
        this.medias = new ArrayList<>(originalPlaylist.medias);
        this.totalDuration = originalPlaylist.totalDuration;
    }

    public List<Media> getMedias() {
        return medias;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    /**
     * Adds a media item to the playlist.
     *
     * @param media The media to add.
     */
    public void addMedia(Media media) {
        this.medias.add(media);
        this.totalDuration += media.getDuration();
    }

    /**
     * Removes a media from the playlist.
     *
     * @param media The media to remove.
     * @return true If the media was in the Playlist.
     */
    public boolean removeMedia(Media media) {
        int nbOccurences = 0;
        while (this.medias.remove(media)) {
            this.totalDuration -= media.getDuration();
            nbOccurences++;
        }
        return nbOccurences > 0;
    }

    /**
     * Removes a media from the playlist at the specified index.
     *
     * @param index The index of the media to remove.
     */
    public void removeMedia(int index) {
        if (index >= 0 && index < this.medias.size()) {
            Media removedMedia = this.medias.remove(index);
            this.totalDuration -= removedMedia.getDuration();
        } else {
            System.err.println("Invalid index: " + index);
        }
    }

    /**
     * Plays the media items in the playlist.
     *
     * @param random If true, plays the tracks in random order; otherwise, plays
     *               them in the order they were added.
     */
    public void play(boolean random) {
        List<Media> playOrder = new ArrayList<>(this.medias);

        if (random) {
            Collections.shuffle(playOrder);
        }

        for (Media media : playOrder) {
            media.play();
        }
    }

}