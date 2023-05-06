package com.example.DOTA.services;

import com.example.DOTA.models.Views;
import com.example.DOTA.repository.ViewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ViewsService {
    private final ViewsRepository viewsRepository;
    public List<Views> viewsList(){ return viewsRepository.findAll();}

    public void viewsClass(){
        Views views = new Views();
        views.setId(1l);
        views.setName("Class");
        views.setUrl("/home/display/class");
        int i = 0;
        i++;
        if (viewsRepository.findById(1l).isPresent()) {
            views.setViews(viewsRepository.findById(1l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }

    public void viewsEnergising(){
        Views views = new Views();
        views.setId(2l);
        views.setName("Energising");
        views.setUrl("/home/display/energising");
        int i = 0;
        i++;
        if (viewsRepository.findById(2l).isPresent()) {
            views.setViews(viewsRepository.findById(2l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsGuid(){
        Views views = new Views();
        views.setId(3l);
        views.setName("Guide");
        views.setUrl("/home/display/guide");
        int i = 0;
        i++;
        if (viewsRepository.findById(3l).isPresent()) {
            views.setViews(viewsRepository.findById(3l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsHero(){
        Views views = new Views();
        views.setId(4l);
        views.setName("Hero");
        views.setUrl("/home/display/hero");
        int i = 0;
        i++;
        if (viewsRepository.findById(4l).isPresent()) {
            views.setViews(viewsRepository.findById(4l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsMain(){
        Views views = new Views();
        views.setId(5l);
        views.setName("Main");
        views.setUrl("/");
        int i = 0;
        i++;
        if (viewsRepository.findById(5l).isPresent()) {
            views.setViews(viewsRepository.findById(5l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }

    public void viewsMainHome(){
        Views views = new Views();
        views.setId(5l);
        views.setName("MainHome");
        views.setUrl("/home");
        int i = 0;
        i++;
        if (viewsRepository.findById(5l).isPresent()) {
            views.setViews(viewsRepository.findById(5l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsNewWeb(){
        Views views = new Views();
        views.setId(6l);
        views.setName("NewWeb");
        views.setUrl("/home/display/newWebSite");
        int i = 0;
        i++;
        if (viewsRepository.findById(6l).isPresent()) {
            views.setViews(viewsRepository.findById(6l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsPath(){
        Views views = new Views();
        views.setId(7l);
        views.setName("Path");
        views.setUrl("/home/display/path");
        int i = 0;
        i++;
        if (viewsRepository.findById(7l).isPresent()) {
            views.setViews(viewsRepository.findById(7l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsPlay(){
        Views views = new Views();
        views.setId(8l);
        views.setName("Play");
        views.setUrl("/home/display/play");
        int i = 0;
        i++;
        if (viewsRepository.findById(8l).isPresent()) {
            views.setViews(viewsRepository.findById(8l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsRas(){
        Views views = new Views();
        views.setId(9l);
        views.setName("Ras");
        views.setUrl("/home/display/ras");
        int i = 0;
        i++;
        if (viewsRepository.findById(9l).isPresent()) {
            views.setViews(viewsRepository.findById(9l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsSkill(){
        Views views = new Views();
        views.setId(10l);
        views.setName("Skill");
        views.setUrl("/home/display/skill");
        int i = 0;
        i++;
        if (viewsRepository.findById(10l).isPresent()) {
            views.setViews(viewsRepository.findById(10l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
    public void viewsItems(){
        Views views = new Views();
        views.setId(11l);
        views.setName("Items");
        views.setUrl("/home/display/items");
        int i = 0;
        i++;
        if (viewsRepository.findById(11l).isPresent()) {
            views.setViews(viewsRepository.findById(11l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }

}
