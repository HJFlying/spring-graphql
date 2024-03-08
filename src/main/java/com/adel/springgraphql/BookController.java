package com.adel.springgraphql;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    @QueryMapping
    public Book findBookById(@Argument String id){
        return Book.getById(id);
    }

    @QueryMapping
    public List<Book> findAllBooks(){
        return Book.getBooks();
    }

    @SchemaMapping
    public Author author(Book book){
        return Author.getById(book.getAuthorId());
    }

}
