package br.com.pti.fish_monitoring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.fish_monitoring.entities.StatusAntenna;

@Repository
public interface StatusAntennaRepository  extends JpaRepository<StatusAntenna, Long>{

}
