package com.authentication.security.controllers;


import com.authentication.security.dto.UserDTO;
import com.authentication.security.models.user.UserUpdateRequest;
import com.authentication.security.responseMessage.ResponseMsg;
import com.authentication.security.models.user.User;
import com.authentication.security.repository.UserRepository;
import com.authentication.security.services.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserService userService;
    private UserRepository userRepository;
    @GetMapping
    public List<UserDTO> getUsers(){
        List<User> users = userRepository.findAll();
        List<UserDTO> userDTOs = new ArrayList<UserDTO>();
        for(User user : users){
            UserDTO userDTO = new UserDTO();
            userDTO.setFullName(user.getFullName());
            userDTO.setEmail(user.getEmail());
            userDTO.setGender(user.getGender());
            userDTO.setPhone(user.getPhone());
            userDTO.setCin(user.getCin());
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        token = token.substring(7);
        try {
            userService.deleteUser(token);
            ResponseMsg okResponse = new ResponseMsg(HttpStatus.OK.value(), "User deleted successfully");
            return ResponseEntity.ok(okResponse);
        } catch (IllegalArgumentException e) {
            ResponseMsg errorResponse = new ResponseMsg(HttpStatus.BAD_REQUEST.value(), "User Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        } catch (IllegalStateException e) {
            ResponseMsg errorResponse = new ResponseMsg(HttpStatus.FORBIDDEN.value(), token);
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestHeader("Authorization") String token,
                                        @RequestBody UserUpdateRequest userUpdateRequest)
    {
        token = token.substring(7);
        try {
            userService.updateUser(token, userUpdateRequest);
            ResponseMsg okResponse = new ResponseMsg(HttpStatus.OK.value(), "User updated Successfully" );
            return ResponseEntity.ok(okResponse);
        }catch (Exception e){
            ResponseMsg errorResponse = new ResponseMsg(HttpStatus.BAD_REQUEST.value(), "User Not Found");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }catch (IllegalAccessError e) {
            ResponseMsg errorResponse = new ResponseMsg(HttpStatus.FORBIDDEN.value(), "You are not authorized to update this user.");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
        }
    }
}
