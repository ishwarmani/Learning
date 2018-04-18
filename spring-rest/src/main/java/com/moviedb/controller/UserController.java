package com.moviedb.controller;

import com.moviedb.model.User;
import com.moviedb.repositories.UserRepository;
import com.moviedb.util.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;

import static com.moviedb.util.SecurityUtils.HEADER_STRING;
import static com.moviedb.util.SecurityUtils.TOKEN_PREFIX;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository userRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signup(@RequestBody User user, HttpServletResponse response){
        User appUser = userRepository.findByUsername(user.getUsername());
        HashMap respBody = new HashMap();
        if(appUser != null){
            respBody.put("message","User already exist with this username");
            respBody.put("code",HttpStatus.CONFLICT);
            return new ResponseEntity<HashMap>(respBody,HttpStatus.CONFLICT);
        }else{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + SecurityUtils.generateToken(user.getUsername()));
            respBody.put("message","Successfully Registered");
            respBody.put("code",HttpStatus.OK);
            return new ResponseEntity<HashMap>(respBody,HttpStatus.OK);
        }
    }
}
