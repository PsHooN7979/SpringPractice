package org.example.userspring.repository;


import org.example.userspring.domain.User;

import java.util.*;

//@Repository
public class MemoryUserRepository implements UserRepository {
    private static Map<Long, User> users = new HashMap<>();
    private static long index = 0L;

    @Override
    public User add(User user) {
        user.setIndex(++index);
        users.put(user.getIndex(), user);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return users.values().stream()
                .filter(user -> user.getId().equals(id))
                .findAny();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public void clearUser() {
        users.clear();
    }
}
