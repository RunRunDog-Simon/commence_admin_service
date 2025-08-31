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
@RestController
@RequestMapping("/UserCommerce")
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
//
//    @GetMapping
//
    @PostMapping
    public ResponseEntity<GetUserResponse> createUser(@RequestBody CreateUserRequest request){
        return ResponseEntity.ok(response);
    }
//
//    @DeleteMapping
//
//    @PutMapping
}


