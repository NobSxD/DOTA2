package com.example.DOTA.repository;

import com.example.DOTA.models.ClassHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassHero, Long> {
}
