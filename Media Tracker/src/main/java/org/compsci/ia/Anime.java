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
}
