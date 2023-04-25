package com.example.DOTA.models.image;

import com.example.DOTA.models.Weapon;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image_weapon")
public class ImageWeapon {
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

}
