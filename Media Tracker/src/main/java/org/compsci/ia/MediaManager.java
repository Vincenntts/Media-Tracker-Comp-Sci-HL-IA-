package org.compsci.ia;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage and searching for the media tracker
 */
public class MediaManager {
    private Library library;

    public MediaManager(Library library) {
        this.library = library;
    }

    public void addMedia(Media media) {
        library.getMedias().add(media);
    }

    public void removeMedia(Media media) {
        library.getMedias().remove(media);
    }

    public ArrayList<Media> getAllMedia() {
        return library.getMedias();
    }

    /**
     * This loads the data from the data file. It assumes that none of the comments nor
     * the names include the "|" symbol.
     */
    public void loadFromFile() {
        File data = new File("mediaTrackerData.txt");
        try (Scanner in = new Scanner(data)) {
            while (in.hasNextLine()) {
                String[] dataArray = in.nextLine().split("\\|");
                if (dataArray[0].equalsIgnoreCase("ANIME")) {
                    Media media = new Anime();
                    media.setTitle(dataArray[1]);
                    media.setReleaseYear(Integer.parseInt(dataArray[2]));
                    media.setRating(Integer.parseInt(dataArray[3]));
                    media.setGenre(genres(dataArray[4].split(",")));
                    media.setNotes(dataArray[5]);
                    media.setCompleted(Boolean.parseBoolean(dataArray[6]));
                    ((Anime)media).setTotalEpisodes(Integer.parseInt(dataArray[7]));
                    ((Anime)media).setEpisodeProgress(Integer.parseInt(dataArray[8]));
                    ((Anime)media).setSubbed(Boolean.parseBoolean(dataArray[9]));
                    ((Anime)media).setDubbed(Boolean.parseBoolean(dataArray[10]));
                    addMedia(media);
                } else if (dataArray[0].equalsIgnoreCase("CARTOON")) {
                    Media media = new Cartoon();
                    media.setTitle(dataArray[1]);
                    media.setReleaseYear(Integer.parseInt(dataArray[2]));
                    media.setRating(Integer.parseInt(dataArray[3]));
                    media.setGenre(genres(dataArray[4].split(",")));
                    media.setNotes(dataArray[5]);
                    media.setCompleted(Boolean.parseBoolean(dataArray[6]));
                    ((Cartoon)media).setTotalEpisodes(Integer.parseInt(dataArray[7]));
                    ((Cartoon)media).setEpisodeProgress(Integer.parseInt(dataArray[8]));
                    ((Cartoon)media).setAgeRating(dataArray[9]);
                    addMedia(media);
                } else if (dataArray[0].equalsIgnoreCase("MOVIE")) {
                    Media media = new Movie();
                    media.setTitle(dataArray[1]);
                    media.setReleaseYear(Integer.parseInt(dataArray[2]));
                    media.setRating(Integer.parseInt(dataArray[3]));
                    media.setGenre(genres(dataArray[4].split(",")));
                    media.setNotes(dataArray[5]);
                    media.setCompleted(Boolean.parseBoolean(dataArray[6]));
                    ((Movie)media).setDurationMinutes(Integer.parseInt(dataArray[7]));
                    addMedia(media);
                } else if (dataArray[0].equalsIgnoreCase("SHOW")) {
                    Media media = new Show();
                    media.setTitle(dataArray[1]);
                    media.setReleaseYear(Integer.parseInt(dataArray[2]));
                    media.setRating(Integer.parseInt(dataArray[3]));
                    media.setGenre(genres(dataArray[4].split(",")));
                    media.setNotes(dataArray[5]);
                    media.setCompleted(Boolean.parseBoolean(dataArray[6]));
                    ((Show)media).setTotalEpisodes(Integer.parseInt(dataArray[7]));
                    ((Show)media).setEpisodeProgress(Integer.parseInt(dataArray[8]));
                    ((Show)media).setSeasons(Integer.parseInt(dataArray[9]));
                    addMedia(media);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (FileNotFoundException | IllegalArgumentException e) {
           System.exit(0);
        }

    }

    /**
     * Sorts the shows from the highest rating to lowest. If a show has the
     * same rating, it will be sorted alphabetically.
     */
    public void sortByRating() {
        //TODO
    }
    /**
     * Sorts the shows in alphabetical order.
     */
    public void sortByAlphabeticalOrder() {
        //TODO
    }
    /**
     * Sorts the shows in reverse alphabetical order.
     */
    public void sortByReverseAlphabeticalOrder() {
        //TODO
    }
    /**
     * Sorts the show from the most recently released to the oldest.
     */
    public void sortByReleaseYear() {
        //TODO
    }
    /**
     * Sorts the shows by whether the user have completed them or not.
     * The uncompleted shows will be on the top and the completed shows
     * will be on the bottom, as that will show the user what they still
     * need to watch on their list. The completed show will be sorted
     * from A-Z and so will the uncompleted shows.
     */
    public void sortByCompletion() {
        //TODO
    }



    public void saveToFile() {
        //TODO
    }

    private ArrayList<Genre> genres(String[] data) {
        ArrayList<Genre> genres = new ArrayList<>();
        for (String d : data) {
            if (d.equalsIgnoreCase("ACTION")) {
                genres.add(Genre.ACTION);
            } else if (d.equalsIgnoreCase("ADVENTURE")) {
                genres.add(Genre.ADVENTURE);
            } else if (d.equalsIgnoreCase("BIOGRAPHY")) {
                genres.add(Genre.BIOGRAPHY);
            } else if (d.equalsIgnoreCase("COMEDY")) {
                genres.add(Genre.COMEDY);
            } else if (d.equalsIgnoreCase("CRIME")) {
                genres.add(Genre.CRIME);
            } else if (d.equalsIgnoreCase("DOCUMENTARY")) {
                genres.add(Genre.DOCUMENTARY);
            } else if (d.equalsIgnoreCase("DRAMA")) {
                genres.add(Genre.DRAMA);
            } else if (d.equalsIgnoreCase("FANTASY")) {
                genres.add(Genre.FANTASY);
            } else if (d.equalsIgnoreCase("GEOGRAPHY")) {
                genres.add(Genre.GEOGRAPHY);
            } else if (d.equalsIgnoreCase("HISTORICAL")) {
                genres.add(Genre.HISTORICAL);
            } else if (d.equalsIgnoreCase("HORROR")) {
                genres.add(Genre.HORROR);
            } else if (d.equalsIgnoreCase("ISEKAI")) {
                genres.add(Genre.ISEKAI);
            } else if (d.equalsIgnoreCase("MYSTERY")) {
                genres.add(Genre.MYSTERY);
            } else if (d.equalsIgnoreCase("ROMANCE")) {
                genres.add(Genre.ROMANCE);
            } else if (d.equalsIgnoreCase("SCIENCE_FICTION")) {
                genres.add(Genre.SCIENCE_FICTION);
            } else if (d.equalsIgnoreCase("THRILLER")) {
                genres.add(Genre.THRILLER);
            } else if (d.equalsIgnoreCase("WAR")) {
                genres.add(Genre.WAR);
            } else if (d.equalsIgnoreCase("WESTERN")) {
                genres.add(Genre.WESTERN);
            }
        }
        return genres;
    }

}
