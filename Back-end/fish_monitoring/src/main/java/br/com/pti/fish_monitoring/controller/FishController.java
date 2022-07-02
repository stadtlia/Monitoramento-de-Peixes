package br.com.pti.fish_monitoring.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.pti.fish_monitoring.services.FishService;
import br.com.pti.fish_monitoring.entities.Fish;
import br.com.pti.fish_monitoring.entities.dtos.FishDTO;
import br.com.pti.fish_monitoring.entities.dtos.FishNoPassesDTO;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/fishes")
@Slf4j
public class FishController {
    private FishService fishService;
    private ModelMapper modelMapper;
    private Boolean update = false;

    public FishController( ModelMapper modelMapper, FishService fishService) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }

    // Inserir um peixe
    @PostMapping
    public ResponseEntity<FishNoPassesDTO> create(@RequestBody Fish fish) {
        try {
        Fish savedFish = fishService.save(fish, update);
        FishNoPassesDTO fishDTO = modelMapper.map(savedFish, FishNoPassesDTO.class);
        return ResponseEntity.ok(fishDTO);
        } catch(DataIntegrityViolationException e) {
            log.error("Pittag em uso.", e);
            return ResponseEntity.badRequest().build();
        } catch(Exception e) {
            log.error("Falha durante inserção.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Inserir um bloco de peixes (/fishes/multiple)
    @PostMapping("/multiple")
    public ResponseEntity<List<FishNoPassesDTO>> create(@RequestBody List<Fish> fishes){
        try{
            List<FishNoPassesDTO> fishNoPassesDTO =  fishes
                    .stream()
                    .map(f -> modelMapper
                            .map(fishService
                                    .save(f, update), FishNoPassesDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(fishNoPassesDTO);

        }catch (Exception e){
            log.error("Falha durante inserção.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir todos os peixes com /fishes ou buscar peixes pelo nome científico com /fishes?scientificName=
    // Obrigatório
    @GetMapping
    public ResponseEntity<List<FishDTO>> findAll(
        @RequestParam(value = "scientificName", required = false) String scientificName) {
        List<Fish> fishes;
        if (scientificName != null) {
            fishes = fishService.findByScientificName(scientificName);
        } else {
            fishes = fishService.findAll();            
        }
        List<FishDTO> fishesDTO = fishes
                                .stream()
                                .map(f ->
                                 modelMapper.map(f, FishDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }

    // Exibir peixe com uma determinada ID com /fishes/id/{id}
    // Opcional
    @GetMapping("/id/{id}")
    public ResponseEntity<FishDTO> find(@PathVariable Long id) {
        try {
            Fish fish = fishService.findById(id);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir peixe com uma determinada pittag com /fishes/pittag/{pittag}
    // Obrigatório
    @GetMapping("/pittag/{pittag}")
    public ResponseEntity<FishDTO> findByPittag(@PathVariable String pittag) {
        try {
            Fish fish = fishService.findByPittag(pittag);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir peixe com uma determinada data de soltura com /fishes/release_date?releaseDate=
    // Obrigatório
    @GetMapping("/release_date")
    public ResponseEntity<List<FishDTO>> findByReleaseDate(
        @RequestParam(value = "releaseDate", required = false) LocalDateTime releaseDate) {
        List<Fish> fishes;
        if (releaseDate != null) {
            fishes = fishService.findByReleaseDate(releaseDate);
        } else {
            fishes = fishService.findAll();            
        }
        List<FishDTO> fishesDTO = fishes
                                .stream()
                                .map(f ->
                                 modelMapper.map(f, FishDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }

    // Exibir peixe com uma determinada localização de soltura com /fishes/release_location?releaseLocation=
    // Opcional
    @GetMapping("/release_location")
    public ResponseEntity<List<FishDTO>> findByReleaseLocation(
        @RequestParam(value = "releaseLocation", required = false) String releaseLocation) {
        List<Fish> fishes;
        if (releaseLocation != null) {
            fishes = fishService.findByReleaseLocation(releaseLocation);
        } else {
            fishes = fishService.findAll();            
        }
        List<FishDTO> fishesDTO = fishes
                                .stream()
                                .map(f ->
                                 modelMapper.map(f, FishDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }

    // Exibir peixe com um determinado local de caputura com /fishes/capture_location?captureLocation=
    @GetMapping("/capture_location")
    public ResponseEntity<List<FishDTO>> findByCaptureLocation(
        @RequestParam(value = "captureLocation", required = false) String captureLocation) {
        List<Fish> fishes;
        if (captureLocation != null) {
            fishes = fishService.findByCaptureLocation(captureLocation);
        } else {
            fishes = fishService.findAll();            
        }
        List<FishDTO> fishesDTO = fishes
                                .stream()
                                .map(f ->
                                 modelMapper.map(f, FishDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }

    // Exibir peixe com uma determinada amostra de dna com /fishes/dna_sample/{dnaSample}
    // Opcional
    @GetMapping("/dna_sample/{dnaSample}")
    public ResponseEntity<FishDTO> findByDnaSample(@PathVariable String dnaSample) {
        try {
            Fish fish = fishService.findByDnaSample(dnaSample);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontrado.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Exibir peixe com uma determinada amostra de dna com /fishes/recapture?recapture=
    // Opcional
    @GetMapping("/recapture")
    public ResponseEntity<List<FishDTO>> findByRecapture(
        @RequestParam(value = "recapture", required = false) Boolean recapture) {
        List<Fish> fishes;
        if (recapture != null) {
            fishes = fishService.findByRecapture(recapture);
        } else {
            fishes = fishService.findAll();            
        }
        List<FishDTO> fishesDTO = fishes
                                .stream()
                                .map(f ->
                                 modelMapper.map(f, FishDTO.class))
                                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Fish> updateById(@PathVariable Long id,
                                        @RequestBody Fish fish) {
            System.out.println("ok");
            return ResponseEntity.ok(fish);                           
        //     System.out.println("ok");try {
        //     Fish foundFish = fishService.findById(id);
        //     modelMapper.map(fish, foundFish);
        //     System.out.println("ok");
        //     fishService.save(foundFish, update);
        //     return ResponseEntity.ok(modelMapper.map(foundFish,FishDTO.class));
        // } catch(Exception e) {
        //     log.error("Falha durante atualização.", e);
        //     return ResponseEntity.notFound().build();
        // }
    }

    // Update do peixe pela pittag
    @PutMapping("/{pittag}")
    public ResponseEntity<FishDTO> update(@PathVariable String pittag,
                                        @RequestBody Fish fish) {
        System.out.println("ok" + pittag);
        try {
            Fish foundFish = fishService.findByPittag(pittag);
            modelMapper.map(fish, foundFish);
            System.out.println("(((((((((((()))))))))))" + foundFish);
            System.out.println("ok");
            fishService.save(foundFish, update);
            return ResponseEntity.ok(modelMapper.map(foundFish, FishDTO.class));
            //return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante atualização.", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar um peixe pelo seu ID /fishes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            fishService.deleteById(id); 
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção.", e);
            return ResponseEntity.notFound().build();
        }
    }
}