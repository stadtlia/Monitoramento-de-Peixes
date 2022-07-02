package br.com.pti.fish_monitoring.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pti.fish_monitoring.entities.Fish;

@Repository
public interface FishRepository extends JpaRepository<Fish, Long>{
    Optional<Fish> findByPittag(String pittag);
    List<Fish> findByScientificName(String scientificName);
    List<Fish> findByReleaseDate(LocalDateTime releaseDate);
    List<Fish> findByCaptureLocation(String captureLocation);
    List<Fish> findByReleaseLocation(String releaseLocation);
    Optional<Fish> findByDnaSample(String dnaSample);
    List<Fish> findByRecapture(Boolean recapture);
    
    Optional<Fish> findTopByPittagOrderByIdDesc(String pittag);
    List<Fish> findAllByPittagOrderByIdDesc(String pittag);
}
