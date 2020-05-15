package ogustaflor.com.github.logbook.service;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Event;
import ogustaflor.com.github.logbook.exception.BusinessException;
import ogustaflor.com.github.logbook.exception.NotFoundException;
import ogustaflor.com.github.logbook.repository.EventRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event add(Event newEvent) {
        if (newEvent.getId() != null) {
            throw new BusinessException();
        }
        return eventRepository.save(newEvent);
    }

    public Event findById(Long id) {
        Optional<Event> filteredEvent = eventRepository.findById(id);
        if (!filteredEvent.isPresent()) {
            throw new NotFoundException();
        }
        return filteredEvent.get();
    }

    public Page<Event> findAll(PageRequest pageRequest) {
        return eventRepository.findAll(pageRequest);
    }

}
