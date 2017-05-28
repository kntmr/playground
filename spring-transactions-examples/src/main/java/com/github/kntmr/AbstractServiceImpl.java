package com.github.kntmr;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AbstractServiceImpl extends AbstractService {

    @Transactional // => rollback
    @Override
    public void register(String... names) {
        super.register(names);
    }

}
