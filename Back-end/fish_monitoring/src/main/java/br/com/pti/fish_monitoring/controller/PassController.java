package br.com.pti.fish_monitoring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.pti.fish_monitoring.entities.Pass;
import br.com.pti.fish_monitoring.entities.dtos.PassDTO;
import br.com.pti.fish_monitoring.entities.dtos.PassFileDTO;
import br.com.pti.fish_monitoring.entities.dtos.PassFormDTO;
import br.com.pti.fish_monitoring.services.PassService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/passes")
@RestController
@Slf4j
public class PassController {
    
    private PassService passService;
    private ModelMapper modelMapper;

    public PassController(PassService passService, ModelMapper modelMapper) {
        this.passService = passService;
        this.modelMapper = modelMapper;
    }

    // Inserir uma passagem
    @PostMapping
    public ResponseEntity<PassDTO> create(@RequestBody PassFormDTO pass){
        try {
            Pass savedPass = passService.create(pass);
            PassDTO passDTO = modelMapper.map(savedPass, PassDTO.class);
            return ResponseEntity.ok(passDTO);
        } catch(Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    // Inserir passagens por um arquivo .csv
    @PostMapping("/upload")
    public ResponseEntity<List<PassDTO>> createFromCSV(@RequestParam("csvFile") MultipartFile file) {
         try {
            List<Pass> passes = passService.upload(file);
            List<PassDTO> passesDTO = passes.stream()
                    .map(p -> modelMapper.map(p,PassDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(passesDTO);
        } catch(Exception e) {
            log.error("" + e.getMessage());
           return ResponseEntity.status(400).build();
        }
    }
        
    // Exibir todas as passagens
    @GetMapping
    public ResponseEntity<List<PassDTO>> getAll(){
        List<Pass> passes = passService.findAll();
        List<PassDTO> passesDtos = passes
                                .stream()
                                .map(p -> modelMapper.map(p, PassDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(passesDtos);
    }

    // Exibir passagens pelo ID com /passes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<PassDTO> getById(@PathVariable Long id){
        try {
            Pass pass = passService.findById(id);
            return ResponseEntity.ok(modelMapper.map(pass,PassDTO.class));
        } catch(Exception e) {
            log.error("Passagem não encontrada", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar uma passagem pelo seu ID /passes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            passService.deleteById(id); 
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção.", e);
            return ResponseEntity.notFound().build();
        }
    }
}
