package ogustaflor.com.github.logbook.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ogustaflor.com.github.logbook.entity.Event;
import ogustaflor.com.github.logbook.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventService {

    private final EventRepository eventRepository;

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public Page<Event> findAll(PageRequest pageRequest) {
        return eventRepository.findAll(pageRequest);
    }

    public Event add(Event event) {
        return eventRepository.save(event);
    }

}
