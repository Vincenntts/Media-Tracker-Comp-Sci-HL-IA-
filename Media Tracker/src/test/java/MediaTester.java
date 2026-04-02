import org.compsci.ia.Library;
import org.compsci.ia.Media;
import org.compsci.ia.MediaManager;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class MediaTester {
    @Test
    void testToStrings() {
        Library library = new Library();
        MediaManager manager = new MediaManager(library);
        manager.loadFromFile();
        ArrayList<Media> medias = library.getMedias();
        for (Media media : medias) {
            System.out.println(media.toString());
        }
    }
}
