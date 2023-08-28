package com.example.DOTA.models.image;

import com.example.DOTA.models.Hero;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



@Entity
@Setter
@Getter
@Table(name = "imageHero")
public class ImageHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    @OneToOne(fetch = FetchType.LAZY)
    private Hero hero;


}
