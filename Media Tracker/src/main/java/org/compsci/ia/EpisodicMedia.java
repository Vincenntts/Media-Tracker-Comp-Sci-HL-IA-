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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(totalEpisodes).append("|");
        sb.append(episodeProgress).append("|");
        return sb.toString();
    }
}
