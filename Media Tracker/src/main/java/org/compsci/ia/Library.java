package org.compsci.ia;

import java.util.ArrayList;

/**
 * Stores all the media the user is tracking
 */
public class Library {
    private ArrayList<Media> medias;
    public Library() {
        medias = new ArrayList<>();
    }
    public ArrayList<Media> getMedias() {
        return medias;
    }
}
