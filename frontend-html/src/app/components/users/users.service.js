(function () {
  'use strict';

  angular
    .module('devDay2015')
    .service('usersService', users);

  /** @ngInject */
  function users($http) {

    this.getUsers = function () {
      return $http.get('/pietryna/api/users');
    };

    this.getMe = function () {
      return $http.get('/pietryna/api/users/me');
    };
  }

})();
