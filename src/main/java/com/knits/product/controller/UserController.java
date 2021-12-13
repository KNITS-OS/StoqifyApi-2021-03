package com.knits.product.controller;

import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.knits.product.dto.UserDto;
import com.knits.product.service.UserService;
import org.springframework.http.ResponseEntity;
import com.knits.product.exceptions.UserException;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {

        log.debug("REST request to get User : {}", userId);
        UserDto userFound = userService.getUserById(userId);
        return ResponseEntity.ok().body(userFound);
    }


    @GetMapping(value = "/users/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.debug("REST request to get all Users");
        return ResponseEntity.ok().body(userService.fetchAllUsers());
    }


    @PostMapping(value = "/users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDTO) {
        log.debug("REST request to createUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.createNewUser(userDTO));
    }

    @PutMapping(value = "/users")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO) {
        log.debug("REST request to updateUser User ");
        if (userDTO == null) {
            throw new UserException("User data are missing");
        }
        return ResponseEntity.ok().body(userService.update(userDTO));
    }

    @PatchMapping(value = "/users")
    public ResponseEntity<UserDto> partialUpdateUser(@RequestBody UserDto userDto) {
        log.debug("REST request to updateUser User ");
        return ResponseEntity.ok().body(userService.partialUpdateUserData(userDto));
    }

    @DeleteMapping("/users")
    public ResponseEntity<Void> deleteUser(@RequestBody UserDto userDto) {
        log.debug("REST request to delete User : {}", userDto.getId());
        userService.deleteUserDataByUserId(userDto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchuser/{searchkeyword}")
    public ResponseEntity<List<UserDto>> searchUser(@PathVariable String searchkeyword) {
        log.debug("Searched Keyword : {}", searchkeyword);
        return ResponseEntity.ok().body(userService.searchUsersByKeyword(searchkeyword));
    }

    @PutMapping("/addusergroup")
    public ResponseEntity<String> assignUserGroup(@Valid @RequestBody UserDto userDto) {
        log.debug("REST request to add user in a group: {}", userDto.getId());
        return ResponseEntity.ok().body(userService.addUserGroup(userDto));
    }

    @PutMapping("/adduserrole")
    public ResponseEntity<Void> addUserRole(@Valid @RequestBody UserDto userDto) {
        log.debug("Requested to add user role");
        userService.addUserRole(userDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/removeusergroup")
    public ResponseEntity<String> removeUserGroup(@RequestBody UserDto userDto) {
        userService.removeUserGroup(userDto);
        return ResponseEntity.noContent().build();
    }
}
