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

@Controller // Указываем, что это контроллер
@RequiredArgsConstructor // Аннотация для создания конструктора с final полями
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    // Обработчик GET запроса на главную страницу
    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }
    // Обработчик GET запроса на страницу информации о продукте
    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-info";
    }
    // Обработчик POST запроса на создание нового продукта
    @PostMapping("/product/create")
    public String createProduct(Product product, Principal principal) throws IOException {
        productService.saveProduct(principal, product);
        return "redirect:/";
    }
    // Обработчик GET запроса на удаление продукта
    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Principal principal) {
        productService.deleteProduct(productService.getUserByPrincipal(principal), id);
        return "redirect:/";
}

    // Обработчик GET запроса на отображение формы редактирования продукта
    @GetMapping("/product/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,Principal principal) {
        Product product = productService.getProductById(id);
        if (!product.getUser().getEmail().equals(principal.getName())) {
            return "redirect:/";
        }
        model.addAttribute("product", product);
        return "edit_product";
    }
    // Обработчик POST запроса на редактирование продукта
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