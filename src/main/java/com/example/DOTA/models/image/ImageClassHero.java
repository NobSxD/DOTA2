package com.example.DOTA.models.image;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name = "imageClassHero")
public class ImageClassHero {
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
    private String detals;

    private LocalDateTime dateTime;




    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }

}
