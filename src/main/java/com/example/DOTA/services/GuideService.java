package com.example.DOTA.services;

import com.example.DOTA.models.Energising;
import com.example.DOTA.models.Guide;
import com.example.DOTA.repository.GuideRepository;
import com.example.DOTA.services.imageServices.SaveImageFileSystem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor

public class GuideService {
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




    public void editGuide(Guide guideDTO) throws IOException {
        Guide guide = guideById(guideDTO.getId());
        guide.setName(guideDTO.getName());
        guide.setEnergising(guideDTO.getEnergising());
        guide.setEnergising1(guideDTO.getEnergising1());
        guide.setEnergising2(guideDTO.getEnergising2());
        guide.setEnergising3(guideDTO.getEnergising3());
        guide.setEnergising4(guideDTO.getEnergising4());
        guide.setFull_text(guideDTO.getFull_text());
        guide.setH2(guideDTO.getH2());
        guide.setFull_text2(guideDTO.getFull_text2());
        guide.setH2(guideDTO.getH3());
        guide.setFull_text3(guideDTO.getFull_text3());
        guide.setH2(guideDTO.getH4());
        guide.setFull_text4(guideDTO.getFull_text4());
        guide.setNameHref1(guideDTO.getNameHref1());
        guide.setHref1(guideDTO.getHref2());
        guide.setNameHref2(guideDTO.getNameHref2());
        guide.setHref2(guideDTO.getHref2());
        guide.setNameHref3(guideDTO.getNameHref3());
        guide.setHref3(guideDTO.getHref3());
        guide.setNameHref4(guideDTO.getNameHref4());
        guide.setHref4(guideDTO.getHref4());
        guide.setNameHref5(guideDTO.getNameHref5());
        guide.setHref5(guideDTO.getHref5());
        guideRepository.save(guide);


    }





    public void delete(Long id , String upload) {
        Guide guide = guideById(id);
        SaveImageFileSystem.delete(guide.getFilName1(), upload);
        SaveImageFileSystem.delete(guide.getFilName2(), upload);
        SaveImageFileSystem.delete(guide.getFilName3(), upload);
        SaveImageFileSystem.delete(guide.getFilName4(), upload);
        SaveImageFileSystem.delete(guide.getFilName5(), upload);
        SaveImageFileSystem.delete(guide.getFilName6(), upload);
        SaveImageFileSystem.delete(guide.getFilName7(), upload);
        SaveImageFileSystem.delete(guide.getFilName8(), upload);
        deleteGuide(id);
    }



    public void save(Guide guideDTO) {
        guideRepository.save(guideDTO);
    }




}
