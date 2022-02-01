package com.gerimedica.codechalenge.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NoteDto {
    private String code;
    private String source;
    private String codeListCode;
    private String displayName;
    private String longDescription;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer sortingPriority;
}
