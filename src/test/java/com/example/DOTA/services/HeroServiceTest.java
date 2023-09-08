package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.HeroRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import static com.example.DOTA.config.CustomAccessDeniedHandler.LOG;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class HeroServiceTest {

    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;

    @Autowired private  HeroRepository heroRepository;

    @BeforeEach
        //  @Rollback(value = false)
    void saveHero() {
        List<ImageHero> list = new ArrayList<>();
        list.add(new ImageHero());
        Hero hero = new Hero(1l,"джага","ТИР1", "воин","орк", "бог", "800", "100"
                ,"40", "50","300","5","маг","куритилка","критилка",
                "описание героя","детали скила", "детали скила2","детали скила3","детали скила4"
                ,"детали скила5","детали скила6","100","5",
                LocalDateTime.now(), new Energising(),new Energising(),new Energising(),list);
        heroRepository.save(hero);
        LOG.info("save!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

    }

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();

        assertThat(heroRepository).isNotNull();
    }



    @Test
    void getImageHero() {
    }

    @Test
    void heroExistById() {
    }

    @Test
    void getListHeroAll() {
    }





    @Test
    void getHeroById() {
        LOG.info("count = " + heroRepository.count());
        Hero byId = heroRepository.findById(10l).orElse(null);
        List<Hero> heroList = heroRepository.findAll();
        LOG.info(String.valueOf(heroList.get(0).getId()));
        LOG.info("count list" + heroList.size());
        assertThat("джага").isEqualTo(byId.getNameHero());

    }

    @Test
    void summaHero() {
    }

    @Test
    void imageHeroList() {
    }

    @Test
    void imageHeroAll() {
    }

    @Test
    void saveImage() {
    }

    @Test
    void displayHeroEnergising() {
    }

    @Test
    void testDisplayHeroEnergising() {
    }

    @Test
    void testSaveHero() {
    }

    @Test
    void editHero() {
    }

    @Test
    void displayHero() {
    }

    @Test
    void deleteHero() {
    }
}