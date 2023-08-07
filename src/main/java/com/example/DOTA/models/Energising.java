package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageEnergising;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Energising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String tip;
    private String ItWorks;
    @Size(max = 2000)
    private String buff;
    private String descriptionKlass;
    private String descriptionBuff;

    private String buffOne;
    private String buffTwo;
    private String buffThere;
    private String priceDelete;
    private int one;
    private int two;
    private int there;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
     private List<ImageEnergising> energisingImage;

}
