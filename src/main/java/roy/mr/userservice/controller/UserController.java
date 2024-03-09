package roy.mr.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roy.mr.userservice.error.DataNotFoundException;
import roy.mr.userservice.model.Response;
import roy.mr.userservice.model.User;
import roy.mr.userservice.service.UserService;
import roy.mr.userservice.util.ResponseUtil;

@RestController
@RequestMapping("${api.path}/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Response> createUser(@RequestBody User user) {
        return ResponseUtil.success(
                userService.create(user)
        );
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Response> readUser(@PathVariable Long id) throws DataNotFoundException {
        return ResponseUtil.success(
                userService.read(id)
        );
    }

    @PutMapping
    public ResponseEntity<Response> updateUser(@RequestBody User user) {
        return ResponseUtil.success(
                userService.update(user)
        );
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Response> updateUser(@PathVariable Long id) throws DataNotFoundException {
        userService.delete(id);
        return ResponseUtil.success("user deletion successful with id " + id, null);
    }
}
