package com.example.DOTA.services;

import com.example.DOTA.models.Hero;
import com.example.DOTA.models.Weapon;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.models.image.ImageWeapon;
import com.example.DOTA.repository.WeaponRepository;
import com.example.DOTA.repository.WeaponRepository2;
import com.example.DOTA.repository.image.ImageRepositoryWeapon;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final ImageRepositoryWeapon imageRepositoryWeapon;
    private final WeaponRepository weaponRepository;
    private final WeaponRepository2 weaponRepository2;



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

    public void saveWeapon(String weapon, String tir, String passiveSkill,String activeSkill,String rangeAttack, String kd, String items1,String items2,String items3, MultipartFile file1) throws IOException {

        Weapon items = new Weapon();
        if (tir.equals("ТИР 1") || tir.equals("ТИР 2") || tir.equals("ТИР 3") || tir.equals("ТИР 4") || tir.equals("ТИР 5")) {





            if(items1 != null && items1.length() > 0){
                Weapon imageItems = new Weapon();
                imageItems = weaponRepository2.findByName(items1).get(0);
                items.setWeapons1(imageItems);
            }
            if (items2 != null && items2.length() > 0) {
                Weapon imageItems2 = new Weapon();
                imageItems2 = weaponRepository2.findByName(items2).get(0);
                items.setWeapons2(imageItems2);
            }
            if (items3 != null && items3.length() > 0) {
                Weapon imageItems3 = new Weapon();
                imageItems3 =weaponRepository2.findByName(items3).get(0);
                items.setWeapons3(imageItems3);
            }

            ImageWeapon imageWeapon = new ImageWeapon();

            if (file1.getSize() != 0) {
              imageWeapon =  toImageEntity(file1);
              imageWeapon.setName(weapon);
              imageRepositoryWeapon.save(imageWeapon);
            }

            items.setName(weapon);
            items.setTirWeapon(tir);
            items.setActiveSkill(activeSkill);
            items.setPassiveSkill(passiveSkill);
            items.setRangeAttack(rangeAttack);
            items.setKd(kd);
            items.setIcon(imageWeapon);
            items.setDateTime(LocalDateTime.now());

            weaponRepository.save(items);
        }
    }


    public void editWeapon(String weapon, String tir, String passiveSkill,String activeSkill,String rangeAttack, String kd, String items1,String items2,String items3, MultipartFile file1, Long id) throws IOException {

        Weapon items = weaponID(id);
        if (tir.equals("ТИР 1") || tir.equals("ТИР 2") || tir.equals("ТИР 3") || tir.equals("ТИР 4") || tir.equals("ТИР 5")) {


            Weapon imageItems = items.getWeapons1();
            Weapon imageItems2 = items.getWeapons2();
            Weapon imageItems3 = items.getWeapons3();
            if(items1.length() > 0){
                imageItems = weaponRepository2.findByName(items1).get(0);
            }
            if (items2.length() > 0) {
                imageItems2 = weaponRepository2.findByName(items2).get(0);
            }
            if (items3.length() > 0) {
                imageItems3 = weaponRepository2.findByName(items3).get(0);
            }
            ImageWeapon imageWeapon = items.getIcon();

            if (file1.getSize() != 0) {
                imageWeapon =  toImageEntity(file1);
                imageWeapon.setName(weapon);

            }
            items.setId(id);
            items.setName(weapon);
            items.setTirWeapon(tir);
            items.setActiveSkill(activeSkill);
            items.setPassiveSkill(passiveSkill);
            items.setRangeAttack(rangeAttack);
            items.setKd(kd);
            items.setIcon(imageWeapon);
            items.setWeapons1(imageItems);
            items.setWeapons2(imageItems2);
            items.setWeapons3(imageItems3);
            items.setDateTime(LocalDateTime.now());
            imageRepositoryWeapon.save(imageWeapon);
            weaponRepository.save(items);
        }
    }


    public void deleteItems(Long id){
        Weapon weapon = weaponID(id);

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
