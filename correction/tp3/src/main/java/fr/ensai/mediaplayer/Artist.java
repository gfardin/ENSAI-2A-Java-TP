package fr.ensai.mediaplayer;

import java.util.Objects;

/**
 * Represents an artist.
 */
public class Artist {
    private String firstName;
    private String lastName;
    private String nationality;

    /**
     * Constructs a new Artist object.
     *
     * @param firstName   The first name of the artist.
     * @param lastName    The last name of the artist.
     * @param nationality The nationality of the artist.
     */
    public Artist(String firstName, String lastName, String nationality) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationality = nationality;
    }

    /**
     * Returns the full name of the artist.
     *
     * @return The first name followed by last name.
     */
    public String toString() {
        return "Artist " + firstName + " " + lastName;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * Two Artist objects are considered equal if their first name, last name, and
     * nationality are the same.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Artist otherArtist = (Artist) o;
        return Objects.equals(this.firstName, otherArtist.firstName) &&
                Objects.equals(this.lastName, otherArtist.lastName) &&
                Objects.equals(this.nationality, otherArtist.nationality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstName, this.lastName, this.nationality);
    }

}