package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageWeapon;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "weapon")
@NoArgsConstructor
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String tirWeapon;
    private String passiveSkill;
    private String activeSkill;
    private String rangeAttack;
    private String kd;


    @ManyToOne(fetch = FetchType.LAZY)
    private ImageWeapon icon;
    private LocalDateTime dateTime;


    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "weapons_id1")
    private Weapon weapons1;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "weapons_id2")
    private Weapon weapons2;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "weapons_id3")
    private Weapon weapons3;


}



