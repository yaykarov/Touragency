package com.example.touragency.repositories;
import com.example.touragency.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
Jpa репозиторий для поиска по заголовку
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT i from Product i where concat(i.title, ' ', i.description, ' ', i.price, ' ', i.city) like concat('%', :title, '%', '%', '%')")
    List<Product> findByTitle(String title);
}
