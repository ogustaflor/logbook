package ogustaflor.com.github.logbook.repository;

import ogustaflor.com.github.logbook.entity.Service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> { }
