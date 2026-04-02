package org.compsci.ia;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        MediaManager manager = new MediaManager(library);
        manager.loadFromFile("mediaTrackerData.txt");

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
                JLabel episode = new JLabel("Episodes: " + ((Anime) media).getEpisodeProgress() + "/" + ((Anime) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel subbed = new JLabel("Subbed: " + ((Anime) media).isSubbed());
                subbed.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel dubbed = new JLabel("Dubbed: " + ((Anime) media).isDubbed());
                dubbed.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(subbed);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(dubbed);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Cartoon.class)) {
                JLabel episode = new JLabel("Episodes: " + ((Cartoon) media).getEpisodeProgress() + "/" + ((Cartoon) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel ageRating = new JLabel("Age Rating: " + ((Cartoon) media).getAgeRating());
                ageRating.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(ageRating);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Movie.class)) {
                JLabel duration = new JLabel("Duration: " + ((Movie) media).getDurationMinutes() + " Minutes");
                duration.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(duration);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Show.class)) {
                JLabel episode = new JLabel("Episodes: " + ((Show) media).getEpisodeProgress() + "/" + ((Show) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel seasons = new JLabel("Seasons: " + ((Show) media).getSeasons());
                seasons.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(seasons);
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

        plusButton.addActionListener(e -> {
            JFrame addFrame = new JFrame("Add New Media");
            addFrame.setSize(600, 600);
            JPanel addPanel = new JPanel(new GridLayout(20, 2, 5, 5));

            JRadioButton animeButton = new JRadioButton("Anime");
            JRadioButton cartoonButton = new JRadioButton("Cartoon");
            JRadioButton movieButton = new JRadioButton("Movie");
            JRadioButton showButton = new JRadioButton("Show");

            ButtonGroup mediaGroup = new ButtonGroup();
            mediaGroup.add(animeButton);
            mediaGroup.add(cartoonButton);
            mediaGroup.add(movieButton);
            mediaGroup.add(showButton);
            JPanel mediaTypePanel = new JPanel();
            mediaTypePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
            mediaTypePanel.add(animeButton);
            mediaTypePanel.add(cartoonButton);
            mediaTypePanel.add(movieButton);
            mediaTypePanel.add(showButton);
            addPanel.add(new JLabel("Media Type (Required):"));
            addPanel.add(mediaTypePanel);

            JTextField titleField = new JTextField();
            JTextField yearField = new JTextField();
            JPanel genrePanel = new JPanel(new GridLayout(0, 2, 5, 5));
            Map<Genre, JCheckBox> genreMap = new HashMap<>();
            for (Genre genre : Genre.values()) {
                JCheckBox checkBox = new JCheckBox(genre.toString());
                genrePanel.add(checkBox);
                genreMap.put(genre, checkBox);
            }
            JScrollPane genreScroller = new JScrollPane(genrePanel);
            genreScroller.getVerticalScrollBar().setUnitIncrement(2);
            JTextField ratingField = new JTextField();
            JTextField notesField = new JTextField();
            JCheckBox completedBox = new JCheckBox("Completed");
            JTextField totalEpisodeField = new JTextField();
            JTextField episodeProgressField = new JTextField();
            JCheckBox dubbedBox = new JCheckBox("Dubbed");
            JCheckBox subbedBox = new JCheckBox("Subbed");
            JTextField ageRatingField = new JTextField();
            JTextField seasonField = new JTextField();
            JTextField durationField = new JTextField();

            addPanel.add(new JLabel("Title (Required):"));
            addPanel.add(titleField);
            addPanel.add(new JLabel("Release Year (Optional):"));
            addPanel.add(yearField);
            addPanel.add(new JLabel("Genre (Optional):"));
            addPanel.add(genreScroller);
            addPanel.add(new JLabel("Rating (Optional):"));
            addPanel.add(ratingField);
            addPanel.add(new JLabel("Notes (Optional):"));
            addPanel.add(notesField);
            addPanel.add(new JLabel("Completed:"));
            addPanel.add(completedBox);

            addPanel.add(new JLabel("For Anime/Cartoon/Show Only:"));
            addPanel.add(new JLabel(""));
            addPanel.add(new JLabel("Total Episodes (Optional):"));
            addPanel.add(totalEpisodeField);
            addPanel.add(new JLabel("Episode Progress (Optional):"));
            addPanel.add(episodeProgressField);
            addPanel.add(new JLabel("For Anime Only:"));
            addPanel.add(new JLabel(""));
            addPanel.add(new JLabel("Dubbed:"));
            addPanel.add(dubbedBox);
            addPanel.add(new JLabel("Subbed:"));
            addPanel.add(subbedBox);
            addPanel.add(new JLabel("For Cartoon Only:"));
            addPanel.add(new JLabel(""));
            addPanel.add(new JLabel("Age Rating (Optional):"));
            addPanel.add(ageRatingField);
            addPanel.add(new JLabel("For Movie Only:"));
            addPanel.add(new JLabel(""));
            addPanel.add(new JLabel("Duration in Minutes (Optional):"));
            addPanel.add(durationField);
            addPanel.add(new JLabel("For Show Only:"));
            addPanel.add(new JLabel(""));
            addPanel.add(new JLabel("Seasons (Optional):"));
            addPanel.add(seasonField);
            JButton saveButton = new JButton("Save");

            addPanel.add(saveButton);

            titleField.setPreferredSize(new Dimension(200, 25));
            yearField.setPreferredSize(new Dimension(200, 25));
            genreScroller.setPreferredSize(new Dimension(200, 100));
            ratingField.setPreferredSize(new Dimension(200, 25));
            notesField.setPreferredSize(new Dimension(200, 25));
            totalEpisodeField.setPreferredSize(new Dimension(200, 25));
            episodeProgressField.setPreferredSize(new Dimension(200, 25));
            ageRatingField.setPreferredSize(new Dimension(200, 25));
            durationField.setPreferredSize(new Dimension(200, 25));
            seasonField.setPreferredSize(new Dimension(200, 25));

            JScrollPane addScrollPane = new JScrollPane(addPanel);
            addScrollPane.getVerticalScrollBar().setUnitIncrement(20);
            addFrame.add(addScrollPane);

            saveButton.addActionListener(ev -> {
                try {
                    Media media;
                    if (animeButton.isSelected()) {
                        media = new Anime();
                    } else if (movieButton.isSelected()) {
                        media = new Movie();
                    } else if (showButton.isSelected()) {
                        media = new Show();
                    } else if (cartoonButton.isSelected()) {
                        media = new Cartoon();
                    } else {
                        throw new IllegalArgumentException("Selection required for media type");
                    }
                    if (!titleField.getText().trim().isEmpty()) {
                        String title = titleField.getText();
                        media.setTitle(title);
                    } else {
                        throw new IllegalArgumentException("Title is required!");
                    }
                    if (!yearField.getText().trim().isEmpty()) {
                        int year = Integer.parseInt(yearField.getText());
                        media.setReleaseYear(year);
                    }
                    if (!ratingField.getText().trim().isEmpty()) {
                        int rating = Integer.parseInt(ratingField.getText());
                        media.setRating(rating);
                    }
                    ArrayList<Genre> genreList = new ArrayList<>();
                    for (Map.Entry<Genre, JCheckBox> entry : genreMap.entrySet()) {
                        if (entry.getValue().isSelected()) {
                            genreList.add(entry.getKey());
                        }
                    }
                    media.setGenre(genreList);
                    if (!notesField.getText().trim().isEmpty()) {
                        String notes = notesField.getText();
                        media.setNotes(notes);
                    }
                    boolean completed = completedBox.isSelected();
                    media.setCompleted(completed);
                    if (!totalEpisodeField.getText().trim().isEmpty() && media instanceof EpisodicMedia) {
                        int totalEpisodes = Integer.parseInt(totalEpisodeField.getText());
                        ((EpisodicMedia) media).setTotalEpisodes(totalEpisodes);
                    }
                    if (!episodeProgressField.getText().trim().isEmpty() && media instanceof EpisodicMedia) {
                        int episodeProgress = Integer.parseInt(episodeProgressField.getText());
                        ((EpisodicMedia) media).setEpisodeProgress(episodeProgress);
                    }
                    if (media instanceof Anime) {
                        boolean subbed = subbedBox.isSelected();
                        boolean dubbed = dubbedBox.isSelected();
                        ((Anime) media).setSubbed(subbed);
                        ((Anime) media).setDubbed(dubbed);
                    }
                    if (media instanceof Cartoon) {
                        if (!ageRatingField.getText().trim().isEmpty()) {
                            String ageRating = ageRatingField.getText();
                            ((Cartoon) media).setAgeRating(ageRating);
                        }
                    }
                    if (media instanceof Movie) {
                        if (!durationField.getText().trim().isEmpty()) {
                            int duration = Integer.parseInt(durationField.getText());
                            ((Movie) media).setDurationMinutes(duration);
                        }
                    }
                    if (media instanceof Show) {
                        if (!seasonField.getText().trim().isEmpty()) {
                            int season = Integer.parseInt(seasonField.getText());
                            ((Show) media).setSeasons(season);
                        }
                    }
                    manager.addMedia(media);
                    manager.saveToFile("mediaTrackerData.txt");
                    addFrame.dispose();

                    refresh(mediasPanel, medias, darkAzure);

                    JDialog successDialog = new JDialog(frame, "Success!", true);
                    successDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    successDialog.setSize(200, 100);
                    successDialog.add(new JLabel("Sucessfully added media!"));
                    successDialog.setVisible(true);
                } catch (Exception exception) {
                    addFrame.dispose();

                    JDialog successDialog = new JDialog(frame, "Error!", true);
                    successDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    successDialog.setSize(350, 100);
                    successDialog.add(new JLabel("Something went wrong! Please check your inputs!"));
                    successDialog.setVisible(true);
                }

            });

            addFrame.setVisible(true);
        });

        searchButton.addActionListener(e -> {
            String searchText = searchBar.getText().trim();

            if (!searchText.isEmpty()) {
                ArrayList<Media> filteredList = new ArrayList<>();
                for (Media media : medias) {
                    if (media.getTitle().toLowerCase().contains(searchText.toLowerCase())) {
                        filteredList.add(media);
                    }
                }
                refresh(mediasPanel, filteredList, darkAzure);
            } else {
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            }
        });

        sortingMenu.addActionListener(e -> {
            if (sortingMenu.getSelectedIndex() == 0) {
                manager.sortByAlphabeticalOrder();
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            } else if (sortingMenu.getSelectedIndex() == 1) {
                manager.sortByReverseAlphabeticalOrder();
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            } else if (sortingMenu.getSelectedIndex() == 2) {
                manager.sortByRating();
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            } else if (sortingMenu.getSelectedIndex() == 3) {
                manager.sortByReleaseYear();
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            } else if (sortingMenu.getSelectedIndex() == 4) {
                manager.sortByCompletion();
                refresh(mediasPanel, manager.getAllMedia(), darkAzure);
            }
        });

    }
    public static void refresh(JPanel mediasPanel, ArrayList<Media> medias, Color darkAzure) {
        mediasPanel.removeAll();
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
                JLabel episode = new JLabel("Episodes: " + ((Anime) media).getEpisodeProgress() + "/" + ((Anime) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel subbed = new JLabel("Subbed: " + ((Anime) media).isSubbed());
                subbed.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel dubbed = new JLabel("Dubbed: " + ((Anime) media).isDubbed());
                dubbed.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(subbed);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(dubbed);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Cartoon.class)) {
                JLabel episode = new JLabel("Episodes: " + ((Cartoon) media).getEpisodeProgress() + "/" + ((Cartoon) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel ageRating = new JLabel("Age Rating: " + ((Cartoon) media).getAgeRating());
                ageRating.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(ageRating);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Movie.class)) {
                JLabel duration = new JLabel("Duration: " + ((Movie) media).getDurationMinutes() + " Minutes");
                duration.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(duration);
                mediaPanel.add(Box.createVerticalStrut(5));
            } else if (media.getClass().equals(Show.class)) {
                JLabel episode = new JLabel("Episodes: " + ((Show) media).getEpisodeProgress() + "/" + ((Show) media).getTotalEpisodes());
                episode.setAlignmentX(Component.CENTER_ALIGNMENT);
                JLabel seasons = new JLabel("Seasons: " + ((Show) media).getSeasons());
                seasons.setAlignmentX(Component.CENTER_ALIGNMENT);
                mediaPanel.add(episode);
                mediaPanel.add(Box.createVerticalStrut(5));
                mediaPanel.add(seasons);
                mediaPanel.add(Box.createVerticalStrut(5));
            }
            mediaPanel.add(scrollNotes);
            mediaPanel.add(Box.createVerticalStrut(5));
            mediaPanel.add(buttonPanel);
            mediasPanel.add(mediaPanel);
        }
        mediasPanel.revalidate();
        mediasPanel.repaint();
    }
}