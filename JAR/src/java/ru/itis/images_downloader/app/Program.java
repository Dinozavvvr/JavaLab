package ru.itis.images_downloader.app;

import com.beust.jcommander.JCommander;
import ru.itis.images_downloader.utils.ImagesDownloader;

import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Program {

    private static final String ONE_THREAD = "one-thread";
    private static final String MULTI_THREAD = "multi-thread";

    public static void main(String[] argv) {

        Args args = new Args();
        JCommander.newBuilder()
                .addObject(args)
                .build()
                .parse(argv);


        ImagesDownloader imagesDownloader = new ImagesDownloader();
        Path path = args.path;
        String mode = args.mode;
        ArrayList<URL> urls = (ArrayList<URL>) args.urls;

        if (path != null && mode != null) {
            if (mode.equals(ONE_THREAD)) {
                for (URL url : urls) {
                    imagesDownloader.downloadImage(url, path);
                }
            } else if (mode.equals(MULTI_THREAD)) {
                Integer count = args.count;
                if (count != null) {
                    ExecutorService service = Executors.newFixedThreadPool(count);
                    for (URL url : urls) {
                        service.submit(() -> {
                            imagesDownloader.downloadImage(url, path);
                        });
                    }
                }
            }
        }
    }
}
