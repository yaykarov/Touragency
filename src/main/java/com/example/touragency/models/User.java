package com.example.touragency.models;

import com.example.touragency.models.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;
import javax.persistence.*;

/**

 Класс, представляющий сущность пользователя в системе.
 */
@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {
    /**

     Идентификатор пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**

     Электронная почта пользователя.
     */
    @Column(name = "email", unique = true)
    private String email;
    /**

     Номер телефона пользователя.
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**

     Имя пользователя.
     */
    @Column(name = "name")
    private String name;
    /**

     Флаг, указывающий на активность пользователя.
     */
    @Column(name = "active")
    private boolean active;
    /**

     Хэш пароля пользователя.
     */
    @Column(name = "password", length = 1000)
    private String password;
    /**
     Роли пользователя.
     */
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
    /**

     Список продуктов пользователя.
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    private List<Product> products = new ArrayList<>();
    /**

     Дата создания пользователя.
     */
    private LocalDateTime dateOfCreated;

    /**

     Метод, вызываемый перед сохранением пользователя в базу данных.
     Устанавливает дату создания пользователя на текущую дату.
     */
    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    // security
    /**

     Метод, возвращающий true, если у пользователя есть роль администратора.
     @return true, если у пользователя есть роль администратора, иначе false.
     */
    public boolean isAdmin() {
        return roles.contains(Role.ROLE_ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}