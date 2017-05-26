package com.github.kntmr;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractService {

    @Autowired
    protected CustomerDao customerDao;

    public List<String> findAll() {
        return customerDao.findAll();
    }

    //@Transactional
    public void insert(String... names) {
        customerDao.insert(names);
    }

}
