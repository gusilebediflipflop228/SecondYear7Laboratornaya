package classes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileExample {

    public static List<Path> findFilesByExtension(String directory, String extension){
        try(Stream<Path> path = Files.walk(Paths.get(directory))) {
            return path
                    .filter(Files::isRegularFile)
                    .filter(file -> file.toString().endsWith("." + extension))//
                    .collect(Collectors.toList());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
