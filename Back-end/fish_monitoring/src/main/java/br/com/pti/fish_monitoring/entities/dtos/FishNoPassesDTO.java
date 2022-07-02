package br.com.pti.fish_monitoring.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishNoPassesDTO {
    private Long id;
    
    private LocalDateTime registryDate;

    private String pittag;

    private String scientificName;

    private Integer totalLength;

    private String captureLocation;

    private Integer releaseWeight;

    private LocalDateTime releaseDate;

    private String releaseLocation;

    private String dnaSample;

    private Boolean recapture;
}
