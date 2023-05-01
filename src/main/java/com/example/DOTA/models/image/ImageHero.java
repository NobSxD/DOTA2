package com.example.DOTA.models.image;

import com.example.DOTA.models.Hero;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @OneToOne(fetch = FetchType.EAGER)
    private Hero hero;


}
