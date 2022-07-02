package br.com.pti.fish_monitoring.entities.dtos;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassNoFishDTO {
    private Long id;

    private LocalDateTime registryDate;
    
    private AntennaNoPassesDTO antenna;
}
