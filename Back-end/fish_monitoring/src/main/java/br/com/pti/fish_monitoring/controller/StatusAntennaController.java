package br.com.pti.fish_monitoring.controller;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.pti.fish_monitoring.entities.StatusAntenna;
import br.com.pti.fish_monitoring.entities.dtos.StatusAntennaDTO;
import br.com.pti.fish_monitoring.entities.dtos.StatusAntennaFormDTO;
import br.com.pti.fish_monitoring.services.StatusAntennaService;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/status")
@RestController
@Slf4j
public class StatusAntennaController {

    private final StatusAntennaService statusAntennaService;
    private final ModelMapper modelMapper;

    public StatusAntennaController(StatusAntennaService statusAntennaService, ModelMapper modelMapper) {
        this.statusAntennaService = statusAntennaService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<StatusAntennaDTO> create(@RequestBody StatusAntennaFormDTO statusAntenna){
        try {
            StatusAntenna savedStatusAntenna = statusAntennaService.create(statusAntenna);
            StatusAntennaDTO statusAntennaDTO = modelMapper.map(savedStatusAntenna, StatusAntennaDTO.class);
            return ResponseEntity.ok(statusAntennaDTO);
        } catch(Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<StatusAntennaDTO>> getAll(){

        List<StatusAntenna> statusAntennas = statusAntennaService.findAll();

        List<StatusAntennaDTO> statusAntennaDTOS = statusAntennas
                .stream()
                .map(p -> modelMapper.map(p, StatusAntennaDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(statusAntennaDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusAntennaDTO> getById(@PathVariable Long id){
        try {
            StatusAntenna statusAntenna = statusAntennaService.findById(id);
            return ResponseEntity.ok(modelMapper.map(statusAntenna,StatusAntennaDTO.class));
        } catch(Exception e) {
            log.error("Status não encontrado", e);
            return ResponseEntity.notFound().build();
        }
    }

}