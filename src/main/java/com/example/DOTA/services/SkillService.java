package com.example.DOTA.services;

import com.example.DOTA.models.image.ImageSkill;
import com.example.DOTA.repository.image.ImageRepositorySkill;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SkillService {
    private final ImageRepositorySkill imageRepositorySkill;

    public void saveSkill(MultipartFile file1, String name, String detals) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntitySave(file1, name, detals);

        }
    }
    public void updateSkill(MultipartFile file1, String name, String detals, Long id) throws IOException {
        if (file1.getSize() != 0) {
            toImageEntityUpdate(file1, name, detals, id);
        }
        ImageSkill imageSkill = skill(id);
        imageSkill.setName(name);
        imageSkill.setDetals(detals);
        imageRepositorySkill.save(imageSkill);
    }
    public List<ImageSkill> listSkill(){
        return imageRepositorySkill.findAll();
    }
    public ImageSkill skill(Long id){
        return imageRepositorySkill.findById(id).orElse(null);

    }
    public long summaSkill(){
        return imageRepositorySkill.count();
    }
    public void deleteSkill(ImageSkill imageSkill){
        imageRepositorySkill.delete(imageSkill);
    }

    private ImageSkill toImageEntitySave(MultipartFile file, String name, String detals) throws IOException {
        ImageSkill image = new ImageSkill();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositorySkill.save(image);
    }
    private ImageSkill toImageEntityUpdate(MultipartFile file, String name, String detals, Long id) throws IOException {
        ImageSkill image = new ImageSkill();
        image.setId(id);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        image.setDetals(detals);
        return imageRepositorySkill.save(image);
    }
}
