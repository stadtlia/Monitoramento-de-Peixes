package br.com.pti.fish_monitoring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.fish_monitoring.entities.Antenna;
import br.com.pti.fish_monitoring.entities.dtos.AntennaDTO;
import br.com.pti.fish_monitoring.entities.dtos.AntennaLocationDTO;
import br.com.pti.fish_monitoring.entities.dtos.AntennaNoPassesDTO;
import br.com.pti.fish_monitoring.services.AntennaService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/antennas")
@Slf4j
public class AntennaController {
    private AntennaService antennaService;
    private ModelMapper modelMapper;

    public AntennaController(AntennaService antennaService, ModelMapper modelMapper){
        this.antennaService = antennaService;
        this.modelMapper = modelMapper;
    }

    //Inserir Antena
    @PostMapping
    public ResponseEntity<AntennaNoPassesDTO> create(@RequestBody Antenna antenna){
        try {
            Antenna saveAntenna = antennaService.save(antenna);
            AntennaNoPassesDTO antennaDTO = modelMapper.map(saveAntenna, AntennaNoPassesDTO.class);
            return ResponseEntity.ok(antennaDTO);
        } catch (Exception e) {
            log.error("Falha na inserção de uma antena",e);       
            return ResponseEntity.notFound().build();
        }
    }

    //Exibir todas as Antenas
    @GetMapping
    public ResponseEntity<List<AntennaNoPassesDTO>> findAll(){
        List<Antenna> antennas;
        antennas = antennaService.findAll();

        List<AntennaNoPassesDTO> antennaNoPassesDTOS = antennas
                .stream()
                .map(c ->
                        modelMapper.map(c, AntennaNoPassesDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(antennaNoPassesDTOS);
    }

    //Pesquisar Antena por ID
    @GetMapping("/{id}")
    public ResponseEntity<Antenna> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(antennaService.findById(id));
        } catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    //Update em caso de dado mal inserido
    @PutMapping("/{id}")
    public ResponseEntity<AntennaDTO> update(@PathVariable Long id, @RequestBody Antenna antenna){
        try {
            Antenna foundAntenna = antennaService.findById(id);
            modelMapper.map(antenna, foundAntenna);
            antennaService.save(foundAntenna);
            return ResponseEntity.ok(modelMapper.map(foundAntenna,AntennaDTO.class));
        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }
    
    //Deletar Antena
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            antennaService.deleteById(id); 
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}

