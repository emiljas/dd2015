package com.hp.devday.pietryna.users;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private static final Logger LOG = Logger.getLogger(UsersController.class);

    private UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/me")
    @ResponseStatus(HttpStatus.OK)
    public User getMe(final Principal principal) {
        return usersService.get(principal.getName());
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> get() {
        LOG.debug("Get all users");
        return usersService.getAll();
    }

}
