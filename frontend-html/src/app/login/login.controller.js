(function () {
  'use strict';

  angular.module('devDay2015')
    .controller('LoginController', LoginController);

  /** @ngInject */
  function LoginController(loginService, $location) {
    var vm = this;

    vm.email = '';

    vm.invalidCredentials = {
      visible: false,
      message: 'message'
    };

    vm.login = {
      inProgress: false
    };

    vm.performLogin = function () {
      vm.invalidCredentials.message = '';
      vm.invalidCredentials.visible = false;
      vm.login.inProgress = true;

      loginService.login(vm.email).then(function () {
        $location.path('/home');
      }, function (error) {
        vm.invalidCredentials.message = error;
        vm.invalidCredentials.visible = true;
      }).finally(function () {
        vm.login.inProgress = false;
      });
    };

  }
})();
