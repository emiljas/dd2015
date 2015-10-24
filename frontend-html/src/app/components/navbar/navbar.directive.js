(function () {
  'use strict';

  angular
    .module('devDay2015')
    .directive('navbar', navbar);

  /** @ngInject */
  function navbar($location, loginService) {
    var directive = {
      restrict: 'E',
      templateUrl: 'app/components/navbar/navbar.html',
      link: link,
      scope: {
        fluid: '@'
      }
    };

    return directive;

    /** @ngInject */
    function link(scope) {
      scope.logout = function () {
        loginService.logout().then(function () {
          $location.path('/');
        });
      };

      scope.toggleExpand = function () {
        scope.expand = !scope.expand;
        scope.style = scope.expand ? {display: 'block'} : {};
      };
    }
  }

})();
