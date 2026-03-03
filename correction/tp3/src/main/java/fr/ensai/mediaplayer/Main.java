package fr.ensai.mediaplayer;

import java.util.List;

public class Main {
        public static void main(String[] args) {
                String lyrics = "If you wanna run away with me, I know a galaxy\n" +
                                "And I can take you for a ride\n" +
                                "I had a premonition that we fell into a rhythm\n" +
                                "Where the music don't stop for life\n" +
                                "Glitter in the sky, glitter in my eyes\n" +
                                "Shining just the way I like\n" +
                                "If you're feeling like you need a little bit of company\n" +
                                "You met me at the perfect time\n" +
                                "You want me, I want you, baby\n" +
                                "My sugarboo, I'm levitating\n" +
                                "The Milky Way, we're renegading\n" +
                                "Yeah, yeah, yeah, yeah, yeah\n" +
                                "I got you, moonlight, you're my starlight\n" +
                                "I need you all night, come on, dance with me\n" +
                                "I'm levitating\n" +
                                "You, moonlight, you're my starlight (you're the moonlight)\n" +
                                "I need you all night, come on, dance with me\n" +
                                "I'm levitating";

                Artist duaLipa = new Artist("Dua", "Lipa", null);
                Artist stephenKozmeniuk = new Artist("Kozmeniuk", "Stephen", null);

                Song levitating = new Song(
                                "Levitating",
                                duaLipa,
                                2020,
                                207,
                                lyrics,
                                duaLipa,
                                stephenKozmeniuk,
                                List.of(MusicalGenre.POP, MusicalGenre.ELECTRONIC));

                // levitating.play();

                Song jump = new Song(
                                "Jump",
                                new Artist("Madonna", "Ciccone", "American"),
                                2005,
                                240,
                                "There's only so much you can learn in one place\n" +
                                                "The more that I wait, the more time that I waste\n" +
                                                "I haven't got much time to waste, it's time to make my way\n" +
                                                "I'm not afraid of what I'll face, but I'm afraid to stay\n" +
                                                "I'm going down my own road and I can make it alone\n" +
                                                "All work and no fighting, I'll find a place of my own\n" +
                                                "Are you ready to jump?\n" +
                                                "Get ready to jump\n" +
                                                "Don't ever look back, oh baby\n" +
                                                "Yes, I'm ready to jump\n" +
                                                "Just take my hands\n" +
                                                "Get ready to jump",
                                null,
                                null,
                                List.of(MusicalGenre.POP, MusicalGenre.DISCO));

                Podcast wangariPodcast = new Podcast(
                                "Wangari Maathai: The Tree Champion",
                                "Eco Heroes",
                                "Environmental Activism",
                                2023,
                                600,
                                "Welcome to Eco Heroes.\nToday, we honor Wangari Maathai, a Kenyan environmentalist.\nShe founded the Green Belt Movement, planting millions of trees.\nHer work fought deforestation and empowered women.\nMaathai won the Nobel Peace Prize for her efforts.\nShe proved that environmental protection and peace go hand in hand.\nHer legacy inspires us to protect our planet.\nThrough her courage, she showed the world that one person can make a difference.\nPlant trees, empower communities, and honor Wangari Maathai's vision.");

                Playlist p1 = new Playlist("p1");
                p1.addMedia(levitating);
                p1.addMedia(jump);
                p1.addMedia(wangariPodcast);

                Playlist p2 = new Playlist(p1);

                p1.removeMedia(jump);

                System.out.println("p1 duration : " + p1.getTotalDuration());
                System.out.println("p2 duration : " + p2.getTotalDuration());

                p2.play(false);
        }
}