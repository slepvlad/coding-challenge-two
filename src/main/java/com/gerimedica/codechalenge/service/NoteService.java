package com.gerimedica.codechalenge.service;

import com.gerimedica.codechalenge.dto.NoteDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NoteService {


    List<NoteDto> fetchAll();

    NoteDto fetchByCode(String code);

    void deleteAllData();

    void upload(MultipartFile file);
}
