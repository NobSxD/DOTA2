package com.example.DOTA.repository;

import com.example.DOTA.models.Energising;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnergisingRepository extends JpaRepository<Energising, Long> {
    public Energising findByName(String name);
}
