package com.example.touragency.repositories;
import com.example.touragency.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**

     Метод для поиска товаров по названию, описанию, цене и городу.
     @param title строка, которую необходимо найти в названии, описании, цене или городе товара.
     @return список объектов Product, соответствующих переданной строке, или пустой список, если товары не найдены.
     */
    @Query("SELECT i from Product i where concat(i.title, ' ', i.description, ' ', i.price, ' ', i.city) like concat('%', :title, '%', '%', '%')")
    List<Product> findByTitle(String title);
}
