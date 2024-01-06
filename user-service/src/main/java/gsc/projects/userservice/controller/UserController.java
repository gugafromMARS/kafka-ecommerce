package gsc.projects.userservice.controller;


import gsc.projects.userservice.dto.UserCreatedDto;
import gsc.projects.userservice.dto.UserUpdateDto;
import gsc.projects.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {


    private UserService userService;
    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreatedDto userCreatedDto){
        return new ResponseEntity<>(userService.createUser(userCreatedDto), HttpStatus.CREATED);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> get(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(userService.getUserByEmail(userEmail));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable ("userId") Long userId){
        userService.deleteById(userId);
        return ResponseEntity.ok("User deleted successfully");
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable ("userId") Long userId, @RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.updateById(userId,  userUpdateDto));
    }
}
