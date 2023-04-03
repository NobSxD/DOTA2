package com.example.DOTA.models.image;

import com.example.DOTA.models.RasHero;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ImageRasHero")
public class ImageRasHero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    private boolean previewImage;
    @Lob
    private byte[] bytes;
    @ManyToOne(fetch = FetchType.EAGER)
    private RasHero rasHero;
}
