package com.example.DOTA.services;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.models.image.ImageWeapon;
import com.example.DOTA.repository.WeaponRepository;
import com.example.DOTA.repository.image.ImageRepositoryWeapon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final ImageRepositoryWeapon imageRepositoryWeapon;
    private final WeaponRepository weaponRepository;



    public ImageWeapon imageById(Long id){
        return imageRepositoryWeapon.findById(id).orElse(null);
    }
    public List<Weapon> weaponAll(){
        return weaponRepository.findAll();
    }
    public Weapon weaponID(Long id){ return weaponRepository.findById(id).orElse(null);}
    public Long summaItems(){
        return weaponRepository.count();
    }

    public boolean weaponExistById(Long id) {
        return weaponRepository.existsById(id);
    }
    public boolean imageWeaponExistById(Long id) {
        return imageRepositoryWeapon.existsById(id);
    }

    public void saveWeapon(String weapon, String tir, String fulText, String items1,String items2,String items3, MultipartFile file1) throws IOException {

        Weapon items = new Weapon();
        if (tir.equals("ТИР 1") || tir.equals("ТИР 2") || tir.equals("ТИР 3") || tir.equals("ТИР 4") || tir.equals("ТИР 5")) {


            List<ImageWeapon> imageWeapon1 = imageRepositoryWeapon.findByName(items1);
            List<ImageWeapon> imageWeapon2 = imageRepositoryWeapon.findByName(items2);
            List<ImageWeapon> imageWeapon3 = imageRepositoryWeapon.findByName(items3);
            ImageWeapon imageWeapon = new ImageWeapon();
            if (imageWeapon1.size() > 0) {
                items.setItems1(imageWeapon1.get(0).getId());
            }
            if (imageWeapon2.size() > 0) {
                items.setItems2(imageWeapon2.get(0).getId());
            }
            if (imageWeapon3.size() > 0) {
                items.setItems3(imageWeapon3.get(0).getId());
            }
            if (file1.getSize() != 0) {
              imageWeapon =  toImageEntity(file1);
              imageWeapon.setName(weapon);
              imageRepositoryWeapon.save(imageWeapon);
            }

            items.setName(weapon);
            items.setTirWeapon(tir);
            items.setFullText(fulText);
            items.setIcon(imageWeapon);

            weaponRepository.save(items);
        }
    }


    public void editWeapon(String weapon, String tir, String fulText, String items1,String items2,String items3, MultipartFile file1, Long id) throws IOException {

        Weapon items = new Weapon();
        if (tir.equals("ТИР 1") || tir.equals("ТИР 2") || tir.equals("ТИР 3") || tir.equals("ТИР 4") || tir.equals("ТИР 5")) {


            List<ImageWeapon> imageWeapon1 = imageRepositoryWeapon.findByName(items1);
            List<ImageWeapon> imageWeapon2 = imageRepositoryWeapon.findByName(items2);
            List<ImageWeapon> imageWeapon3 = imageRepositoryWeapon.findByName(items3);
            ImageWeapon imageWeapon = new ImageWeapon();
            if (imageWeapon1.size() > 0) {
                items.setItems1(imageWeapon1.get(0).getId());
            }
            if (imageWeapon2.size() > 0) {
                items.setItems2(imageWeapon2.get(0).getId());
            }
            if (imageWeapon3.size() > 0) {
                items.setItems3(imageWeapon3.get(0).getId());
            }
            if (file1.getSize() != 0) {
                imageWeapon =  toImageEntity(file1);
                imageWeapon.setName(weapon);

            }
            items.setId(id);
            items.setName(weapon);
            items.setTirWeapon(tir);
            items.setFullText(fulText);
            items.setIcon(imageWeapon);
            imageRepositoryWeapon.save(imageWeapon);
            weaponRepository.save(items);
        }
    }


    public void deleteItems(Long id){
        Weapon weapon = weaponRepository.findById(id).orElse(null);
        imageRepositoryWeapon.count();

        weaponRepository.delete(weapon);
        imageRepositoryWeapon.deleteById(weapon.getIcon().getId());
    }

    private ImageWeapon toImageEntity(MultipartFile file) throws IOException {
        ImageWeapon image = new ImageWeapon();
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        return image;
    }
    public List<Weapon> weaponsTir(String tir){
        return weaponRepository.findByTirWeapon(tir);
    }
}
