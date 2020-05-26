package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.dto.ProductDTO;
import ogustaflor.com.github.logbook.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

     private final ProductService productService;

    @PostMapping
    ProductDTO store(@Valid @RequestBody ProductDTO productDTO) {
        return productService.add(productDTO.toEntity()).toDTO();
    }

    @DeleteMapping(value = "/{id}")
    void destroy(@PathVariable(value = "id") long id) {
        productService.remove(productService.findById(id));
    }

}
