package com.gerimedica.codechalenge.service;

import com.gerimedica.codechalenge.domain.Note;

import java.io.InputStream;
import java.util.List;

public interface CsvService {

    List<Note> getNotes(InputStream file);
}
