package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ProductController {

     private final ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    ResponseEntity<Product> store(@Valid @RequestBody Product newProduct) {
        return new ResponseEntity<>(productService.add(newProduct), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    ResponseEntity<Product> update(@PathVariable(value = "id") long id, @Valid @RequestBody Product updatedProduct) {
        Product product = productService.findById(id);
        product.merge(updatedProduct);
        return new ResponseEntity<>(productService.update(product), HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Object> destroy(@PathVariable(value = "id") long id) {
        productService.remove(productService.findById(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
