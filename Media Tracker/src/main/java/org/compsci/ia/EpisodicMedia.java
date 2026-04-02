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
        if (totalEpisodes >= 0) {
            // Allowing it to be zero for when it's not entered or unknown.
            this.totalEpisodes = totalEpisodes;
        } else {
            throw new IllegalArgumentException("Total episodes can't be negative");
        }
    }

    public void setEpisodeProgress(int episodeProgress) {
        if (episodeProgress >= 0) {
            // Allowing it to be zero for when it's not entered or unknown.
            this.episodeProgress = episodeProgress;
        } else {
            throw new IllegalArgumentException("Episode progress can't be negative");
        }
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
