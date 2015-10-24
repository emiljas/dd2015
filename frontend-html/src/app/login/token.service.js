(function () {
  'use strict';

  angular.module('devDay2015')
    .service('tokenService', function ($http) {

      this.saveSessionToken = function (token) {
        sessionStorage.token = token;
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
