package com.example.touragency.models.enums;

import org.springframework.security.core.GrantedAuthority;


/**

 Это перечисление представляет различные роли, которые пользователь может иметь в системе. Роль используется для целей авторизации.
 Полномочия ROLE_USER предоставляются всем зарегистрированным пользователям, в то время как полномочия ROLE_ADMIN предоставляются пользователям с правами администратора.
 */
public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
