package com.learn.ecotrack;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.learn.ecotrack.dtos.UserDto;
import com.learn.ecotrack.entities.Role;
import com.learn.ecotrack.entities.User;
import com.learn.ecotrack.enums.AppRole;
import com.learn.ecotrack.repositories.RoleRepository;
import com.learn.ecotrack.repositories.UserRepository;
import com.learn.ecotrack.services.EmailService;
import com.learn.ecotrack.services.impl.UserServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class UserTests {
	
	@Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserServiceImpl userService;

    private UserDto userDto;
    private User user;
    private Role role;

    @BeforeEach
    void setUp() {
        userDto = new UserDto();
        userDto.setEmail("test@example.com");
        userDto.setPassword("plainPassword");

        user = new User();
        user.setEmail("test@example.com");

        role = new Role();
        role.setRoleName(AppRole.ROLE_USER);
    }

    @Test
    void testRegisterUser_Success() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(roleRepository.findByRoleName(AppRole.ROLE_USER)).thenReturn(Optional.of(role));
        when(userRepository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        // Act
        UserDto result = userService.registerUser(userDto);

        // Assert
        assertNotNull(result);
        verify(passwordEncoder).encode("plainPassword");
        verify(roleRepository).findByRoleName(AppRole.ROLE_USER);
        verify(userRepository).save(user);
        verify(emailService).sendMail(
                eq("test@example.com"),
                eq("Registration Completed"),
                anyString()
        );
    }

    @Test
    void testRegisterUser_RoleNotFound() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(roleRepository.findByRoleName(AppRole.ROLE_USER)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(userDto);
        });

        assertEquals("role not found", exception.getMessage());
        verify(userRepository, never()).save(any());
     
    }

    @Test
    void testCheckEmailExists_True() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean exists = userService.checkEmailExists("test@example.com");

        assertTrue(exists);
        verify(userRepository).existsByEmail("test@example.com");
    }

    @Test
    void testCheckEmailExists_False() {
        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);

        boolean exists = userService.checkEmailExists("test@example.com");

        assertFalse(exists);
        verify(userRepository).existsByEmail("test@example.com");
    }
	

}
