package com.gtelant.commerce_admin_service.controllers;
import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.requests.CreateUserRequest;
import com.gtelant.commerce_admin_service.requests.UpdateUserRequest;
import com.gtelant.commerce_admin_service.responses.GetUserResponse;
import com.gtelant.commerce_admin_service.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
@Tag(name = "Posters Galore 使用者控制盤")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/page")
    public Page<GetUserResponse> getAllUserPage(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "20") int size
    ){
        PageRequest pageRequest = PageRequest.of(page,size);
        return userService.findAllUsers(pageRequest).map(GetUserResponse::new);
    }

//    @GetMapping
//    public ResponseEntity<List<GetUserResponse>> findAllUsers(){
//        List<User> users = userCommerceService.findAllUsers();
//        return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> findUserById(@PathVariable long id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            GetUserResponse response = new GetUserResponse(user.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        GetUserResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateUserRequest> updateUserById(@PathVariable long id, @RequestBody UpdateUserRequest request){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            userService.updateUserById(id, request);
            return ResponseEntity.ok(request);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable long id){
        Optional<User> user = userService.findUserById(id);
        if(user.isPresent()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    //Kermit
    @PostMapping("/{id}/segment/{segmentId}")
        public ResponseEntity<Void> assignSegmentByIdToUser(@PathVariable long id, @PathVariable long segmentId) {
        userService.assignSegmentToUser(id, segmentId);
        return ResponseEntity.noContent().build();
    }
}


