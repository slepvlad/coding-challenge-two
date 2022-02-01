package com.gerimedica.codechalenge.service;

import com.gerimedica.codechalenge.domain.Note;
import com.gerimedica.codechalenge.mapper.NoteMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class CsvServiceImpl implements CsvService {

    private final NoteMapper noteMapper;

    @Override
    public List<Note> getNotes(InputStream fileInputStream) {

        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Note> notes = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {

                Note note = noteMapper.convert(csvRecord);
                log.debug("Read note: {}", note);
                notes.add(note);
            }
            return notes;
        } catch (IOException e) {
            log.error("fail to parse CSV file: " + e.getMessage());
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
