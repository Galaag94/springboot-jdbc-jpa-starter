package com.accenture.books.dtos.mappers;


import com.accenture.books.dtos.BookDTO;
import com.accenture.books.entities.BookEntity;
import com.accenture.books.domain.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookMapper(AuthorMapper authorMapper) {
        this.authorMapper = authorMapper;
    }

    public BookDTO toDTO (Book model) {
        if (model == null) {
            return null;
        }

        var authorDTO = authorMapper.toDTO(model.getAuthor());
        return new BookDTO(model.getIsbn(), model.getTitle(), authorDTO);
    }

    public BookDTO toDTO(BookEntity entity) {
        if (entity == null) {
            return null;
        }

        var authorDTO = authorMapper.toDTO(entity.getAuthor());
        return new BookDTO(entity.getIsbn(), entity.getTitle(), authorDTO);
    }

    public Book toModel(BookDTO dto) {
        if (dto == null) {
            return null;
        }

        var author = authorMapper.toModel(dto.author());
        return new Book(dto.isbn(), dto.title(), author);
    }

    public BookEntity toEntity(BookDTO dto) {
        if (dto == null) {
            return null;
        }

        var authorDTO = authorMapper.toEntity(dto.author());
        return new BookEntity(dto.isbn(), dto.title(), authorDTO);
    }
}
