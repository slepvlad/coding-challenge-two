package com.gerimedica.codechalenge.mapper;

import com.gerimedica.codechalenge.domain.Note;
import com.gerimedica.codechalenge.dto.NoteDto;
import org.apache.commons.csv.CSVRecord;
import org.mapstruct.Mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.springframework.util.StringUtils.hasLength;

@Mapper(componentModel = "spring")
public interface NoteMapper {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    NoteDto convert(Note note);

    default Note convert(CSVRecord csvRecord) {
        if(csvRecord == null) {
            return null;
        }
        Note note = new Note();
        note.setSource(csvRecord.get(0));
        note.setCodeListCode(csvRecord.get(1));
        note.setCode(csvRecord.get(2));
        note.setDisplayValue(csvRecord.get(3));
        note.setLongDescription(csvRecord.get(4));
        note.setFromDate(parse(csvRecord.get(5)));
        note.setToDate(parse(csvRecord.get(6)));
        note.setSortingPriority(hasLength(csvRecord.get(7)) ? Integer.parseInt(csvRecord.get(7)) : null);
    return note;
    }

    private LocalDate parse(String stringDate) {
        if (!hasLength(stringDate)) {
            return null;
        }
        return LocalDate.parse(stringDate, formatter);
    }
}
