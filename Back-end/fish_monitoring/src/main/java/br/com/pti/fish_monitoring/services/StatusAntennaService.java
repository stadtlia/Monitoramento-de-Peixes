package br.com.pti.fish_monitoring.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pti.fish_monitoring.entities.Antenna;
import br.com.pti.fish_monitoring.entities.StatusAntenna;
import br.com.pti.fish_monitoring.entities.dtos.StatusAntennaFormDTO;
import br.com.pti.fish_monitoring.repositories.StatusAntennaRepository;

@Service
@Transactional
public class StatusAntennaService {
    private final StatusAntennaRepository statusAntennaRepository;
    private final AntennaService antennaService;

    public StatusAntennaService(StatusAntennaRepository statusAntennaRepository, AntennaService antennaService) {
        this.statusAntennaRepository = statusAntennaRepository;
        this.antennaService = antennaService;
    }

    public StatusAntenna create(StatusAntennaFormDTO statusAntennaDTO) {
        Antenna foundAntenna = antennaService.findById(statusAntennaDTO.getAntennaId());

        StatusAntenna statusAntenna = new StatusAntenna();

        statusAntenna.setRegistryDate(statusAntennaDTO.getRegistryDate());

        statusAntenna.setStatus(statusAntennaDTO.getStatus());

        statusAntenna.setStatusChangeDate(statusAntennaDTO.getStatusChangeDate());

        foundAntenna.getStatusAntennas().add(statusAntenna);

        statusAntenna.setAntenna(foundAntenna);

        return statusAntennaRepository.save(statusAntenna);
    }

    public List<StatusAntenna> findAll() {
        return statusAntennaRepository.findAll();
    }
    public StatusAntenna findById(Long id) {
        Optional<StatusAntenna> optionalStatusAntenna = statusAntennaRepository.findById(id);
        if (optionalStatusAntenna.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalStatusAntenna.get();
    }
}