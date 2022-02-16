package helper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileReaderHelper {

    public static String readFileFromResources(final String fileName) throws IOException {
        final URL res = FileReaderHelper.class.getClassLoader().getResource(fileName);
        final Path path;
        try {
            path = Paths.get(res.toURI());
            final String jsonText = Files.readString(path);
            return jsonText;
        } catch (URISyntaxException e) {
            System.out.println("Error while reading file" + fileName + " - Error: " + e.getMessage());
        }
        return null;
    }
}
