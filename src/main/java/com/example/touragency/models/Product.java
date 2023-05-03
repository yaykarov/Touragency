package com.example.touragency.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity //аннотация для обозначения, что это сущность JPA, которая будет сохраняться в базу данных.
@Table(name = "products") //аннотация для указания таблицы базы данных, в которой будет храниться данная сущность.
@Data //аннотация, генерирующая геттеры и сеттеры, а также методы equals, hashCode и toString.
@AllArgsConstructor //аннотация для создания конструктора с аргументами для всех полей класса
@NoArgsConstructor //аннотация для создания конструктора без аргументов.
public class Product {
    @Id //аннотация, обозначающая, что поле является идентификатором сущности.
    @GeneratedValue(strategy = GenerationType.AUTO) //аннотация для указания, что значение поля id будет генерироваться автоматически.
    @Column(name = "id") //аннотация для указания, что поле соответствует столбцу таблицы.
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "city")
    private String city;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn
    private User user;
    private LocalDateTime dateofCreated;

    @PrePersist
    private void init() {
        dateofCreated = LocalDateTime.now();
    }

}