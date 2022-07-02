package br.com.pti.fish_monitoring.entities.dtos;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaDTO {
    private Long id;

    private LocalDateTime registryDate;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime desativationDate;

    private List<PassDTO> passes;

    private List<StatusAntennaDTO> statusAntennas;

}
