package com.project.estimate_value.service;

import com.project.estimate_value.model.Analise;
import com.project.estimate_value.repository.AnaliseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnaliseService {
    @Autowired
    private AnaliseRepository repository;

    public AnaliseService(AnaliseRepository repository){
        this.repository = repository;
    }

    public Analise save(Analise analiseSave){
        return repository.save(analiseSave);
    }

    public List<Analise> listarAnalises(){
        return repository.findAll();
    }

    public Optional<Analise> listarId(Long id){
        return repository.findById(id);
    }
}
