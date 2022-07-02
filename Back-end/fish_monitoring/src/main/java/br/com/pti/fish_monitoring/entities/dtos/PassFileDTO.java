package br.com.pti.fish_monitoring.entities.dtos;

import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassFileDTO {
    @CsvBindByName(column = "antennaId")
    private Long antennaId;

    @CsvBindByName(column = "fishPittag")
    private String fishPittag;
    
    @CsvBindByName(column = "registryDate")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registryDate;
    
}