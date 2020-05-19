package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.annotation.Sortable;
import ogustaflor.com.github.logbook.entity.Event;

import ogustaflor.com.github.logbook.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    ResponseEntity<Page<Event>> index(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "8", required = false) int size,
        @RequestParam(name = "sortBy", defaultValue = "", required = false) String sortBy
    ) {
        String[] properties = {};
        if (!sortBy.isEmpty()) {
            Set<String> sortableFields = Arrays.stream(Event.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Sortable.class))
                .map(Field::getName)
                .collect(Collectors.toSet());
            properties = (String[]) Arrays.stream(sortBy.split(";"))
                .filter(sortableFields::contains)
                .distinct()
                .toArray();
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, properties));
        return new ResponseEntity<>(eventService.findAll(pageRequest), HttpStatus.OK);
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
