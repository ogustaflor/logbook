package ogustaflor.com.github.logbook.controller;

import ogustaflor.com.github.logbook.entity.Service;
import ogustaflor.com.github.logbook.repository.ServiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class ServiceController {

    @Autowired
    private ServiceRepository serviceRepository;

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    ResponseEntity<List<Service>> index() {
        return new ResponseEntity<>(serviceRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    ResponseEntity<Service> store(@Valid @RequestBody Service service) {
        return new ResponseEntity<>(serviceRepository.save(service), HttpStatus.OK);
    }

    @RequestMapping(value = "/services/{service_id}", method = RequestMethod.DELETE)
    ResponseEntity<Object> destroy(@PathVariable(value = "service_id") long serviceId) {
        Optional<Service> filteredService = serviceRepository.findById(serviceId);
        if (filteredService.isPresent()) {
            Service selectedService = filteredService.get();
            serviceRepository.delete(selectedService);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
