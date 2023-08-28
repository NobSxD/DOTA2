package com.example.DOTA.models.image;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ImageSkill")
public class ImageSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String originalFileName;
    private Long size;
    private String contentType;
    @Lob
    private byte[] bytes;
    private String detals;

    private LocalDateTime dateTime;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

}
