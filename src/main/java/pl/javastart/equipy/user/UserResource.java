package pl.javastart.equipy.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.javastart.equipy.assignments.AssignmentDto;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserResource {

    private UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    public UserResource() {
    }

    @GetMapping
    public List<UserDto> findAll(@RequestParam(required = false) String lastName) {
        if (lastName != null) {
            return userService.findByLastName(lastName);
        } else {
            return userService.findAll();
        }
    }

//    @PostMapping
//    public ResponseEntity<UserDto> save(@RequestBody UserDto userDto){
//        if (userDto.getId() != null)
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
//        UserDto saveUser = userService.addUser(userDto);
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(saveUser.getId())
//                .toUri();
//        return ResponseEntity.created(location).body(saveUser);
//    }

    @PostMapping
    public UserDto save(@RequestBody UserDto userDto){
        if (userDto.getId() != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        return userService.addUser(userDto);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id){
        return userService.findById(id);
    }

    @PutMapping("/{id}")
    public UserDto editUser(@RequestBody UserDto userDto, @PathVariable Long id){
        if (userDto.getId() == null || !userDto.getId().equals(id))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Aktualizowany obiekt musi mieć id zgodne z id w ścieżce zasobu");
        return userService.editUser(userDto);
    }

    @GetMapping("/{userId}/assignments")
    public List<AssignmentDto> findAllAssignment(@PathVariable Long userId){
        return userService.findAllAssignment(userId);
    }
}



