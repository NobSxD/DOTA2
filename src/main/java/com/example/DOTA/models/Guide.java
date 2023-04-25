package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageGuide;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "guide")
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String name;

    private String energising;
    private String energising1;
    private String energising2;
    private String energising3;
    private String energising4;

    private String full_text;

    private String h2;
    private String full_text2;
    private String h3;
    private String full_text3;
    private String h4;
    private String full_text4;


    private String nameHref1;
    private String href1;

    private String nameHref2;
    private String href2;

    private String nameHref3;
    private String href3;

    private String nameHref4;
    private String href4;

    private String nameHref5;
    private String href5;


    private int views;
    private LocalDateTime dateTime;

    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "guide")

    private List<ImageGuide> imageGuides = new ArrayList<>();



}
