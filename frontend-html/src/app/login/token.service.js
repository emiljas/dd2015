(function () {
  'use strict';

  angular.module('devDay2015')
    .service('tokenService', function ($http) {
        var userId = 0;

      this.saveSessionToken = function (token, userID) {
          sessionStorage.token = token;
          userId = userID;
      };

      this.getSessionToken = function () {
        return sessionStorage.token;
      };

      this.deleteSessionToken = function () {
        delete sessionStorage.token;
      };

      this.setAuthorizationToken = function (token) {
        $http.defaults.headers.common.Authorization = 'Basic ' + token;
      };

    });
})();
