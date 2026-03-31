package org.compsci.ia;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //TODO do main
        Library library = new Library();
        MediaManager manager = new MediaManager(library);
        manager.loadFromFile();
        JFrame frame = new JFrame("Media Tracker");
        frame.setSize(1000,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel panel = new JPanel();
    }
}