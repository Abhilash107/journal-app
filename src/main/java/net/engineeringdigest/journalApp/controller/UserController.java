package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping("/userName/{userName}")//searching by userName
    public ResponseEntity<User> updateUser(@RequestBody User newUser, @PathVariable String userName){
        User findUser = userService.findByUserName(userName);
        if(findUser != null){
            findUser.setUserName(newUser.getUserName());
            findUser.setPassword(newUser.getPassword());
            userService.saveEntry(findUser);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("id/{id}")
    public Optional<User> findUserById(ObjectId id){
        return userService.findById(id);
    }


    @DeleteMapping("id/{id}")
    public void deleteById(ObjectId id){
        userService.removeById(id);
    }


}
