package com.stockflow.controller;

import com.stockflow.model.Product;
import com.stockflow.repository.LocationRepository;
import com.stockflow.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationRepository locationRepository;

    @GetMapping("/")
    public String listProducts(@RequestParam(value = "search", required = false) String search, Model model) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("products", productRepository.findByNameUnsafe(search));
        } else {
            model.addAttribute("products", productRepository.findAll());
        }
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("locations", locationRepository.findAll());
        return "form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product) {
        productRepository.save(product);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("locations", locationRepository.findAll());
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productRepository.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/debug")
    public String debug(@RequestParam(value = "msg", required = false) String msg, Model model) {
        model.addAttribute("msg", msg);
        return "debug";
    }

    @GetMapping("/admin/wipe")
    public String wipeAll() {
        productRepository.deleteAll();
        return "redirect:/";
    }
}
