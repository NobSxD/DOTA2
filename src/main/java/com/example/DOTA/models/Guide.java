package com.example.DOTA.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

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
    @Size(max = 8000)
    private String full_text;

    private String h2;
    @Size(max = 8000)
    private String full_text2;
    private String h3;
    @Size(max = 8000)
    private String full_text3;
    private String h4;
    @Size(max = 8000)
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



    private String filName1;
    private String filName2;
    private String filName3;
    private String filName4;
    private String filName5;
    private String filName6;
    private String filName7;
    private String filName8;


    @PrePersist
    private void init(){
        dateTime = LocalDateTime.now();
    }





}
