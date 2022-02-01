package com.gerimedica.codechalenge.controller;

import com.gerimedica.codechalenge.dto.NoteDto;
import com.gerimedica.codechalenge.service.NoteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public List<NoteDto> fetchAll() {
        return noteService.fetchAll();
    }

    @GetMapping("/{code}")
    public NoteDto fetchByCode(@PathVariable("code") String code) {
        return noteService.fetchByCode(code);
    }

    @DeleteMapping
    public void deleteAllData() {
        noteService.deleteAllData();
    }

    @PostMapping("/upload")
    public void uploadData(@RequestParam("file") MultipartFile file) {

        noteService.upload(file);
    }
}
