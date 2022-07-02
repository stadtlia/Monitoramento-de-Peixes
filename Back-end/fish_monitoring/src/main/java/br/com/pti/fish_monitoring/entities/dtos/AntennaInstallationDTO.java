package br.com.pti.fish_monitoring.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AntennaInstallationDTO {

    private Long id;

    private LocalDateTime installationDate;

    private LocalDateTime desativationDate;
}