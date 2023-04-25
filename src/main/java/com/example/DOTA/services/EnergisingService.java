package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.image.ImageEnergising;
import com.example.DOTA.repository.EnergisingRepository;
import com.example.DOTA.repository.image.EnergisingRepositoryImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public ImageEnergising ByIDImageEnergising(Long id){
        return energisingRepositoryImage.findById(id).orElse(null);
    }

    public Energising energisingSave(Energising energising){
        return energisingRepository.save(energising);
    }
    public ImageEnergising imageEnergisingSave(ImageEnergising imageEnergising){
        return energisingRepositoryImage.save(imageEnergising);
    }
    public void energisingDelete(Long id){
         energisingRepository.deleteById(id);
    }

    public Energising energisingName(String name){
        return energisingRepository.findByName(name);
    }

    public void energisingParameter(String name, String full_text, MultipartFile file1) throws IOException {
        Energising energising = new Energising();                                                                       //принимаем данные из контроллера что бы их сохранить в бд
        energising.setName(name);
        energising.setFull_text(full_text);
        energising.setEnergisingImage(toImageEntitySave(file1,name));
        imageEnergisingSave(energising.getEnergisingImage());
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

    public void energisingParameterEdit(Long id, String name, String full_text, MultipartFile file1) throws IOException {
        Energising energising = new Energising();
        energising.setId(id);                                                                                           //принимаем данные из контроллера что бы их изменить в бд
        energising.setName(name);
        energising.setFull_text(full_text);
        if (file1.getSize() > 0) {
            energising.setEnergisingImage(toImageEntityEdit(id, file1, name));
        }else {

            energising.setEnergisingImage(energisingRepositoryImage.findByName(name));
        }
        energisingSave(energising);
        if (file1.getSize() > 0) {
            imageEnergisingSave(energising.getEnergisingImage());
        }

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
