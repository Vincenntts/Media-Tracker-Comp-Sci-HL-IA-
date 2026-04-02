package org.compsci.ia;

public class Show extends EpisodicMedia {
    private int seasons;

    public int getSeasons() {
        return seasons;
    }
    public void setSeasons(int seasons) {
        if (seasons >= 0) {
            // Allowing it to be zero for unknowns.
            this.seasons = seasons;
        } else {
            throw new IllegalArgumentException("Seasons cannot be negative.");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Show");
        sb.append(super.toString());
        sb.append(seasons);
        return sb.toString();
    }
}
