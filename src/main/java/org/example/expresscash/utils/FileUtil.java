package org.example.expresscash.utils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.example.expresscash.constants.StatusCodeEnum;
import org.example.expresscash.exceptions.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@UtilityClass
@Slf4j
public class FileUtil {

    private static String UPLOAD_DIR = "uploads/images/";
    public static String saveFile(String username, String base64Image) {
        try {
            // 1. Extract image file extension from Base64 string
            String fileExtension = base64Image.split(";")[0].split("/")[1];  // "png", "jpg", etc.

            // 2. Clean the Base64 string by removing the metadata part
            String imageData = base64Image.split(",")[1];

            // 3. Decode the Base64 string into bytes using java.util.Base64
            byte[] imageBytes = Base64.getDecoder().decode(imageData);

            // 4. Generate a unique file name (using username and file extension)
            String fileName = username + "." + fileExtension; //

            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();  // Create the directory if it doesn't exist
            }// e.g., "testuser.png"

            // 5. Define the target file path to save the image
            Path targetPath = Paths.get(UPLOAD_DIR, fileName);

            // 6. Save the image to the server
            try (FileOutputStream fos = new FileOutputStream(targetPath.toFile())) {
                fos.write(imageBytes);
            }

            // 7. Generate the image URL (you can serve it as static content or using a web server)
            return "/images/" + fileName;  // URL format: /images/username.png
        } catch (IOException e) {
            log.error("ERROR {}", e);
            throw new BusinessException(StatusCodeEnum.GENERAL_ERROR, "Image Not Valid");
        }
    }
}
