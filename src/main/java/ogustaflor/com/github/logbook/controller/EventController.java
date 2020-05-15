package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ogustaflor.com.github.logbook.entity.Event;

import ogustaflor.com.github.logbook.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ResponseEntity<Page<Event>> index(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "8", required = false) int size
    ) {
        return new ResponseEntity<>(eventService.findAll(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    ResponseEntity<Event> store(@Valid @RequestBody Event event) {
        return new ResponseEntity<>(eventService.add(event), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    ResponseEntity<Event> show(@PathVariable(value = "id") long id) {
        Optional<Event> filteredEvent = eventService.findById(id);
        if (filteredEvent.isPresent()) {
            Event selectedEvent = filteredEvent.get();
            return new ResponseEntity<>(selectedEvent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
