package com.example.DOTA.models.image;

import com.example.DOTA.models.Hero;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Hero hero;


}
