package com.example.DOTA.models;

import com.example.DOTA.models.image.ImageEnergising;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Energising {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String full_text;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private ImageEnergising energisingImage;
}
