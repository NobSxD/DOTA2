package com.example.DOTA.services;

import com.example.DOTA.models.Path;
import com.example.DOTA.repository.PathRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathService {
    private final PathRepository pathRepository;

    public List<Path> pathAll(){
        return pathRepository.findAll();
    }
    public void deletePath(Long id){
        pathRepository.deleteById(id);
    }
    public Path pathById(Long id){
        return pathRepository.findById(id).orElse(null);
    }
    public Path savePath(String name, String full_text){
        Path path = new Path();
        path.setName(name);
        path.setFull_text(full_text);
        return pathRepository.save(path);
    }

    public Path editPath(Long id, String name, String full_text){
        Path path = new Path();
        path.setId(id);
        path.setName(name);
        path.setFull_text(full_text);
        return pathRepository.save(path);
    }
}
