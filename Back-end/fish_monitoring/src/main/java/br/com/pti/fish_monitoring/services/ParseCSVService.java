package br.com.pti.fish_monitoring.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;

import org.aspectj.weaver.ast.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import br.com.pti.fish_monitoring.entities.dtos.PassFileDTO;


@Service
public class ParseCSVService {

    public List<PassFileDTO> parse(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
    
        CsvToBean<PassFileDTO> csvToBean = new CsvToBeanBuilder(csvReader)
                                                .withSeparator(',')
                                                .withType(PassFileDTO.class)
                                                .build();
    
        List<PassFileDTO> dtos = csvToBean.parse();

        return dtos;
    }
}