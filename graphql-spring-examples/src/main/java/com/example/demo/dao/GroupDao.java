package com.example.demo.dao;

import com.example.demo.type.Group;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class GroupDao {

    private List<Group> groups = Arrays.asList(
            new Group(1, "Group A"),
            new Group(2, "Group B"),
            new Group(3, "Group C")
    );

    public Optional<Group> findById(int id) {
        return groups.stream().filter(g -> g.getId() == id).findFirst();
    }

}
