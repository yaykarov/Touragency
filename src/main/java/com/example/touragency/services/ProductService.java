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

/**

 Сервис для работы с продуктами.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    /**

     Метод для получения списка продуктов.
     @param title строка для поиска продуктов по названию или описанию. Может быть null.
     @return список продуктов, найденных по заданному критерию или список всех продуктов, если title == null.
     */

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }

    /**

     Метод для сохранения нового продукта.
     @param principal текущий пользователь, который сохраняет продукт.
     @param product продукт, который нужно сохранить.
     @throws IOException если произошла ошибка ввода-вывода при сохранении продукта.
     */
    public void saveProduct(Principal principal, Product product) throws IOException {
        product.setUser(getUserByPrincipal(principal));
        log.info("Saving new Product. Title: {}; Author email: {}", product.getTitle(), product.getUser().getEmail());
        Product productFromDb = productRepository.save(product);
        productRepository.save(product);
    }
    /**

     Метод для получения пользователя по его Principal.
     @param principal Principal пользователя, чьи данные нужно получить.
     @return объект User, соответствующий переданному Principal.
     */

    public User getUserByPrincipal(Principal principal) {
        if (principal == null) return new User();
        return userRepository.findByEmail(principal.getName());
    }

    /**

     Метод для удаления продукта.
     @param user пользователь, который пытается удалить продукт.
     @param id идентификатор продукта, который нужно удалить.
     */

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
    /**

     Метод для получения продукта по его идентификатору.
     @param id идентификатор продукта, который нужно получить.
     @return объект Product, соответствующий переданному идентификатору, или null, если продукт не найден.
     */
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }
}