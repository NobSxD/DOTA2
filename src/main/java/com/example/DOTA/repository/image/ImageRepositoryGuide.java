package com.example.DOTA.repository.image;

import com.example.DOTA.models.Guide;
import com.example.DOTA.models.image.ImageGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepositoryGuide extends JpaRepository<ImageGuide, Long> {
    List<ImageGuide> findByGuide(Guide guide);
}
