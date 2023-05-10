package com.example.touragency.controllers;

import com.example.touragency.models.User;
import com.example.touragency.models.enums.Role;
import com.example.touragency.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**Контроллер для страниц администратора.
 */

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    /**
     Отображение страницы администратора со списком пользователей.
     param model модель для передачи данных на страницу
     return имя представления для отображения страницы
     */

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("users", userService.list());
        return "admin";
    }
    /**

     Обработка запроса на блокировку пользователя.
     param id идентификатор пользователя для блокировки
     return перенаправление на страницу администратора
     */

    @PostMapping("/admin/user/ban/{id}")
    public String userBan(@PathVariable("id") Long id) {
        userService.banUser(id);
        return "redirect:/admin";
    }
    /**

     Обработка запроса на изменение ролей пользователя.
     param user пользователь для изменения ролей
     param form параметры формы, содержащие новые роли пользователя
     return перенаправление на страницу администратора
     */

    @GetMapping("/admin/user/edit/{user}")
    public String userEdit(@PathVariable("user") User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    /**

     Отображение страницы редактирования пользователя.
     @param user пользователь для редактирования
     @return имя представления для отображения страницы
     */

    @PostMapping("/admin/user/edit")
    public String userEdit(@RequestParam("userId") User user, @RequestParam Map<String, String> form) {
        userService.changeUserRoles(user, form);
        return "redirect:/admin";
    }
}