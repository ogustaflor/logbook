package ogustaflor.com.github.logbook.repository;

import ogustaflor.com.github.logbook.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> { }
