package com.example.DOTA.services;


import com.example.DOTA.models.Weapon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(JUnit4.class)
public class WeaponServiceTest {

    @Mock
    private WeaponService weaponService;
    @InjectMocks
    public WeaponService sut;


    @Test
    public void saveWeapon() {

    }



}