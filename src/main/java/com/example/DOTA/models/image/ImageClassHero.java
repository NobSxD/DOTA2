package com.example.DOTA.models.image;

import com.example.DOTA.models.ClassHero;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private ClassHero classHero;
}
