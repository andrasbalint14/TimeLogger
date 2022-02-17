package helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHelper {
    private final static String DEFAULT_EXTENSION = ".json";

    public static String readFileFromResources(final String fileName) {
        final URL res = FileHelper.class.getClassLoader().getResource(fileName);
        final Path path;
        try {
            path = Paths.get(res.toURI());
            final String jsonText = Files.readString(path);
            return jsonText;
        } catch (URISyntaxException e) {
            System.out.println("Error while reading file: " + fileName + " - Error: " + e.toString());
        } catch (IOException e) {
            System.out.println("Error while reading file: " + fileName + " - Error: " + e.toString());
        }
        return null;
    }

    public static String writeFile(final String fileName, final String content) {
        final Path path = Paths.get(fileName + DEFAULT_EXTENSION);

        try {
            Files.write(path, content.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.out.println("Error while writing file: " + fileName + " - Error: " + e.toString());
        }
        return null;
    }
}
