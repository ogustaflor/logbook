package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.entity.Event;

import ogustaflor.com.github.logbook.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
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
    ResponseEntity<Event> store(@Valid @RequestBody Event newEvent) {
        return new ResponseEntity<>(eventService.add(newEvent), HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    ResponseEntity<Event> show(@PathVariable(value = "id") long id) {
        return new ResponseEntity<>(eventService.findById(id), HttpStatus.OK);
    }

}
