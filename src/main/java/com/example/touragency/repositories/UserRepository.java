package com.example.touragency.repositories;

import com.example.touragency.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**

 Интерфейс для работы с хранилищем данных пользователей.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**

     Метод для поиска пользователя по электронной почте.
     @param email электронная почта пользователя.
     @return объект User, соответствующий переданной электронной почте, или null, если такой пользователь не найден.
     */
    User findByEmail(String email);
}
