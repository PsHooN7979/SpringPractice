package org.example.userspring.service;

import org.example.userspring.domain.User;
import org.example.userspring.repository.MemoryUserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    MemoryUserRepository userRepository;
    UserService userService;

    @BeforeEach
    public void beforeEach() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    public void afterEach() {
        userRepository.clearUser();
    }

    // 회원 가입
    @Test
    void join() {
        //given
        User user = new User();
        user.setId("dcu");

        //when
        String id = userService.join(user);

        //then
        User findUser = userService.findOne(id).get();
        assertThat(user.getId()).isEqualTo(findUser.getId());
    }

    @Test
    public void duplicateUser() throws Exception {
        User user1 = new User();
        user1.setId("dcu");
        User user2 = new User();
        user2.setId("dcu");

        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                userService.join(user2)
                );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 사용자입니다.");
    }
}
