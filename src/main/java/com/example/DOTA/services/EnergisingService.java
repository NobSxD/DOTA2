package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Hero;
import com.example.DOTA.models.image.ImageEnergising;
import com.example.DOTA.models.image.ImageHero;
import com.example.DOTA.repository.EnergisingRepository;
import com.example.DOTA.repository.image.EnergisingRepositoryImage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlunit.util.Mapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnergisingService {
    private final EnergisingRepository energisingRepository;
    private final EnergisingRepositoryImage energisingRepositoryImage;

    public List<Energising> allEnergising(){
        return energisingRepository.findAll();
    }
    public Energising ByIdEnergising(Long id){
        return energisingRepository.findById(id).orElse(null);
    }



    public Energising energisingSave(Energising energising){
        return energisingRepository.save(energising);
    }

    public void energisingDelete(Long id){
         energisingRepository.deleteById(id);
    }

    public Energising energisingName(String name){
        return energisingRepository.findByName(name);
    }

    public ImageEnergising imageEnergisingSave(ImageEnergising imageEnergising){
        return energisingRepositoryImage.save(imageEnergising);
    }
    public ImageEnergising ByIDImageEnergising(Long id){
        return energisingRepositoryImage.findById(id).orElse(null);
    }



    public void energisingParameter(Energising energisingObj,MultipartFile file1,MultipartFile file2 ) throws IOException {
        Energising energising = energisingObj;//принимаем данные из контроллера что бы их сохранить в бд
        List<ImageEnergising> imageEnergising = new ArrayList<>();

        imageEnergising.add(toImageEntitySave(file1,energising.getName()));
        imageEnergising.add(toImageEntitySave(file2,energising.getName()));
        energising.setEnergisingImage(imageEnergising);
        if (imageEnergising.get(0).getSize() > 0) {
            imageEnergisingSave(imageEnergising.get(0));
        }
        if (imageEnergising.get(1).getSize() > 0) {
            imageEnergisingSave(imageEnergising.get(1));
        }
        energisingSave(energising);


    }

    private ImageEnergising toImageEntitySave(MultipartFile file, String name) throws IOException {
        ImageEnergising image = new ImageEnergising();                                                                  //принимаем данные из метода для сохранение картинки в бд
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);

        return image;
    }

    public void energisingParameterEdit(Energising energisingObj,MultipartFile file1, MultipartFile file2) throws IOException {
        Energising energising = ByIdEnergising(energisingObj.getId());

        List<ImageEnergising> imageEnergising = energising.getEnergisingImage();
        energising.setId(energisingObj.getId());                                                                                           //принимаем данные из контроллера что бы их изменить в бд
        energising.setName(energisingObj.getName());
        energising.setTip(energisingObj.getTip());
        energising.setItWorks(energisingObj.getItWorks());
        energising.setBuff(energisingObj.getBuff());
        energising.setDescriptionKlass(energisingObj.getDescriptionKlass());
        energising.setDescriptionBuff(energisingObj.getDescriptionBuff());
        energising.setBuffOne(energisingObj.getBuffOne());
        energising.setBuffTwo(energisingObj.getBuffTwo());
        energising.setBuffThere(energisingObj.getBuffThere());
        energising.setPriceDelete(energisingObj.getPriceDelete());
        energising.setOne(energisingObj.getOne());
        energising.setTwo(energisingObj.getTwo());
        energising.setThere(energisingObj.getThere());
        if (file1.getSize() > 0) {
            imageEnergising.set(0,toImageEntityEdit(energising.getEnergisingImage().get(0).getId(),file1,energising.getName()));

            imageEnergisingSave(imageEnergising.get(0));
        }
        if (file2.getSize() > 0) {
            imageEnergising.set(1,toImageEntityEdit(energising.getEnergisingImage().get(1).getId(),file2,energising.getName()));
            imageEnergisingSave(imageEnergising.get(1));
        }
        energising.setEnergisingImage(imageEnergising);
        energisingSave(energising);

    }

    private ImageEnergising toImageEntityEdit(Long id, MultipartFile file, String name) throws IOException {
        ImageEnergising image = new ImageEnergising();                                                                  //принимаем данные из метода для изменение картинки в бд
        image.setId(id);
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(name);
        return image;
    }
}
