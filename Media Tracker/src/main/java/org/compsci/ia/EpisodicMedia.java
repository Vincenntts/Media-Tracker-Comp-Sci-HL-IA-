package org.compsci.ia;

public class EpisodicMedia extends Media{
    protected int totalEpisodes;
    protected int episodeProgress;

    public int getTotalEpisodes() {
        return totalEpisodes;
    }

    public int getEpisodeProgress() {
        return episodeProgress;
    }

    public void setTotalEpisodes(int totalEpisodes) {
        this.totalEpisodes = totalEpisodes;
    }

    public void setEpisodeProgress(int episodeProgress) {
        this.episodeProgress = episodeProgress;
    }
}
