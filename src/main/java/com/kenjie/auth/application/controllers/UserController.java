package com.kenjie.auth.application.controllers;
import com.kenjie.auth.application.DTOs.CreateUserInputDTO;
import com.kenjie.auth.application.DTOs.SuccessResponseDTO;
import com.kenjie.auth.application.DTOs.UpdateUserInputDTO;
import com.kenjie.auth.application.errorhandler.exceptions.UnauthenticatedException;
import com.kenjie.auth.application.errorhandler.exceptions.UnauthorizedException;;
import com.kenjie.auth.application.utils.ValidationHelper;
import com.kenjie.auth.core.entities.PaginatedList;
import com.kenjie.auth.core.enums.UserErrorCode;
import com.kenjie.auth.core.exceptions.BadRequestException;
import com.kenjie.auth.core.services.UserCommandService;
import com.kenjie.auth.core.services.UserQueryService;
import com.kenjie.auth.core.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(path = "users")
public class UserController {

    @Autowired
    private UserQueryService userQueryService;

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<SuccessResponseDTO<PaginatedList<User>>> getUsers(
        @RequestParam(value = "limit", defaultValue = "10") String limit,
        @RequestParam(value = "offset", defaultValue = "0") String offset
    ) throws Exception {
        try {
            if (!ValidationHelper.isInteger(limit)) {
                throw new BadRequestException(UserErrorCode.USER_LIST_100400.name(), "Limit must be integer");
            }

            if (!ValidationHelper.isInteger(offset)) {
                throw new BadRequestException(UserErrorCode.USER_LIST_100400.name(), "Offset must be integer");
            }

            PaginatedList<User> users = this.userQueryService.getUsers(Integer.parseInt(limit), Integer.parseInt(offset));
            SuccessResponseDTO response = new SuccessResponseDTO(users);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            throw e;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponseDTO<User>> getUserById(@PathVariable("id") String id) throws Exception {
        try {
            if (id.equals("1")) {
                throw new UnauthenticatedException();
            }

            if (id.equals("2")) {
                throw new UnauthorizedException();
            }

            User user = this.userQueryService.getUserById(id);
            SuccessResponseDTO response = new SuccessResponseDTO(user);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping()
    public ResponseEntity<SuccessResponseDTO<String>> createUser(@RequestBody CreateUserInputDTO userDetails) throws Exception {
        try {
            UUID userId = UUID.randomUUID();
            User user = new User();
            user.setUserId(userId.toString());
            modelMapper.map(userDetails, user);

            this.userCommandService.createUser(user);

            SuccessResponseDTO response = new SuccessResponseDTO(userId);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            throw e;
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<SuccessResponseDTO<String>> updateUserDetails(
        @PathVariable("id") String id,
        @RequestBody UpdateUserInputDTO userDetails
    ) throws Exception {
        try {
            User user = new User();
            modelMapper.map(userDetails, user);

            this.userCommandService.updateUser(id, user);

            SuccessResponseDTO response = new SuccessResponseDTO(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            throw e;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserByUserId(@PathVariable("id") String id) throws Exception {
        try {
            this.userCommandService.deleteUserByUserId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }
}
