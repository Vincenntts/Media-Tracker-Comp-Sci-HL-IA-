package org.compsci.ia;

public class Movie extends Media {
    private int durationMinutes;

    public int getDurationMinutes() {
        return durationMinutes;
    }
    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes >= 0) {
            // Allowing it to be zero for when total duration is unknown.
            this.durationMinutes = durationMinutes;
        } else {
            throw new IllegalArgumentException("Total duration cannot be negative.");
        }

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
