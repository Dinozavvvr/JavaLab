package ru.itis.javalab;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;
import java.util.UUID;

public class ImagesDownloader  {
    public void downloadImage(URL url, Path path) {
        try (InputStream in = url.openStream()) {
            Files.copy(in, Paths.get(path + "/someFile_" + UUID.randomUUID().toString() + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
