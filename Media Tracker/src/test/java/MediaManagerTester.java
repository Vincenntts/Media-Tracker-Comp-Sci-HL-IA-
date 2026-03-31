import org.compsci.ia.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MediaManagerTester {
    @Test
    void testLoadingFiles() {
        Library library = new Library();
        MediaManager manager = new MediaManager(library);
        manager.loadFromFile();
        ArrayList<Media> medias = library.getMedias();
        for (Media media : medias) {
            System.out.println(media.getTitle());
            System.out.println(media.getReleaseYear());
            System.out.println(media.getRating());
            System.out.println(media.getGenre());
            System.out.println(media.getNotes());
            System.out.println(media.isCompleted());
            System.out.println("------------------------");
        }
        Assertions.assertEquals("Rush Hour 2", medias.get(1).getTitle());
        Assertions.assertEquals(5, ((Show)medias.get(0)).getSeasons());
    }
}
