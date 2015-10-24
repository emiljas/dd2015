(function () {
  'use strict';

  angular.module('devDay2015')
    .service('friendService', function ($http) {

      this.getUsers = function () {
        return $http.get('/pietryna/api/users');
      };

      this.getFriends = function () {
        return $http.get('/pietryna/api/friends');
      };

      this.addFriend = function (friend) {
        return $http.post('/pietryna/api/friends/', friend);
      };

      this.removeFriend = function (friend) {
        return $http.delete('/pietryna/api/friends/' + friend.id);
      };

    });
})();
