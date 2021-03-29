package ru.itis.javalab.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.javalab.utils.Media;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * created: 21-02-2021 - 17:06
 * project: SemesterWork
 *
 * @author dinar
 * @version v0.1
 */
@Controller
public class MediaController {

    @Value("${media.images}")
    private String imagesPath;

    @GetMapping("/media")
    public void getImage(HttpServletRequest request,
                         HttpServletResponse response) {

        response.setContentType("image/jpeg");

        String path = request.getParameter("path");
        File file = new File( imagesPath + path + ".jpg");

        try (InputStream inputStream = new FileInputStream(file)) {
            response.getOutputStream().write(inputStream.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping(
            value = "/media",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody byte[] getImageWithMediaType(@RequestParam String name) throws IOException {
        InputStream inputStream =
                new FileInputStream(new File(imagesPath + name + Media.JPEG.value));
        return IOUtils.toByteArray(inputStream);
    }

}
