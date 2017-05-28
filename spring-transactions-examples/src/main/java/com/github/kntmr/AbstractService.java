package com.github.kntmr;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractService {

    @Autowired
    protected CustomerDao customerDao;

    //@Transactional // => no rollback
    public void register(String... names) {
        customerDao.insert(names);
    }

    public List<String> findAll() {
        return customerDao.findAll();
    }

}
