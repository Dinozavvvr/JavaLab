package ru.itis.images_downloader.utils;

import com.beust.jcommander.IStringConverter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class UrlConverter implements IStringConverter<List<URL>> {
    @Override
    public List<URL> convert(String s) {
        String[] values = s.split(";");
        List<URL> urls = new ArrayList<>();
        for (String value : values) {
            try {
                urls.add(new URL(value));
            } catch (MalformedURLException e) {
                System.out.println("Download error. Link : " + value);
            }
        }
        return urls;
    }
}
