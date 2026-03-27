package org.compsci.ia;

import java.util.ArrayList;

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

    public void loadFromFile() {
        //TODO
    }

    public void saveToFile() {
        //TODO
    }

}
