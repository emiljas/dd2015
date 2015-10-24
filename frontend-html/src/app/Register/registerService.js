(function () {
    'use strict';

    angular.module('devDay2015')
      .service('registerService', function ($http) {

          this.register = function (username, email, password, firstName, lastName, photo) {
              console.log(firstName);
              console.log(lastName);
              return $http.post('/pietryna/api/users/register', {
                      UserName: username,
                      Email: email,
                      Password: password,
                      FirstName: firstName,
                      LastName: lastName,
                      Photo: photo
              });
          };
      });
})();
