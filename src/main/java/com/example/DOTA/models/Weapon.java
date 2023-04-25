package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageWeapon;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "weapon")
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String tirWeapon;
    private String fullText;
    @ManyToOne(fetch = FetchType.LAZY)
    private ImageWeapon icon;


    private long items1;


    private long items2;


    private long items3;

    private LocalDateTime dateTime;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

    public Weapon(String name, String tirWeapon, String fullText, ImageWeapon icon, long items1, long items2, long items3) {
        this.name = name;
        this.tirWeapon = tirWeapon;
        this.fullText = fullText;
        this.icon = icon;
        this.items1 = items1;
        this.items2 = items2;
        this.items3 = items3;
    }
}



