package org.compsci.ia;

public class Show extends EpisodicMedia {
    private int seasons;

    public int getSeasons() {
        return seasons;
    }
    public void setSeasons(int seasons) {
        this.seasons = seasons;
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
