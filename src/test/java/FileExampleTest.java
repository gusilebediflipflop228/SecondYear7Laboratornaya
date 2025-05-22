import classes.FileExample;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileExampleTest {

    @Test
    public void check() {
        List<Path> answer = FileExample.findFilesByExtension("C:\\Users\\1\\прога\\7laba\\7laba\\4testTXT", "txt");
        List<Path> expected = new ArrayList<>();
        expected.add(Path.of("C:\\Users\\1\\прога\\7laba\\7laba\\4testTXT\\1.txt"));
        expected.add(Path.of("C:\\Users\\1\\прога\\7laba\\7laba\\4testTXT\\2.txt"));
        expected.add(Path.of("C:\\Users\\1\\прога\\7laba\\7laba\\4testTXT\\3.txt"));
        assertEquals(expected, answer);
    }

}
