package com.example.DOTA.models.image;

import com.example.DOTA.models.Guide;
import com.example.DOTA.models.Hero;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "ImageGuide")
public class ImageGuide {


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

    @ManyToOne(fetch = FetchType.EAGER)
    private Guide guide;

    private LocalDateTime dateTime;


    @PrePersist
    private void init() {
        dateTime = LocalDateTime.now();
    }


}
