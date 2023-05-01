package com.example.DOTA.services;

import com.example.DOTA.models.Views;
import com.example.DOTA.repository.ViewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ViewsService {
    private final ViewsRepository viewsRepository;

    public void viewsGuid(){
        Views views = new Views();
        views.setId(1l);
        views.setName("Guide");
        int i = 0;
        i++;
        if (viewsRepository.findById(1l).isPresent()) {
            views.setViews(viewsRepository.findById(1l).get().getViews() + i);
        } else views.setViews(i);
        viewsRepository.save(views);
    }
}
