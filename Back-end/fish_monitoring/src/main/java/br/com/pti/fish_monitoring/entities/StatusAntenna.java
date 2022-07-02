package br.com.pti.fish_monitoring.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "statusAntennas")
public class StatusAntenna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDateTime registryDate;

    @ManyToOne
    @JoinColumn(name = "fk_antenna")
    private Antenna antenna;

    @Column(nullable = false)
    private Boolean status;

    @Column(nullable = true)
    private LocalDateTime statusChangeDate;
    

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
    
}
