package com.example.touragency.services;
import com.example.touragency.models.Product;
import com.example.touragency.models.User;
import com.example.touragency.repositories.ProductRepository;
import com.example.touragency.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.security.Principal;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    //Метод, который возвращает список товаров с указанным заголовком,
    // если заголовок задан, или все товары в противном случае.
    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    //Метод, который сохраняет новый товар в базу данных.
    // Получает объект Principal для получения информации о текущем пользователе.
    public void saveProduct(Principal principal, Product product) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productRepository.save(product);
    }

    //Метод, который возвращает объект пользователя, который соответствует текущему Principal.
    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    //Метод, который удаляет товар из базы данных.
    // Получает объект пользователя и идентификатор товара, который нужно удалить.
    public void deleteProduct(User user, Long id) {
        Product product = productRepository.findById(id)
                .orElse(null);
        if (product != null) {
            if (product.getUser().getId().equals(user.getId())) {
                productRepository.delete(product);
                log.info("Product with id = {} was deleted", id);
            } else {
                log.error("User: {} haven't this product with id = {}", user.getEmail(), id);
            }
        } else {
            log.error("Product with id = {} is not found", id);
        }
    }

    //Метод, который возвращает товар с указанным идентификатором.
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}