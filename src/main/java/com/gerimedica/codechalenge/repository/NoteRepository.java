package com.gerimedica.codechalenge.repository;

import com.gerimedica.codechalenge.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, String> {
}
