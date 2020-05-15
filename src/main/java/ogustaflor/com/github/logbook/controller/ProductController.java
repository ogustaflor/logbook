package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.exception.BusinessException;
import ogustaflor.com.github.logbook.exception.NotFoundException;
import ogustaflor.com.github.logbook.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductController {

     private final ProductService productService;

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    ResponseEntity<Product> store(@Valid @RequestBody Product newProduct) {
        log.info("> [ProductController.store]");
        try {
            newProduct = productService.add(newProduct);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("< [ProductController.store]");
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
    ResponseEntity<Product> update(@PathVariable(value = "id") long id, @Valid @RequestBody Product updatedProduct) {
        log.info("> [ProductController.update]");
        try {
            Product product = productService.findById(id);
            product.merge(updatedProduct);
            updatedProduct = productService.update(product);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("< [ProductController.update]");
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    ResponseEntity<Object> destroy(@PathVariable(value = "id") long id) {
        log.info("> [ProductController.destroy]");
        try {
            Product product = productService.findById(id);
            productService.remove(product);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (BusinessException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("< [ProductController.destroy]");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
