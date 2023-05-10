package com.example.touragency.services;

import com.example.touragency.models.User;
import com.example.touragency.models.enums.Role;
import com.example.touragency.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**

 Сервис для работы с пользователями.
 */
@Service
@Slf4j
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
    Метод для создания нового пользователя.
    @param user - объект пользователя, которого нужно сохранить.
     @return true, если пользователь был успешно создан,
     @false - если пользователь с таким email уже существует.
     */
    public boolean createUser(User user) {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(Role.ROLE_USER);
        log.info("Saving new User with email: {}", email);
        userRepository.save(user);
        return true;
    }

    /**
     Метод для получения списка всех пользователей.
     @return список всех пользователей.
     */

    public List<User> list() {
        return userRepository.findAll();
    }
    /**

     Метод для блокировки/разблокировки пользователя.
     @param id - идентификатор пользователя, которого нужно заблокировать/разблокировать.
     */
    public void banUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            if (user.isActive()) {
                user.setActive(false);
                log.info("Ban user with id = {}; email: {}", user.getId(), user.getEmail());
            } else {
                user.setActive(true);
                log.info("Unban user with id = {}; email: {}", user.getId(), user.getEmail());
            }
        }
        userRepository.save(user);
    }
    /**
     Метод для изменения ролей пользователя.
     @param user - объект пользователя, роли которого нужно изменить.
     @param form - Map, содержащая новые роли пользователя.
     */
    public void changeUserRoles(User user, Map<String, String> form) {
        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }
}