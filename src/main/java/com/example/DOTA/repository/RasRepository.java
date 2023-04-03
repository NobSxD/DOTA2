package com.example.DOTA.repository;

import com.example.DOTA.models.RasHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RasRepository extends JpaRepository<RasHero, Long> {
}
