package com.project.estimate_value.controller;

import com.project.estimate_value.model.Analise;
import com.project.estimate_value.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AnaliseController {
    @Autowired
    private AnaliseService service;

    public AnaliseController(AnaliseService service){
        this.service = service;
    }

    @PostMapping("/analises")
    public Analise criar(@RequestBody Analise analiseCriar){
        return service.save(analiseCriar);
    }

    @GetMapping("/listar-analises")
    public List<Analise> listar(){
        return service.listarAnalises();
    }

    @GetMapping("/listar-analises/{id}")
    public ResponseEntity<Analise> buscarPorId(@PathVariable Long id){
        return service.listarId(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
