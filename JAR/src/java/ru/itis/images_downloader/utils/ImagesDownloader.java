package ru.itis.images_downloader.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public class ImagesDownloader  {
    public void downloadImage(URL url, Path path) {
        try (InputStream in = url.openStream()) {
            Random random = new Random();
            Files.copy(in, Paths.get(path + "/someFile_" + random.nextInt(100)+ "" + random.nextInt(1000) + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }
}
