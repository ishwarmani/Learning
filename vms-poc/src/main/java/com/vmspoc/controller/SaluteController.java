package com.vmspoc.controller;

import com.vmspoc.model.Greeting;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class SaluteController {

    @RequestMapping(value="/greet")
    public Greeting demo(){
        return new Greeting(12,"hello from me");
    }

}
