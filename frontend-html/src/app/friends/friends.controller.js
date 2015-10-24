(function () {
  'use strict';

  angular.module('devDay2015')
    .controller('FriendsController', FriendsController);

  /** @ngInject */
  function FriendsController(friendService, usersService) {
    var vm = this;

    vm.friends = [];
    vm.filteredUsers = [];

    var me = {},
        friendIds = [];

    var start = 0,
        limit = 100;

    vm.removeFriend = function (friend) {
      friendService.removeFriend(friend).success(function () {
        getAllFriends();
      });
    };

    vm.addFriend = function (user) {
      friendService.addFriend(user).success(function () {
        getAllFriends();
      });
    };

    vm.removeFriendsAndUserFilter = function (item) {
      if (item.id === me.id) {
        return false;
      } else if (friendIds.indexOf(item.id) >= 0) {
        return false;
      }
      return true;
    };

    var getAllFriends = function () {
      friendService.getFriends().success(function (friends) {
        vm.friends = friends;
        updateFriendIds();
      });
    };

    var updateFriendIds = function () {
      friendIds.length = 0;
      for (var i = vm.friends.length - 1; i >= 0; i--) {
        friendIds.push(vm.friends[i].id);
      }
    };

    var getAllUsers = function () {
      friendService.getUsers().success(function (users) {
        vm.filteredUsers = users.slice(start, limit);
      });
    };

    usersService.getMe().success(function (data) {
      me = data;

      getAllFriends();
      getAllUsers();
    });

  }

})();
