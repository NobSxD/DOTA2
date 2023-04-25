package com.example.DOTA.repository.image;

import com.example.DOTA.models.image.ImageEnergising;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergisingRepositoryImage extends JpaRepository<ImageEnergising, Long> {
    public ImageEnergising findByName(String name);
}
