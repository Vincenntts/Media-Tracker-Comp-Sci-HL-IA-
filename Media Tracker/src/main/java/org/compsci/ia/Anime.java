package org.compsci.ia;

public class Anime extends EpisodicMedia {
    private boolean subbed;
    private boolean dubbed;

    public boolean isSubbed() {
        return subbed;
    }

    public boolean isDubbed() {
        return dubbed;
    }

    public void setDubbed(boolean dubbed) {
        this.dubbed = dubbed;
    }

    public void setSubbed(boolean subbed) {
        this.subbed = subbed;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Anime");
        sb.append(super.toString());
        sb.append(subbed).append("|");
        sb.append(dubbed);
        return sb.toString();
    }
}
