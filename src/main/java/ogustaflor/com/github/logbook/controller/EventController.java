package ogustaflor.com.github.logbook.controller;

import ogustaflor.com.github.logbook.entity.Event;
import ogustaflor.com.github.logbook.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ResponseEntity<List<Event>> index() {
        return new ResponseEntity<>(eventRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    ResponseEntity<Event> store(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventRepository.save(event), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{event_id}", method = RequestMethod.GET)
    ResponseEntity<Event> show(@PathVariable(value = "event_id") long logId) {
        Optional<Event> filteredEvent = eventRepository.findById(logId);
        if (filteredEvent.isPresent()) {
            Event selectedEvent = filteredEvent.get();
            return new ResponseEntity<>(selectedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
