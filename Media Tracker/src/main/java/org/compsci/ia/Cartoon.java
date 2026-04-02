package org.compsci.ia;

public class Cartoon extends EpisodicMedia {
    private String ageRating;

    public String getAgeRating() {
        return ageRating;
    }
    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cartoon");
        sb.append(super.toString());
        sb.append(ageRating);
        return sb.toString();
    }
}
