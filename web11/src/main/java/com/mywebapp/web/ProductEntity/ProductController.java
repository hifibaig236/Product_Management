package com.mywebapp.web.ProductEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService service;

    @GetMapping("/Product")
    public String showUserList(Model model) {
        List<Product> listUsers = service.listAll();
        model.addAttribute("listUsers", listUsers);
        return "Product";
    }

    @GetMapping("/Product/new")
    public String showNewForm(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product_form";
    }
    @PostMapping("/Product/save")
    public String SaveProduct(Product product, RedirectAttributes ra){
        service.save(product);

        ra.addFlashAttribute("message", "Product has been saved Successfully");
        return "redirect:/Product";
    }
    @GetMapping("/Product/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Product user = service.get(id);
            model.addAttribute("user", user);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");

            return "product_form";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/Product";
        }
    }

    @GetMapping("/Product/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The user ID " + id + " has been deleted.");
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/Product";
    }





}
