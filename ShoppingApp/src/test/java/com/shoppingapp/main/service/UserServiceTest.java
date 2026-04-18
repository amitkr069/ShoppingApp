package com.shoppingapp.main.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shoppingapp.main.dto.UserRequestDto;
import com.shoppingapp.main.dto.UserResponseDto;
import com.shoppingapp.main.entity.User;
import com.shoppingapp.main.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImp userService;

    @Test
    void testCreateUser_Success() {
        UserRequestDto request = new UserRequestDto();
        request.setName("Amit Kumar");
        request.setEmail("amit@example.com");
        request.setRole("CUSTOMER");

        User savedUser = new User(1L, "Amit Kumar", "amit@example.com", "CUSTOMER");

        when(userRepository.existsByEmail("amit@example.com")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        UserResponseDto result = userService.createUser(request);

        assertNotNull(result);
        assertEquals("Amit Kumar", result.getName());
        assertEquals("amit@example.com", result.getEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUser_DuplicateEmail_ThrowsException() {
        UserRequestDto request = new UserRequestDto();
        request.setEmail("duplicate@example.com");

        when(userRepository.existsByEmail("duplicate@example.com")).thenReturn(true);

        assertThrows(RuntimeException.class, () -> userService.createUser(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetAllUsers() {
        User user1 = new User(1L, "Rohit", "rohit@example.com", "CUSTOMER");
        User user2 = new User(2L, "Amit", "amit@example.com", "ADMIN");

        when(userRepository.findAll()).thenReturn(List.of(user1, user2));

        List<UserResponseDto> result = userService.getAllUsers();

        assertEquals(2, result.size());
        assertEquals("Rohit", result.get(0).getName());
        assertEquals("Amit", result.get(1).getName());
    }

    @Test
    void testGetUserById_Found() {
        User user = new User(1L, "Rohit", "rohit@example.com", "CUSTOMER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        UserResponseDto result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Rohit", result.getName());
    }

    @Test
    void testDeleteUser_Success() {
        User user = new User(1L, "Rohit", "rohit@example.com", "CUSTOMER");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        userService.deleteUser(1L);

        verify(userRepository, times(1)).delete(user);
    }
}
