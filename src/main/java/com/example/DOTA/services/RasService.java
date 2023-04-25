package com.example.DOTA.services;

import com.example.DOTA.models.Rasa;
import com.example.DOTA.repository.RasaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class RasService {

    private final RasaRepository rasaRepository;
    private final EnergisingService energisingService;


    private Rasa saveRepo(Rasa rasa){
        return rasaRepository.save(rasa);
    }
    public void saveRasHero(String name){
        Rasa rasa = new Rasa();                                                                                         //берем название рассы из формы, по этому названию ищем данные в таблице синергия
        rasa.setName(name);                                                                                             //и добавляем эти данные в бд
        rasa.setEnergising(energisingService.energisingName(name));
        saveRepo(rasa);
        }

    public void updateRasHero(String name,Long id){
        Rasa rasa = new Rasa();                                                                                         //обновляем данные в бд
        rasa.setId(id);
        rasa.setName(name);
        rasa.setEnergising(energisingService.energisingName(name));
        saveRepo(rasa);
    }
    public List<Rasa> listRasHero(){
        return rasaRepository.findAll();
    }
    public Rasa rasHero(Long id){
        return rasaRepository.findById(id).orElse(null);

    }
    public long summaRas(){
        return rasaRepository.count();
    }
    public void delete(Rasa rasa){
        rasaRepository.delete(rasa);
    }


}
