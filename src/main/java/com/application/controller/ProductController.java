package com.application.controller;

import com.application.domain.Product;
import com.application.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private final static Logger log = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public ResponseEntity<List<Product>> getProducts(@RequestParam("orderBy") Optional<String> orderBy, @RequestParam("name") Optional<String> name) {
        log.info("get all products");

        if(orderBy.isPresent()){
            if (orderBy.equals("DESC")) {
                return ResponseEntity.ok().body(productService.findOrderByName(Sort.by(Sort.Direction.DESC, "name")));
            } else  {
                return ResponseEntity.ok().body(productService.findOrderByName(Sort.by(Sort.Direction.ASC, "name")));
            }
        } else if(name.isPresent()){
            return ResponseEntity.ok().body(productService.findByName(name.orElse("")));
        } else {
          return ResponseEntity.ok().body(productService.findAll());
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Optional<Product>> getProductById(@PathVariable("id") String id) {
        return Optional
                .ofNullable(productService.findById(id))
                .map(product -> ResponseEntity.ok().body(product))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(productService.create(product));
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<Product> createClient(@PathVariable("id") String id, @RequestParam("name") String name) {
        return productService.findById(id)
                .map(product -> ResponseEntity.ok().body(productService.updateName(product,name)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String id) {
        return  productService.findById(id)
                .map(product ->{ productService.delete(product);
                    return ResponseEntity.ok().build(); })
                .orElse(ResponseEntity.notFound().build());
    }
}
