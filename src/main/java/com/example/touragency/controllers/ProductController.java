package com.example.touragency.controllers;
import com.example.touragency.models.Product;
import com.example.touragency.models.User;
import com.example.touragency.services.ProductService;
import com.example.touragency.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.security.Principal;

/**
 * Контроллер для управления турами в приложении турагентства.
 */
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    /**
    * Обработчик GET запроса на главную страницу
     * @param title заголовок
     * @param principal авторизированный пользователь
     * @param model модель для view
     * @return имя
     */
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }
    /**
     * Выводит подробную информацию о продукте.
     *
     * @param id ID тура
     * @param model модель для view
     * @return имя view для вывода
     */

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-info";
    }
    /**
     * Метод для создания тура
     *
     * @param product данные тура
     * @param principal авторизованный пользователь
     * @return URL для редиректа
     * @throws IOException при возникновении ошибки при создании тура
     */

    @PostMapping("/product/create")
    public String createProduct(Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product);
        return "redirect:/";
    }

    /**
     * Удаление тура
     *
     * @param id ID тура
     * @param principal авторизованный пользователь
     * @return URL для редиректа
     */
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
        return "redirect:/";
}
    /**
     * Выводит форму для редактирования товара
     *
     * @param id ID тура
     * @param model модель для view
     * @param principal авторизованный пользователь
     * @return название представления edit_product, если пользователь авторизован для редактирования продукта, в противном случае происходит перенаправление на страницу продуктов
     */

    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,Principal principal) {
        Product product = productService.getProductById(id);
        if (!product.getUser().getEmail().equals(principal.getName())) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "edit_product";
    }


    /**
     * Обновляет сведения о товаре в базе данных и перенаправляет пользователя на страницу туров.
     *
     * @param id ID тура
     * @param updatedProduct детали редактируемого тура
     * @param principal авторизованный пользователь
     * @return редирект на главную
     * @throws IOException при возникновении ошибки при создании тура
     */
    @PostMapping("/product/edit/{id}")
    public String editProduct(@PathVariable Long id, Product updatedProduct, Principal principal) throws IOException {
        Product product = productService.getProductById(id);
        product.setTitle(updatedProduct.getTitle());
        product.setDescription(updatedProduct.getDescription());
        product.setPrice(updatedProduct.getPrice());
        product.setCity(updatedProduct.getCity());
        productService.saveProduct(principal, product);
        return "redirect:/";


    }
}