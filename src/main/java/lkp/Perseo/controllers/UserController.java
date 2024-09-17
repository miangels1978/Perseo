package lkp.Perseo.controllers;

import lkp.Perseo.models.User;
import lkp.Perseo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAll ();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.findById (id)
                .map (ResponseEntity::ok)
                .orElse (ResponseEntity.notFound ().build ());
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.save (user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        return userService.findById (id)
                .map (user -> {
                    user.setUsername (userDetails.getUsername ());
                    user.setEmail (userDetails.getEmail ());
                    user.setPassword (userDetails.getPassword ());
                    user.setRole (userDetails.getRole ());
                    return ResponseEntity.ok (userService.save (user));
                })
                .orElse (ResponseEntity.notFound ().build ());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        return userService.findById (id)
                .map (user -> {
                    userService.deleteById (id);
                    return ResponseEntity.ok ().build ();
                })
                .orElse (ResponseEntity.notFound ().build ());
    }
}