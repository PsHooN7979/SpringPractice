package org.example.userspring.service;


import jakarta.transaction.Transactional;
import org.example.userspring.domain.User;
import org.example.userspring.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional //db에 반영이 안됨
public class UserServiceIntegrationTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void join() {
        //given
        User user = new User();
        user.setId("dcu5");
        user.setPassword("1234");

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
        user1.setPassword("1234");
        User user2 = new User();
        user2.setId("dcu");
        user2.setPassword("1234");

        userService.join(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () ->
                userService.join(user2)
        );
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 사용자입니다.");
    }
}
