package com.example.DOTA.services;

import com.example.DOTA.models.Path;
import com.example.DOTA.repository.PathRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PathService {
    private final PathRepository pathRepository;

    public List<Path> pathAll(){
        return pathRepository.findAll().stream().sorted(Comparator.comparing(Path::getId).reversed()).collect(Collectors.toList());
    }
    public void deletePath(Long id){
        pathRepository.deleteById(id);
    }
    public Path pathById(Long id){
        return pathRepository.findById(id).orElse(null);
    }
    public Path savePath(String name, String full_text, MultipartFile file) throws IOException {
        Path path = new Path();
        path.setName(name);
        path.setOriginalFileName(file.getOriginalFilename());
        path.setSize(file.getSize());
        path.setContentType(file.getContentType());
        path.setBytes(file.getBytes());
        path.setFull_text(full_text);
        return pathRepository.save(path);
    }

    public Path editPath(Long id, String name, String full_text,  MultipartFile file) throws IOException {

        Path path = pathById(id);
        path.setId(id);

        path.setName(name);
        path.setOriginalFileName(file.getOriginalFilename());
        path.setSize(file.getSize());
        path.setContentType(file.getContentType());
        path.setBytes(file.getBytes());
        path.setFull_text(full_text);
        return pathRepository.save(path);
    }
}
