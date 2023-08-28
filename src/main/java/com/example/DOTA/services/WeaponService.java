package com.example.DOTA.services;

import com.example.DOTA.models.Weapon;
import com.example.DOTA.repository.WeaponRepository;
import com.example.DOTA.services.imageServices.SaveImageFileSystem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeaponService {
    private final WeaponRepository weaponRepository;

    public List<Weapon> weaponAll(){
        return weaponRepository.findAll();
    }
    public Weapon weaponID(Long id){ return weaponRepository.findById(id).orElse(null);}
    public Long summaItems(){
        return weaponRepository.count();
    }

    public void saveWeapon(Weapon weapon){
        weaponRepository.save(weapon);
    }

    public void editWeapon(Weapon weapon){
        Weapon items = weaponID(weapon.getId());
            items.setId(weapon.getId());
            items.setName(weapon.getName());
            items.setTirWeapon(weapon.getTirWeapon());
            items.setActiveSkill(weapon.getActiveSkill());
            items.setPassiveSkill(weapon.getPassiveSkill());
            items.setRangeAttack(weapon.getRangeAttack());
            items.setKd(weapon.getKd());

            if (!weapon.getIcon().isEmpty() && weapon.getIcon() != null){
                items.setIcon(weapon.getIcon());
            }

            items.setDateTime(LocalDateTime.now());
            weaponRepository.save(items);
        }

    public void deleteItems(Long id, String uploads){
        Weapon weapon = weaponID(id);
        SaveImageFileSystem.delete(weapon.getIcon(), uploads);
        weaponRepository.deleteById(id);
    }

    public List<Weapon> weaponsTir(String tir){
        return weaponRepository.findByTirWeapon(tir);
    }
}
