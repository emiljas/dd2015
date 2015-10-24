package com.hp.devday.pietryna.friends;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Friendship between provided users already exists!")
public class FriendshipExistsException extends RuntimeException {
}
