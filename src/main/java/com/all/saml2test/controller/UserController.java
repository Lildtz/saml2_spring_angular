package com.all.saml2test.controller;

import com.all.saml2test.model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {


    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        UserDTO u1 = UserDTO.builder().id(1).nome("Usuário 1").build();
        UserDTO u2 = UserDTO.builder().id(2).nome("Usuário 2").build();
        UserDTO u3 = UserDTO.builder().id(3).nome("Usuário 3").build();
        UserDTO u4 = UserDTO.builder().id(4).nome("Usuário 4").build();
        UserDTO u5 = UserDTO.builder().id(5).nome("Usuário 5").build();
        UserDTO u6 = UserDTO.builder().id(6).nome("Usuário 6").build();

        List<UserDTO> users = new ArrayList<>(Arrays.asList(u1, u2, u3, u4, u5, u6));


        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
