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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    
    @Column(nullable = false)
    private LocalDateTime registryDate;
    
    @ManyToOne
    @JoinColumn(name = "fk_antenna")
    private Antenna antenna;

    @ManyToOne
    @JoinColumn(name = "fk_fish")
    private Fish fish;

    @PrePersist
    public void create() {
        registryDate = LocalDateTime.now();
    }
}
