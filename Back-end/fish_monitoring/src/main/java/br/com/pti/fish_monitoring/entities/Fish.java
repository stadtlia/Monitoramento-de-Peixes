package br.com.pti.fish_monitoring.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime registryDate;

    @Column(name = "pittag", nullable = false)
    private String pittag;

    @Column(nullable = false)
    private String scientificName;

    @Column(nullable = false)
    private Integer totalLength;

    @Column(nullable = false)
    private String captureLocation;

    @Column(nullable = false)
    private Integer releaseWeight;

    @Column(nullable = false)
    private LocalDateTime releaseDate;

    @Column(nullable = false)
    private String releaseLocation;

    @Column(nullable = false)
    private String dnaSample;

    @Column(nullable = true)
    private Boolean recapture;

    @Column(nullable = true)
    private Integer standardLength;

    @OneToMany(mappedBy = "fish", cascade={CascadeType.ALL})
    private List<Pass> passes;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
}
