package ogustaflor.com.github.logbook.repository;

import ogustaflor.com.github.logbook.entity.Event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> { }
