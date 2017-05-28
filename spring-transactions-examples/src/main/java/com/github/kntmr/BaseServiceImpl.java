package com.github.kntmr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class BaseServiceImpl implements BaseService {

    @Autowired
    protected CustomerDao customerDao;

    @Transactional // => rollback
    @Override
    public void register(String... names) {
        customerDao.insert(names);
    }

    @Override
    public List<String> findAll() {
        return customerDao.findAll();
    }

}
