package com.example.DOTA.repository;

import com.example.DOTA.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    List<Weapon> findByTirWeapon(String tir);
}
