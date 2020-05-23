package ogustaflor.com.github.logbook.controller;

import lombok.RequiredArgsConstructor;
import ogustaflor.com.github.logbook.annotation.Sortable;
import ogustaflor.com.github.logbook.entity.Event;

import ogustaflor.com.github.logbook.service.EventService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    Page<Event> index(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "24", required = false) int size,
        @RequestParam(name = "sortBy", defaultValue = "", required = false) String sortBy,
        @RequestParam(name = "ascendingSort", defaultValue = "true", required = false) boolean ascendingSort
    ) {
        String[] properties = {};
        if (!sortBy.isEmpty()) {
            Set<String> sortableFields = Arrays.stream(Event.class.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Sortable.class))
                .map(Field::getName)
                .collect(Collectors.toSet());
            final String SEPARATOR = ";";
            properties = Arrays.stream(sortBy.split(SEPARATOR))
                .distinct()
                .filter(sortableFields::contains)
                .toArray(String[]::new);
        }
        PageRequest pageRequest = properties.length > 0
            ? PageRequest.of(page, size, Sort.by(ascendingSort ? Sort.Direction.ASC : Sort.Direction.DESC, properties))
            : PageRequest.of(page, size);

        return eventService.findAll(pageRequest);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    Event store(@Valid @RequestBody Event newEvent) {
        return eventService.add(newEvent);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.GET)
    Event show(@PathVariable(value = "id") long id) {
        return eventService.findById(id);
    }

}
