package com.github.kntmr;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("customerService")
public class CustomerService extends AbstractService {

    @Transactional
    @Override
    public void insert(String... names) {
        super.insert(names);
    }

}
