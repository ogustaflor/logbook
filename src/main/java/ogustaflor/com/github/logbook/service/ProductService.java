package ogustaflor.com.github.logbook.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Product;
import ogustaflor.com.github.logbook.exception.NotFoundException;
import ogustaflor.com.github.logbook.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Product add(Product newProduct) {
        return productRepository.saveAndFlush(newProduct);
    }

    public void remove(Product product) {
        productRepository.delete(product);
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(NotFoundException::new);
    }

}
