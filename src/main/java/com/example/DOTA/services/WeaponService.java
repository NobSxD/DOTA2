package com.example.DOTA.services;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.models.image.ImageWeapon;
import com.example.DOTA.repository.image.ImageRepositoryWeapon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final ImageRepositoryWeapon imageRepositoryWeapon;

    public  void saveWeapon(Weapon weapon, MultipartFile file1,MultipartFile file2,MultipartFile file3,MultipartFile file4) throws IOException {

        if (file1.getSize() != 0) {
            toImageEntity(file1, weapon);

        }
        if (file2.getSize() != 0) {
            toImageEntity(file2, weapon);

        }
        if (file3.getSize() != 0) {
            toImageEntity(file3, weapon);

        }
        if (file4.getSize() != 0) {
            toImageEntity(file4, weapon);

        }


    }

    private ImageWeapon toImageEntity(MultipartFile file, Weapon weapon) throws IOException {
        ImageWeapon image = new ImageWeapon();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setWeapon(weapon);
        return imageRepositoryWeapon.save(image);
    }
}
