package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.image.ImageEnergising;
import com.example.DOTA.repository.EnergisingRepository;
import com.example.DOTA.repository.image.EnergisingRepositoryImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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

    public void energisingParameter(String name, String tip, String ItWorks, String buff,
                                    String descriptionKlass, String descriptionBuff, String buffOne, String buffTwo, String buffThere,String priceDelete,MultipartFile file1,MultipartFile file2 ) throws IOException {
        Energising energising = new Energising();//принимаем данные из контроллера что бы их сохранить в бд
        List<ImageEnergising> imageEnergising = new ArrayList<>();
        energising.setName(name);
        energising.setTip(tip);
        energising.setItWorks(ItWorks);
        energising.setBuff(buff);
        energising.setDescriptionKlass(descriptionKlass);
        energising.setDescriptionBuff(descriptionBuff);
        energising.setBuffOne(buffOne);
        energising.setBuffTwo(buffTwo);
        energising.setBuffThere(buffThere);
        energising.setPriceDelete(priceDelete);
        imageEnergising.add(toImageEntitySave(file1,name));
        imageEnergising.add(toImageEntitySave(file2,name));
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

    public void energisingParameterEdit(Long id,String name, String tip, String ItWorks, String buff,
                                        String descriptionKlass, String descriptionBuff, String buffOne, String buffTwo, String buffThere,String priceDelete,MultipartFile file1, MultipartFile file2) throws IOException {
        Energising energising = ByIdEnergising(id);
        List<ImageEnergising> imageEnergising = energising.getEnergisingImage();
        energising.setId(id);                                                                                           //принимаем данные из контроллера что бы их изменить в бд
        energising.setName(name);
        energising.setTip(tip);
        energising.setItWorks(ItWorks);
        energising.setBuff(buff);
        energising.setDescriptionKlass(descriptionKlass);
        energising.setDescriptionBuff(descriptionBuff);
        energising.setBuffOne(buffOne);
        energising.setBuffTwo(buffTwo);
        energising.setBuffThere(buffThere);
        energising.setPriceDelete(priceDelete);
        if (file1.getSize() > 0) {
            imageEnergising.set(0,toImageEntityEdit(energising.getEnergisingImage().get(0).getId(),file1,name));

            imageEnergisingSave(imageEnergising.get(0));
        }
        if (file2.getSize() > 0) {
            imageEnergising.set(1,toImageEntityEdit(energising.getEnergisingImage().get(1).getId(),file2,name));
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
