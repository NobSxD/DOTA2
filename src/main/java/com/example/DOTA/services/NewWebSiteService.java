package com.example.DOTA.services;

import com.example.DOTA.models.NewWebSite;
import com.example.DOTA.models.Path;
import com.example.DOTA.repository.NewWebSiteRepository;
import com.example.DOTA.repository.PathRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewWebSiteService {
    private final NewWebSiteRepository newWebSiteRepository;

    public List<NewWebSite> pathAll(){
        return newWebSiteRepository.findAll();
    }
    public void deleteNewWebSite(Long id){
        newWebSiteRepository.deleteById(id);
    }
    public NewWebSite newWebSiteById(Long id){
        return newWebSiteRepository.findById(id).orElse(null);
    }
    public NewWebSite saveNewWebSite(String name, String full_text){
        NewWebSite newWebSite = new NewWebSite();
        newWebSite.setName(name);
        newWebSite.setFull_text(full_text);
        return newWebSiteRepository.save(newWebSite);
    }

    public NewWebSite editNewWebSite(Long id, String name, String full_text){
        NewWebSite newWebSite = newWebSiteById(id);
        newWebSite.setId(id);
        newWebSite.setName(name);
        newWebSite.setFull_text(full_text);
        return newWebSiteRepository.save(newWebSite);
    }
}
