package ogustaflor.com.github.logbook.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Event;
import ogustaflor.com.github.logbook.exception.NotFoundException;
import ogustaflor.com.github.logbook.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event add(Event newEvent) {
        return eventRepository.save(newEvent);
    }

    public Event findById(Long id) {
        return eventRepository.findById(id).orElseThrow(NotFoundException::new);
    }

    public Page<Event> findAll(PageRequest pageRequest) {
        return eventRepository.findAll(pageRequest);
    }

}
