package com.example.touragency.controllers;
import com.example.touragency.models.User;
import com.example.touragency.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // Обработчик GET запроса на страницу логина
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Обработчик GET запроса на страницу регистрации
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    // Обработчик POST запроса на создание пользователя
    @PostMapping("/registration")
    public String createUser(User user, Model model) {
        // Если создание пользователя не удалось, возвращаем страницу регистрации с сообщением об ошибке
        if (!userService.createUser(user)) {
            model.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        // В случае успешного создания пользователя перенаправляем на страницу логина
        return "redirect:/login";
    }
    // Обработчик GET запроса на страницу с информацией о пользователе
    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, Model model){
        model.addAttribute("user", user);
        model.addAttribute("products", user.getProducts());
        return "user-info";
    }
}
