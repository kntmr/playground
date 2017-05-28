package com.github.kntmr;

import java.util.List;

public interface BaseService {
    //@Transactional // => no rollback
    void register(String... names);
    List<String> findAll();
}
