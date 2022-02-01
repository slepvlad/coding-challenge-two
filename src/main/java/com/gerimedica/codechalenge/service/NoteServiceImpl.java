package com.gerimedica.codechalenge.service;

import com.gerimedica.codechalenge.domain.Note;
import com.gerimedica.codechalenge.dto.NoteDto;
import com.gerimedica.codechalenge.exception.BadRequestException;
import com.gerimedica.codechalenge.exception.EntityNotFound;
import com.gerimedica.codechalenge.mapper.NoteMapper;
import com.gerimedica.codechalenge.repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {

    private static final String EXTENSION = "text/csv";

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final CsvService csvService;

    @Override
    public List<NoteDto> fetchAll() {
        return noteRepository.findAll().stream()
                .map(noteMapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public NoteDto fetchByCode(String code) {
        Note note = noteRepository.findById(code).orElseThrow(() ->
                new EntityNotFound("Not found entity with code: "+ code));
        return noteMapper.convert(note);
    }

    @Override
    public void deleteAllData() {
        noteRepository.deleteAll();
    }

    @Override
    public void upload(MultipartFile file) {
        if(!EXTENSION.equals(file.getContentType())) {
            throw new BadRequestException();
        }
        try {
            List<Note> notes = csvService.getNotes(file.getInputStream());
            noteRepository.saveAll(notes);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }
}
