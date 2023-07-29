package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Guide;
import com.example.DOTA.models.image.ImageGuide;
import com.example.DOTA.repository.CrudRepo;
import com.example.DOTA.repository.GuideRepository;
import com.example.DOTA.repository.image.ImageRepositoryGuide;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor

public class GuideService implements CrudRepo {
    private final ImageRepositoryGuide imageRepositoryGuide;
    private final GuideRepository guideRepository;
    private final EnergisingService energisingService;



    public Long countGuide() {
        return guideRepository.count();
    }

    public List<Guide> guidesAll() {
        return guideRepository.findAll();
    }

    public Guide guideById(Long id) {
        return guideRepository.findById(id).orElse(null);
    }
    public void deleteGuide(Long id) {
        guideRepository.deleteById(id);
    }

    public ImageGuide imageGuideById(Long id) {
        return imageRepositoryGuide.findById(id).orElse(null);
    }





    public List<Energising> energising(Long id) {
        Guide guide = guideById(id);
        List<Energising> energisings = new ArrayList<>();
        if (!guide.getEnergising().isEmpty()) {
            Energising energising = energisingService.energisingName(guide.getEnergising());
            if (energising != null) {
                energisings.add(energising);
            }
        }
        if (!guide.getEnergising1().isEmpty()) {
            Energising energising1 = energisingService.energisingName(guide.getEnergising1());
            if (energising1 != null) {
                energisings.add(energising1);
            }
        }
        if (!guide.getEnergising2().isEmpty()) {
            Energising energising2 = energisingService.energisingName(guide.getEnergising2());
            if (energising2 != null) {
                energisings.add(energising2);
            }
        }
        if (!guide.getEnergising3().isEmpty()) {
            Energising energising3 = energisingService.energisingName(guide.getEnergising3());
            if (energising3 != null) {
                energisings.add(energising3);
            }
        }
        if (!guide.getEnergising4().isEmpty()) {
            Energising energising4 = energisingService.energisingName(guide.getEnergising4());
            if (energising4 != null) {
                energisings.add(energising4);
            }
        }
        return energisings;
    }

    public void saveGuide(Guide guide,
                          MultipartFile file1,
                          MultipartFile file2,
                          MultipartFile file3,
                          MultipartFile file4,
                          MultipartFile file5,
                          MultipartFile file6,
                          MultipartFile file7,
                          MultipartFile file8) throws IOException {



        saveImageGuide(file1, file2, file3, file4, file5, file6, file7, file8, guide);

    }

    public void saveImageGuide(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7, MultipartFile file8, Guide guide) throws IOException {
        if (file1.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file1, guide));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то сохраняем в бд
        }
        if (file2.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file2, guide));
        }
        if (file3.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file3, guide));
        }
        if (file4.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file4, guide));
        }
        if (file5.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file5, guide));
        }
        if (file6.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file6, guide));
        }
        if (file7.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file7, guide));
        }
        if (file8.getSize() > 0) {
            imageRepositoryGuide.save(toImageEntitySave(file8, guide));
        }
    }

    private ImageGuide toImageEntitySave(MultipartFile file, Guide guide) throws IOException {
        ImageGuide image = new ImageGuide();                                                                            //сетим поля картинки
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(file.getName());
        image.setGuide(guide);
        image.setDateTime(LocalDateTime.now());
        return image;
    }

    public void editGuide(Long id,
                          String name,                                                                                      //добовляю параметры пришедшие из формы html для изменение в бд
                          String energising,
                          String energising1,
                          String energising2,
                          String energising3,
                          String energising4,
                          String full_text,
                          String h2,
                          String full_text2,
                          String h3,
                          String full_text3,
                          String h4,
                          String full_text4,
                          String hrefName1,
                          String href1,
                          String hrefName2,
                          String href2,
                          String hrefName3,
                          String href3,
                          String hrefName4,
                          String href4,
                          String hrefName5,
                          String href5,
                          MultipartFile file1,
                          MultipartFile file2,
                          MultipartFile file3,
                          MultipartFile file4,
                          MultipartFile file5,
                          MultipartFile file6,
                          MultipartFile file7,
                          MultipartFile file8) throws IOException {
        Guide guide = guideById(id);
        guide.setId(id);
        guide.setName(name);
        guide.setEnergising(energising);
        guide.setEnergising1(energising1);
        guide.setEnergising2(energising2);
        guide.setEnergising3(energising3);
        guide.setEnergising4(energising4);
        guide.setFull_text(full_text);
        guide.setH2(h2);
        guide.setFull_text2(full_text2);
        guide.setH2(h3);
        guide.setFull_text3(full_text3);
        guide.setH2(h4);
        guide.setFull_text4(full_text4);
        guide.setNameHref1(hrefName1);
        guide.setHref1(href1);
        guide.setNameHref2(hrefName2);
        guide.setHref2(href2);
        guide.setNameHref3(hrefName3);
        guide.setHref3(href3);
        guide.setNameHref4(hrefName4);
        guide.setHref4(href4);
        guide.setNameHref5(hrefName5);
        guide.setHref5(href5);
        guideRepository.save(guide);

        editImageGuide(file1, file2, file3, file4, file5, file6, file7, file8, guide);
    }

    public void editImageGuide(MultipartFile file1, MultipartFile file2, MultipartFile file3, MultipartFile file4, MultipartFile file5, MultipartFile file6, MultipartFile file7, MultipartFile file8, Guide guide) throws IOException {
        if (file1.getSize() > 0) {
            if (guide.getImageGuides().size() >= 1) {
                imageRepositoryGuide.save(toImageEntityEdit(file1, guide, guide.getImageGuides().get(0).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file1, guide));
            }
        }
        if (file2.getSize() > 0) {
            if (guide.getImageGuides().size() >= 2) {
                imageRepositoryGuide.save(toImageEntityEdit(file2, guide, guide.getImageGuides().get(1).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file2, guide));
            }
        }
        if (file3.getSize() > 0) {
            if (guide.getImageGuides().size() >= 3) {
                imageRepositoryGuide.save(toImageEntityEdit(file3, guide, guide.getImageGuides().get(2).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file3, guide));
            }
        }
        if (file4.getSize() > 0) {
            if (guide.getImageGuides().size() >= 4) {
                imageRepositoryGuide.save(toImageEntityEdit(file4, guide, guide.getImageGuides().get(3).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file4, guide));
            }
        }
        if (file5.getSize() > 0) {
            if (guide.getImageGuides().size() >= 5) {
                imageRepositoryGuide.save(toImageEntityEdit(file5, guide, guide.getImageGuides().get(4).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file5, guide));
            }
        }
        if (file6.getSize() > 0) {
            if (guide.getImageGuides().size() >= 6) {
                imageRepositoryGuide.save(toImageEntityEdit(file5, guide, guide.getImageGuides().get(5).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file5, guide));
            }
        }
        if (file7.getSize() > 0) {
            if (guide.getImageGuides().size() >= 7) {
                imageRepositoryGuide.save(toImageEntityEdit(file7, guide, guide.getImageGuides().get(6).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file7, guide));
            }
        }
        if (file8.getSize() > 0) {
            if (guide.getImageGuides().size() >= 8) {
                imageRepositoryGuide.save(toImageEntityEdit(file8, guide, guide.getImageGuides().get(7).getId()));                                                 //делаю проверку добавил ли админ картинку или нет, если добавил то изменяем в бд
            } else {
                imageRepositoryGuide.save(toImageEntitySave(file8, guide));
            }
        }
    }

    private ImageGuide toImageEntityEdit(MultipartFile file, Guide guide, Long id) throws IOException {
        ImageGuide image = new ImageGuide();
        image.setId(id);                                                                                                 //сетим поля картинки для изменение в бд
        image.setOriginalFileName(file.getOriginalFilename());
        image.setSize(file.getSize());
        image.setContentType(file.getContentType());
        image.setBytes(file.getBytes());
        image.setName(file.getName());
        image.setGuide(guide);
        image.setDateTime(LocalDateTime.now());
        return image;
    }

    public List<ImageGuide> displayAll() {                                                                              //получаем отоброжение в список всех гайдов и только главной картинки
        List<Guide> guideList = guidesAll();
        List<ImageGuide> imageGuides = new ArrayList<>();
        for (Guide sort : guideList
        ) {
            imageGuides.add(imageRepositoryGuide.findByGuide(sort).get(0));
        }
        return imageGuides;
    }


    @Override
    public void delete(Long id) {
        List<ImageGuide> guideList = imageRepositoryGuide.findByGuide(guideRepository.findById(id).orElse(null));
        for (ImageGuide delete : guideList
        ) {
            imageRepositoryGuide.delete(delete);
        }
        deleteGuide(id);
    }


    @Override
    public void save(Object o) {
        Guide guide = (Guide) o;
        guideRepository.save(guide);
    }

    @Override
    public void create(Object o) {

    }


}
