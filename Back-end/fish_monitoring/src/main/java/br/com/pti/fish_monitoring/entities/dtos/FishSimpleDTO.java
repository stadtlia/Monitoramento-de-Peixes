package br.com.pti.fish_monitoring.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FishSimpleDTO {
    private Long id;

    private String pittag;

    private String scientificName;

}
