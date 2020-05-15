package ogustaflor.com.github.logbook.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.exception.BusinessException;
import ogustaflor.com.github.logbook.exception.NotFoundException;
import ogustaflor.com.github.logbook.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product add(Product newProduct) {
        if (newProduct.getId() != null) {
            throw new BusinessException();
        }
        return productRepository.save(newProduct);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void remove(Product product) {
        if (!product.getEvents().isEmpty()) {
            throw new BusinessException();
        }
        productRepository.delete(product);
    }

    public Product findById(Long id) {
        Optional<Product> filteredProduct = productRepository.findById(id);
        if (!filteredProduct.isPresent()) {
            throw new NotFoundException();
        }
        return filteredProduct.get();
    }

}
