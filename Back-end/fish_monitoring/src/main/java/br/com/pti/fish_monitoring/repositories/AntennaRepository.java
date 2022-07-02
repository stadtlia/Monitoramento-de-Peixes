package br.com.pti.fish_monitoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.fish_monitoring.entities.Antenna;

@Repository
public interface AntennaRepository extends JpaRepository<Antenna, Long> {
    
}
