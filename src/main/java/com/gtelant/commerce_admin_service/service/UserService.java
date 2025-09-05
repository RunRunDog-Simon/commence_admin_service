package com.gtelant.commerce_admin_service.service;

import com.gtelant.commerce_admin_service.models.Segment;
import com.gtelant.commerce_admin_service.models.User;
import com.gtelant.commerce_admin_service.models.UserSegment;
import com.gtelant.commerce_admin_service.repositories.SegmentRepo;
import com.gtelant.commerce_admin_service.repositories.UserRepo;
import com.gtelant.commerce_admin_service.repositories.UserSegmentRepo;
import com.gtelant.commerce_admin_service.requests.CreateUserRequest;
import com.gtelant.commerce_admin_service.requests.UpdateUserRequest;
import com.gtelant.commerce_admin_service.responses.GetUserResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepo userRepo;
    private final SegmentRepo segmentRepo;
    private final UserSegmentRepo userSegmentRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, UserSegmentRepo userSegmentRepo, SegmentRepo segmentRepo){
        this.userRepo = userRepo;
        this.userSegmentRepo = userSegmentRepo;
        this.segmentRepo = segmentRepo;
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }
    public Page<User> findAllUsersPage(PageRequest pageRequest) {
        return userRepo.findAll(pageRequest);
    }
    public Optional<User> findUserById(long id){
        Optional<User> user = userRepo.findById(id); //long int沒有重載?
        return user;
    }

    public GetUserResponse createUser(CreateUserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setBirthday(request.getBirthday());
        user.setAddress(request.getAddress());
        user.setCity(request.getCity());
        user.setState(request.getState());
        user.setZipcode(request.getZipcode());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setHasNewsletter(request.isHasNewsletter());
        user.setRole(request.getRole());
        User savedUser = userRepo.save(user);
        GetUserResponse response = new GetUserResponse(savedUser);
        return response;
    }

    public ResponseEntity<Void> deleteUserById(long id) {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public void updateUserById(Long id, UpdateUserRequest request) {
        Optional<User> user = userRepo.findById(id);
        user.get().setFirstName(request.getFirstName());
        user.get().setLastName(request.getLastName());
        user.get().setAddress(request.getAddress());
        user.get().setCity(request.getCity());
        user.get().setState(request.getState());
        user.get().setZipcode(request.getZipcode());
        user.get().setHasNewsletter(request.isHasNewsletter());
        User updatedUser = userRepo.save(user.get());
        UpdateUserRequest response = new UpdateUserRequest(updatedUser);
    }

    //Kermit
    public void assignSegmentToUser(long id, long segmentId){
        Optional<Segment> segment = segmentRepo.findById(segmentId);
        Optional <User> user = userRepo.findById(id);

        if(user.isPresent() && segment.isPresent()){
            UserSegment userSegment = new UserSegment();
            userSegment.setSegment(segment.get());
            userSegment.setUser(user.get());
            userSegmentRepo.save(userSegment);
        }
    }
}
