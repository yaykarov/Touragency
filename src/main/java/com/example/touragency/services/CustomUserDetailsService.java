package com.example.touragency.services;

import com.example.touragency.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**

 Сервис для загрузки пользовательских данных для аутентификации и авторизации.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    /**

     Метод для загрузки данных пользователя по его электронной почте.
     @param email электронная почта пользователя, чьи данные необходимо загрузить.
     @return объект UserDetails, содержащий информацию о пользователе, или исключение UsernameNotFoundException,
     если пользователь не найден.
     @throws UsernameNotFoundException если пользователь с переданной электронной почтой не найден.
     */

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        return userRepository.findByEmail(email);
    }

}
