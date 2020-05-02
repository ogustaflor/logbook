package ogustaflor.com.github.logbook.controller;

import ogustaflor.com.github.logbook.entity.User;
import ogustaflor.com.github.logbook.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    ResponseEntity<User> store(@Valid @RequestBody User user) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }

    @RequestMapping(value = "/users/{user_id}", method = RequestMethod.PUT)
    ResponseEntity<User> update(@PathVariable(value = "user_id") long userId, @Valid @RequestBody User updatedUser) {
        Optional<User> filteredUser = userRepository.findById(userId);
        if (filteredUser.isPresent()) {
            User selectedUser = filteredUser.get();
            boolean isUpdated = false;
            if (updatedUser.getName() != null && updatedUser.getName().isEmpty()) {
                isUpdated = true;
                selectedUser.setName(updatedUser.getName());
            }
            if (updatedUser.getEmail() != null && updatedUser.getEmail().isEmpty()) {
                isUpdated = true;
                selectedUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null && updatedUser.getPassword().isEmpty()) {
                isUpdated = true;
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                selectedUser.setPassword(bCryptPasswordEncoder.encode(updatedUser.getPassword()));
            }
            if (isUpdated) selectedUser = userRepository.save(selectedUser);
            return new ResponseEntity<>(selectedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
