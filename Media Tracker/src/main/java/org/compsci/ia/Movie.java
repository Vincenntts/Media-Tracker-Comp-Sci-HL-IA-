package org.compsci.ia;

public class Movie extends Media {
    private int durationMinutes;

    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Movie");
        sb.append(super.toString());
        sb.append(durationMinutes);
        return sb.toString();
    }
}
