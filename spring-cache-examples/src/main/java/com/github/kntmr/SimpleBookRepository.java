package com.github.kntmr;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class SimpleBookRepository implements BookRepository {

    @Cacheable("books")
    @Override
    public Book getByIsbn(String isbn) {
        doDummy();
        return new Book(isbn, "Book-" + isbn);
    }

    private void doDummy() {
        try {
            long time = 3000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
