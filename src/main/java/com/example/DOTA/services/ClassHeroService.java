package com.example.DOTA.services;

import com.example.DOTA.models.ClassHero;
import com.example.DOTA.models.Rasa;
import com.example.DOTA.repository.ClassHeroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class ClassHeroService {
    private final ClassHeroRepository repositoryClass;

    private final EnergisingService energisingService;


    private ClassHero saveRepoClass(ClassHero classHero){
        return repositoryClass.save(classHero);
    }
    public void saveClassHero(String name){
        if(name.length() >0) {
            ClassHero classHero = new ClassHero();                                                                                         //берем название рассы из формы, по этому названию ищем данные в таблице синергия
            classHero.setName(name);                                                                                             //и добавляем эти данные в бд
            classHero.setEnergising(energisingService.energisingName(name));
            saveRepoClass(classHero);
        }
    }

    public void updateClassHero(String name,Long id){
        if(name.length() >0) {
            ClassHero classHero = classHero(id);                                                                                         //обновляем данные в бд
            classHero.setId(id);
            classHero.setName(name);
            classHero.setEnergising(energisingService.energisingName(name));
            saveRepoClass(classHero);
        }
    }
    public List<ClassHero> listClassHero(){
        return repositoryClass.findAll();
    }
    public ClassHero classHero(Long id){
        return repositoryClass.findById(id).orElse(null);

    }
    public long summaClass(){
        return repositoryClass.count();
    }
    public void deleteClass(ClassHero classHero){
        repositoryClass.delete(classHero);
    }
}
