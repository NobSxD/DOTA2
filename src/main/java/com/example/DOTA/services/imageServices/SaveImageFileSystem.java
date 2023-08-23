package com.example.DOTA.services.imageServices;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class SaveImageFileSystem {




    public static String multiple(MultipartFile multipartFile, String uploads) throws IOException {
        String resultFileName = "";
        if (multipartFile != null && !multipartFile.isEmpty()){
            File uploadDir = new File(uploads);
            if (!uploadDir.exists()){
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            resultFileName = uuidFile + "." + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploads + "/" + resultFileName));
        }
        return resultFileName;
    }

    public static void delete(String filename, String uploads) {
        File file = new File(uploads + "/" + filename);
        if (file.delete()){
            System.out.printf("файл: "  + filename + " удален ");
        } else System.out.printf("файл не найден");

    }


}
