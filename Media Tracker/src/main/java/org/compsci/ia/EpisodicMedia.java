package org.compsci.ia;

public class EpisodicMedia extends Media{
    protected int totalEpisodes;
    protected int episodeDuration;

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public int getEpisodeDuration() {
        return episodeDuration;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public void setEpisodeDuration(int episodeDuration) {
        this.episodeDuration = episodeDuration;
    }
}
