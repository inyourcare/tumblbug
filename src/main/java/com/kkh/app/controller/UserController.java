package com.kkh.app.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "user api", consumes = "application/json")
@RequestMapping("/v0/user")
public class UserController {
}
