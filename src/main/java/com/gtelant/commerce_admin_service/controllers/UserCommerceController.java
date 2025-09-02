package com.gtelant.commerce_admin_service.controllers;
import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.repositories.UserCommerceRepo;
import com.gtelant.commerce_admin_service.requests.CreateUserRequest;
import com.gtelant.commerce_admin_service.responses.GetUserResponse;
import com.gtelant.commerce_admin_service.service.UserCommerceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user_commerce")
@CrossOrigin("*")
@Tag(name = "Posters Galore 使用者控制盤")
public class UserCommerceController {
    private final UserCommerceService userCommerceService;
    @Autowired
    public UserCommerceController(UserCommerceService userCommerceService) {
        this.userCommerceService = userCommerceService;
    }

    @GetMapping
    public ResponseEntity<List<GetUserResponse>> findAllUsers(){
        List<User> users = userCommerceService.findAllUsers();
        return ResponseEntity.ok(users.stream().map(GetUserResponse::new).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserResponse> findUserById(@PathVariable long id){
        Optional<User> user = userCommerceService.findUserById(id);
        if(user.isPresent()){
            GetUserResponse response = new GetUserResponse(user.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        GetUserResponse response = userCommerceService.createUser(request);
        return ResponseEntity.ok(response);
    }
//
//    @DeleteMapping
//
//    @PutMapping
}


