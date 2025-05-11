import classes.FileExample;
import org.junit.Test;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileExampleTest {

    @Test
    public void check(){
        List<Path> answer = new ArrayList<>();
        answer = FileExample.findFilesByExtension("C:\\Users\\1\\прога\\7laba\\7laba\\4testTXT","txt");
        System.out.println(answer);
    }

}
