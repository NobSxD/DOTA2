package com.example.DOTA.repository.image;

import com.example.DOTA.models.image.ImageWeapon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepositoryWeapon extends JpaRepository<ImageWeapon, Long> {

    List<ImageWeapon> findByName(String name);
}
