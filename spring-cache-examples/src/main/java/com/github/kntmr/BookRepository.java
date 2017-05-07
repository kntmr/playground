package com.github.kntmr;

public interface BookRepository {

    Book getByIsbn(String isbn);

}
