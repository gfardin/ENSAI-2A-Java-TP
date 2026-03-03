package fr.ensai.mediaplayer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlaylistTest {

    private Playlist playlist;
    private Song song1;
    private Song song2;
    private Song song3;

    @BeforeEach
    void beforeEachTest() {
        // GIVEN
        Artist artist1 = new Artist("firstName1", "lastName1", null);
        Artist artist2 = new Artist("firstName2", "lastName2", null);
        Artist artist3 = new Artist("firstName3", "lastName3", null);

        song1 = new Song("title1", artist1, 2020, 120, "lyrics1", artist1, artist2, null);
        song2 = new Song("title2", artist2, 2021, 180, "lyrics2", artist2, artist1, null);
        song3 = new Song("title3", artist3, 2022, 60, "lyrics3", artist3, artist3, null);

        playlist = new Playlist("Test Playlist");
        playlist.addMedia(song1);
        playlist.addMedia(song2);
    }

    /**
     * Tests the addMedia method to ensure a media is added correctly.
     */
    @Test
    void addMedia_OK() {
        // WHEN
        playlist.addMedia(song3);

        // THEN
        assertTrue(playlist.getMedias().contains(song1));
        assertEquals(3, playlist.getMedias().size());
        assertEquals(360, playlist.getTotalDuration());
    }

    /**
     * Tests the removeMedia method to remove a single media.
     */
    @Test
    void removeMedia_OneOK() {
        // WHEN
        playlist.removeMedia(song1);

        // THEN
        assertFalse(playlist.getMedias().contains(song1));
        assertTrue(playlist.getMedias().contains(song2));
        assertEquals(180, playlist.getTotalDuration());
    }

    /**
     * Tests the removeMedia method when multiple instances of the same media exist.
     */
    @Test
    void removeMedia_MultipleSong1_OK() {
        // GIVEN
        playlist.addMedia(song1);
        playlist.addMedia(song1);

        // WHEN
        boolean res = playlist.removeMedia(song1);

        // THEN
        assertTrue(res);
        assertFalse(playlist.getMedias().contains(song1));
        assertTrue(playlist.getMedias().contains(song2));
        assertEquals(180, playlist.getTotalDuration());
    }

    /**
     * Tests the removeMedia method when the media to remove is not in the playlist.
     */
    @Test
    void removeMedia_NotInPlaylist() {
        // WHEN
        boolean res = playlist.removeMedia(song3);

        // THEN
        assertFalse(res);
        assertFalse(playlist.getMedias().contains(song3));
        assertEquals(300, playlist.getTotalDuration());
    }

}