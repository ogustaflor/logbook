package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.entity.dto.ProductDTO;
import ogustaflor.com.github.logbook.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

     private final ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    ProductDTO store(@Valid @RequestBody ProductDTO productDTO) {
        return productService.add(productDTO.toEntity()).toDTO();
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    void destroy(@PathVariable(value = "id") long id) {
        productService.remove(productService.findById(id));
    }

}
