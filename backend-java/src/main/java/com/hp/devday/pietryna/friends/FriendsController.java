package com.hp.devday.pietryna.friends;

import com.hp.devday.pietryna.users.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/friends")
public class FriendsController {

    private static final Logger LOG = Logger.getLogger(FriendsController.class);

    private FriendsService friendsService;

    @Autowired
    public FriendsController(final FriendsService friendsService) {
        this.friendsService = friendsService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Iterable<User> get(final Principal principal) {
        LOG.debug("Get all friends");
        return friendsService.get(principal.getName());
    }

    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public User post(final @RequestBody User friend, final Principal principal) {
        LOG.debug("Add new friend: " + friend.getId());
        return friendsService.add(principal.getName(), friend.getId());
    }

    @RequestMapping(value = "/{friendId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(final @PathVariable("friendId") Integer friendId, final Principal principal) {
        LOG.debug("Delete friend by id: " + friendId);
        friendsService.delete(principal.getName(), friendId);
    }
}
