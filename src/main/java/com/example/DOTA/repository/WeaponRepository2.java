package com.example.DOTA.repository;

import com.example.DOTA.models.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeaponRepository2 extends JpaRepository<Weapon, Long> {

    List<Weapon> findByName(String name);
}
