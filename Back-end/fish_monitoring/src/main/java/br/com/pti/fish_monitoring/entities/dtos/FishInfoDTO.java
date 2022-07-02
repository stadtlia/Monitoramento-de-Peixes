package br.com.pti.fish_monitoring.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishInfoDTO {
    private Long id;

    private String scientificName;

    private String popularName;

    private Integer standardLength;

    private Integer standardWeight;
    
}
