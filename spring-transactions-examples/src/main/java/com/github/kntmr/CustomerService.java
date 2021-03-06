package com.github.kntmr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class CustomerService {

    @Autowired
    protected CustomerDao customerDao;

    @Transactional // => rollback
    public void register(String... names) {
        customerDao.insert(names);
    }

    public List<String> findAll() {
        return customerDao.findAll();
    }

}
