(function () {
  'use strict';

  angular
    .module('devDay2015')
    .config(routeConfig);

  function routeConfig($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'app/login/login.html',
        controller: 'LoginController',
        controllerAs: 'vm'
      })
      .when('/home', {
        templateUrl: 'app/main/main.html',
        controller: 'MainController',
        controllerAs: 'vm'
      })
      .when('/friends', {
        templateUrl: 'app/friends/friends.html',
        controller: 'FriendsController',
        controllerAs: 'vm'
      })
        .when('/register', {
            templateUrl: 'app/Register/register.html',
            controller: 'RegisterController',
            controllerAs: 'vm'
        })
        .when('/details/:placeId', {
            templateUrl: 'app/details/details.html',
            controller: 'DetailsController',
            controllerAs: 'vm'
        })
      .otherwise({
        redirectTo: '/'
      });
  }

})();
