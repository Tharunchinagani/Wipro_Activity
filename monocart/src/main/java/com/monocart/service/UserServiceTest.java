package com.monocart.service;

import com.monocart.entity.User;
import com.monocart.repository.UserRepository;
import com.monocart.service.UserServiceImpl;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUser() {
        User user = new User(null, "Ramya", "ramya@example.com", "India");

        when(userRepository.save(user)).thenReturn(new User(1L, "Ramya", "ramya@example.com", "India"));

        User created = userService.createUser(user);

        assertNotNull(created);
        assertEquals("Ramya", created.getName());
        assertEquals("ramya@example.com", created.getEmail());
    }

    @Test
    void testGetAllUsers() {
        List<User> mockList = Arrays.asList(
                new User(1L, "Alice", "alice@gmail.com", "Hyd"),
                new User(2L, "Bob", "bob@gmail.com", "Blr")
        );

        when(userRepository.findAll()).thenReturn(mockList);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    void testUpdateUser() {
        User existing = new User(1L, "Old", "old@gmail.com", "City");
        User updated = new User(null, "New", "new@gmail.com", "NewCity");

        when(userRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(userRepository.save(any(User.class))).thenReturn(
                new User(1L, "New", "new@gmail.com", "NewCity"));

        User result = userService.updateUser(1L, updated);

        assertEquals("New", result.getName());
        assertEquals("new@gmail.com", result.getEmail());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        userService.deleteUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
}
