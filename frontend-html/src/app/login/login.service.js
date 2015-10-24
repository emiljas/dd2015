(function () {
  'use strict';

  angular.module('devDay2015')
    .service('loginService', function ($http, $base64, $q, tokenService) {

      this.login = function (email) {
        var hash = $base64.encode(email + ':');
        tokenService.setAuthorizationToken(hash);
        return $http.get('/pietryna/api/users/me').then(function (response) {
          // disclaimer: note that Basic Auth token will be stored in a session storage
          // this serves only as an example - do not do it live!
          tokenService.saveSessionToken(hash);

          return $q.resolve(response.data);
        }, function (error) {
          var message = 'There was a problem logging in, check if the backend server is up and running. (Error ' + error.status + ': ' + error.statusText + ')';
          if (error.status === 401) {
            message = 'There is no such an email as ' + email;
          }
          return $q.reject(message);
        });
      };

      this.logout = function () {
        tokenService.setAuthorizationToken('');
        tokenService.deleteSessionToken();
        return $q.resolve();
      };

    });
})();
