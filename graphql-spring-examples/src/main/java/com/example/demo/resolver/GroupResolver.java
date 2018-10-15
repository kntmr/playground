package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.dao.GroupDao;
import com.example.demo.type.Group;
import com.example.demo.type.User;
import org.springframework.stereotype.Component;

@Component
public class GroupResolver implements GraphQLResolver<User> {

    private GroupDao groupDao;

    public GroupResolver(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public Group group(User user) {
        return groupDao.findById(user.getGroupId()).orElse(new Group(0, "Undefined"));
    }

}
