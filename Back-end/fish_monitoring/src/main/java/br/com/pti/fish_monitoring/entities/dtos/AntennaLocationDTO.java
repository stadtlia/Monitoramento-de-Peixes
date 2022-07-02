package br.com.pti.fish_monitoring.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AntennaLocationDTO {

    private Long id;

    private String latitude;

    private String longitude;

}
