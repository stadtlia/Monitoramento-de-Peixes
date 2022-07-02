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
@Table(name = "antennas")
public class Antenna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime registryDate;

    @Column(nullable = false)
    private String latitude;

    @Column(nullable = false)
    private String longitude;

    @Column(nullable = false)
    private LocalDateTime installationDate;

    @Column(nullable = true)
    private LocalDateTime desativationDate;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
    
    @OneToMany(mappedBy = "antenna", cascade = {CascadeType.ALL})
    private List<StatusAntenna> statusAntennas;

    @OneToMany(mappedBy = "antenna", cascade = {CascadeType.ALL})
    private List<Pass> passes;
}
