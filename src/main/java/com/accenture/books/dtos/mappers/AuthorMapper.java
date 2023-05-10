package com.accenture.books.dtos.mappers;

import com.accenture.books.dtos.AuthorDTO;
import com.accenture.books.entities.AuthorEntity;
import com.accenture.books.domain.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO(Author model) {
        if (model == null) {
            return null;
        }

        return new AuthorDTO(model.getId(), model.getName(), model.getAge());
    }

    public AuthorDTO toDTO(AuthorEntity entity) {
        if (entity == null) {
            return null;
        }

        return new AuthorDTO(entity.getId(), entity.getName(), entity.getAge());
    }

    public Author toModel(AuthorDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Author(dto.id(), dto.name(), dto.age());
    }

    public AuthorEntity toEntity(AuthorDTO dto) {
        if (dto == null) {
            return null;
        }

        return new AuthorEntity(dto.id(), dto.name(), dto.age());
    }
}
