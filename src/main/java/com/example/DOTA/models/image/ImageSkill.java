package com.example.DOTA.models.image;

import com.example.DOTA.models.Skill;
import lombok.Data;

import javax.persistence.*;

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
    private boolean previewImage;
    @Lob
    private byte[] bytes;
    @ManyToOne(fetch = FetchType.EAGER)
    private Skill skill;
}
