(function () {
    'use strict';

    angular
      .module('devDay2015')
      .controller('RegisterController', RegisterController);

    /** @ngInject */
    function RegisterController(registerService, $location) {
        var vm = this;

        vm.emailAddress = '';
        vm.username = '';
        vm.password = '';
        vm.firstname = '';
        vm.lastname = '';
        vm.photo = '';

        vm.register = function () {
            registerService.register(vm.username, vm.emailAddress, vm.password, vm.firstname, vm.lastname, vm.photo);
            console.log('registred');
            $location.path('/login');
        }
    }
})();
