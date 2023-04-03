package com.example.DOTA.services;

import com.example.DOTA.models.Skill;
import com.example.DOTA.models.image.ImageSkill;
import com.example.DOTA.repository.image.ImageRepositorySkill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkillService {
    private final ImageRepositorySkill imageRepositorySkill;

    public  void saveSkill(Skill skill, MultipartFile file1) throws IOException {

        if (file1.getSize() != 0) {
            toImageEntity(file1, skill);

        }


    }

    private ImageSkill toImageEntity(MultipartFile file, Skill skill) throws IOException {
        ImageSkill image = new ImageSkill();
        image.setName(file.getName());
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setSkill(skill);
        return imageRepositorySkill.save(image);
    }
}
