package com.example.touragency.controllers;
import com.example.touragency.models.User;
import com.example.touragency.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Контроллер, отвечающий за обработку запросов, связанных с пользователем.
 */
@Controller
@RequiredArgsConstructor
public class UserController {
    /**
     * Сервис, отвечающий за операции с пользователями.
     */
    private final UserService userService;

    /**
     * Обработчик GET запроса для страницы авторизации пользователя.
     *
     * @return Возвращает имя представления, которое будет использоваться для отображения страницы авторизации.
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Обработчик GET запроса для страницы регистрации нового пользователя.
     *
     * @return Возвращает имя представления, которое будет использоваться для отображения страницы регистрации.
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }
    /**
     * Обработчик POST запроса для создания нового пользователя.
     *
     * @param user  Новый пользователь, который будет создан.
     * @param model Модель, которая используется для передачи данных в представление.
     * @return Возвращает имя представления, которое будет использоваться для отображения страницы авторизации, если пользователь был успешно создан.
     * Если пользователь уже существует, то возвращает имя представления для отображения сообщения об ошибке.
     */
    @PostMapping("/registration")
    public String createUser(User user, Model model) {

        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }

        return "redirect:/login";
    }
    /**
     * Обработчик GET запроса для страницы информации о пользователе.
     *
     * @param user  Пользователь, информация о котором будет отображена.
     * @param model Модель, которая используется для передачи данных в представление.
     * @return Возвращает имя представления, которое будет использоваться для отображения страницы информации о пользователе.
     */
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
}
