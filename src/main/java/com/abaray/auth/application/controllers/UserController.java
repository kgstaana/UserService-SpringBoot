package com.abaray.auth.application.controllers;

import com.abaray.auth.application.DTOs.AccountStatusDTO;
import com.abaray.auth.application.DTOs.CreateUserInputDTO;
import com.abaray.auth.application.DTOs.SuccessResponseDTO;
import com.abaray.auth.application.DTOs.UpdateUserInputDTO;
import com.abaray.auth.application.errorhandler.exceptions.UnauthenticatedException;
import com.abaray.auth.application.errorhandler.exceptions.UnauthorizedException;
import com.abaray.auth.application.utils.ValidationHelper;
import com.abaray.auth.core.entities.PaginatedList;
import com.abaray.auth.core.entities.User;
import com.abaray.auth.core.enums.UserErrorCode;
import com.abaray.auth.core.exceptions.BadRequestException;
import com.abaray.auth.core.repositories.services.UserCommandService;
import com.abaray.auth.core.repositories.services.UserQueryService;
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

            PaginatedList<User> users = userQueryService.getUsers(Integer.parseInt(limit), Integer.parseInt(offset));
            SuccessResponseDTO response = new SuccessResponseDTO(users);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            throw e;
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<SuccessResponseDTO<User>> getUserById(@PathVariable("id") String id) throws Exception {
        try {
            // Todo implement authentication
            if (id.equals("1")) {
                throw new UnauthenticatedException();
            }
            // Todo implement authorization
            if (id.equals("2")) {
                throw new UnauthorizedException();
            }

            User user = userQueryService.getUserById(id);
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

            userCommandService.createUser(user);

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

            userCommandService.updateUser(id, user);

            SuccessResponseDTO response = new SuccessResponseDTO(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch(Exception e) {
            throw e;
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUserByUserId(@PathVariable("id") String id) throws Exception {
        try {
            userCommandService.deleteUserByUserId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateAccountStatus(
        @PathVariable("id") String id,
        @RequestBody() AccountStatusDTO dto
    ) throws Exception {
        try {
            userCommandService.changeAccountStatus(id, dto.getAccountStatus());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            throw e;
        }
    }
}
