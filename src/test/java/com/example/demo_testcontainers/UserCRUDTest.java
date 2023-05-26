package com.example.demo_testcontainers;


import com.example.demo_testcontainers.users.User;
import com.example.demo_testcontainers.users.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

@SpringBootTest
public class UserCRUDTest {
    @Autowired
    UserService userService;
    UUID id;

    @AfterEach
    public void tearDown() {
        if (id != null) {
            userService.deleteUserById(id);
        }
    }

    @Test
    @Description("пользователь проверка не является пустым")
    public void userCheckIsNotEmpty() {
        List<User> userList = userService.loadAllUsers();
        assertThat(userList.size()).isNotNull();
    }

    @Test
    @Description("Проверяем наличие пользователя по id")
    public void checkUserAvailabilityById() {
        User user = userService.createUser("oirur", "86439");
        id = user.getId();
        assertThat(userService.loadUserById(id)).isEqualTo(user);
    }

    @Test
    @Description("Проверка удаления пользователя по идентификатору")
    public void CheckUserDeletionById() {
        User currentUser = userService.createUser("oirur", "86439");
        UUID currentId = currentUser.getId();
        userService.deleteUserById(currentId);
        List<User> userList = userService.loadAllUsers();
        for (User user : userList) {
            assertThat(user).isNotIn(currentUser);
        }

    }

    @Test
    @Description("Проверка исключений при выбросах при отсутствии пользователя")
    public void CheckEjectionExceptionsWhenUnloadingNotExistingUser() {
        UUID invalidId = UUID.randomUUID();
        Throwable thrown = catchThrowable(() -> {
            userService.loadUserById(invalidId);
        });
        assertThat(thrown).isInstanceOf(ResponseStatusException.class);
        assertThat(thrown.getMessage()).isEqualTo("404 NOT_FOUND");
    }

    @Test
    @Description("проверка создания пользователя")
    public void checkUserCreation() {
        User user = userService.createUser("Ivan", "983654");
        id = user.getId();
        assertThat(userService.loadUserById(id)).isEqualTo(user);
    }

    @Test
    @Description("проверка редактирования пользователя")
    public void checkUserEdition() {
        User user = userService.createUser("Ivan", "983654");
        id = user.getId();
        userService.editUser(id, "editLogin", "editEmail");
        User editUser = userService.loadUserById(id);
        assertThat(editUser.getLogin()).isEqualTo("editLogin");
        assertThat(editUser.getEmail()).isEqualTo("editEmail");
    }
}
