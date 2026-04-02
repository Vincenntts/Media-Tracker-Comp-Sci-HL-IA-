package org.compsci.ia;

import java.util.ArrayList;

public class Media {
    protected String title;
    protected int releaseYear;
    protected int rating;
    protected ArrayList<Genre> genre;
    protected String notes;
    protected boolean completed;

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public int getRating() {
        return rating;
    }

    public ArrayList<Genre> getGenre() {
        return genre;
    }

    public String getNotes() {
        return notes;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setRating(int rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("The rating must be between 0 and 10");
        }
        this.rating = rating;
    }

    public void setGenre(ArrayList<Genre> genre) {
        this.genre = genre;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        sb.append(this.title).append("|");
        sb.append(this.releaseYear).append("|");
        sb.append(this.rating).append("|");
        ArrayList<Genre> genre = this.genre;
        for (Genre g : genre) {
            sb.append(g).append(",");
        }
        sb.append("|");
        sb.append(this.notes).append("|");
        sb.append(this.completed).append("|");
        return sb.toString();
    }
}
