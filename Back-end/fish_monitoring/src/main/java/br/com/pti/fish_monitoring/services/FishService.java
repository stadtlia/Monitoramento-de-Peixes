package br.com.pti.fish_monitoring.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.pti.fish_monitoring.entities.Fish;
import br.com.pti.fish_monitoring.repositories.FishRepository;

@Service
@Transactional
public class FishService {
    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository){
        this.fishRepository = fishRepository;        
    }

    public Fish save(Fish fish, Boolean update){

        // Optional<Fish> optionalFish = fishRepository.findTopByPittagOrderByIdDesc(fish.getPittag());

        // if(optionalFish.isPresent()) {

        //     Fish foundFish = optionalFish.get();

        //     if(!update){
        //         if(foundFish.getPittag().equals(fish.getPittag()) 
        //                 && fish.getRecapture() != foundFish.getRecapture()){
        //             throw new DataIntegrityViolationException("Peixe existente e não houve recaptura");
        //         }
        //     }
        // }
        return fishRepository.save(fish);
    }

    public List<Fish> findAll(){
        return fishRepository.findAll();
    }

    public Fish findById(Long id) {
        Optional<Fish> optFish = fishRepository.findById(id);
        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public Fish findByPittag(String pittag){
        Optional<Fish> optFish = fishRepository.findByPittag(pittag);
        if(optFish.isEmpty()){
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public List<Fish> findByScientificName(String scientificName){
        return fishRepository.findByScientificName(scientificName);
    }

    public List<Fish> findByReleaseDate(LocalDateTime releaseDate){
        return fishRepository.findByReleaseDate(releaseDate);
    }

    public List<Fish> findByCaptureLocation(String captureLocation){
        return fishRepository.findByCaptureLocation(captureLocation);
    }

    public List<Fish> findByReleaseLocation(String releaseLocation){
        return fishRepository.findByReleaseLocation(releaseLocation);             
    }

    public Fish findByDnaSample(String dnaSample){
        Optional<Fish> optFish = fishRepository.findByDnaSample(dnaSample);
        if(optFish.isEmpty()){
            throw new NoSuchElementException();
        }
        return optFish.get();        
    }

    public List<Fish> findByRecapture(Boolean recapture){
        return fishRepository.findByRecapture(recapture);
    }

    public void deleteById(Long id) {
        Fish fish = findById(id);
        fishRepository.delete(fish);
    }
}
