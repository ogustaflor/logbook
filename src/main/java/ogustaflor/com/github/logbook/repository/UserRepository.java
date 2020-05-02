package ogustaflor.com.github.logbook.repository;

import ogustaflor.com.github.logbook.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { }
