package org.compsci.ia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        MediaManager manager = new MediaManager(library);
        manager.loadFromFile();

        JFrame frame = new JFrame("Media Tracker");
        frame.setSize(1000,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel topPanel = new JPanel(new BorderLayout());

        JLabel welcomeText = new JLabel("Welcome to Media Tracker!");
        welcomeText.setFont(new Font("SansSerif", Font.BOLD, 25));
        welcomeText.setHorizontalAlignment(JLabel.CENTER);
        welcomeText.setVerticalAlignment(JLabel.TOP);
        welcomeText.setBorder(new EmptyBorder(50, 0, 0, 0));
        Color darkAzure = new Color(6,86,135);
        welcomeText.setForeground(darkAzure);
        topPanel.add(welcomeText, BorderLayout.NORTH);

        JButton plusButton = new JButton("+");
        plusButton.setFont(new Font("SansSerif", Font.BOLD, 25));
        plusButton.setForeground(darkAzure);
        plusButton.setBackground(Color.white);
        plusButton.setFocusPainted(false);
        topPanel.add(plusButton, BorderLayout.EAST);
        //TODO make the button open a frame to add stuff
        frame.add(topPanel, BorderLayout.NORTH);


        JTextField searchBar = new JTextField(30);
        searchBar.setForeground(darkAzure);
        searchBar.setFont(new Font("SansSerif", Font.PLAIN, 18));
        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
        searchButton.setForeground(darkAzure);
        searchButton.setBackground(Color.white);
        searchButton.setFocusPainted(false);
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        //TODO add the search function when the search is pressed

        JPanel sortingMenuPanel = new JPanel(new FlowLayout());
        JLabel sortingLabel = new JLabel("Sort by:");
        JComboBox<String> sortingMenu = new JComboBox<>(new String[] {
                "Alphabetically",
                "Reverse Alphabetically",
                "Rating",
                "Release Year",
                "Completion"
        });
        sortingMenuPanel.add(sortingLabel);
        sortingMenuPanel.add(sortingMenu);
        //TODO make the sorting menu actually do something

        JPanel mediasPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        ArrayList<Media> medias = manager.getAllMedia();
        for (Media media : medias) {
            JPanel mediaPanel = new JPanel();
            mediaPanel.setLayout(new BoxLayout(mediaPanel, BoxLayout.Y_AXIS));
            mediaPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            mediaPanel.setPreferredSize(new Dimension(250,400));

            JLabel title = new JLabel(media.getTitle());
            title.setForeground(darkAzure);
            title.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel releaseYear = new JLabel("Release Year: " + String.valueOf(media.getReleaseYear()));
            releaseYear.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel genre = new JLabel("Genres: " + media.getGenre());
            genre.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel rating = new JLabel("Rating: " + String.valueOf(media.getRating()));
            rating.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel completion = new JLabel("Completed: " + media.isCompleted());
            completion.setAlignmentX(Component.CENTER_ALIGNMENT);

            JTextArea notes = new JTextArea(media.getNotes());
            notes.setAlignmentX(Component.CENTER_ALIGNMENT);
            notes.setEditable(false);
            notes.setLineWrap(true);
            notes.setWrapStyleWord(true);
            notes.setOpaque(false);
            JScrollPane scrollNotes = new JScrollPane(notes);
            scrollNotes.setPreferredSize(new Dimension(180, 60));

            JPanel buttonPanel = new JPanel();
            JButton edit = new JButton("Edit");
            edit.setForeground(darkAzure);
            edit.setBackground(Color.white);
            edit.setFocusPainted(false);
            JButton delete = new JButton("Delete");
            delete.setForeground(darkAzure);
            delete.setBackground(Color.white);
            delete.setFocusPainted(false);
            buttonPanel.add(edit);
            buttonPanel.add(delete);
            //TODO make the edit and delete buttons do something

            mediaPanel.add(title);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(releaseYear);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(genre);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(rating);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(completion);
            mediaPanel.add(Box.createVerticalStrut(5));
            if (media.getClass().equals(Anime.class)) {
                JLabel episode = new JLabel("Episodes: " + ((Anime) media).getEpisodeProgress() + "/" + ((Anime) media).getTotalEpisodes() );
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                
            }
            mediaPanel.add(scrollNotes);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(buttonPanel);
            mediasPanel.add(mediaPanel);
        }

        JScrollPane scrollPane = new JScrollPane(mediasPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(searchPanel);
        centerPanel.add(sortingMenuPanel);
        centerPanel.add(scrollPane);

        frame.add(centerPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}