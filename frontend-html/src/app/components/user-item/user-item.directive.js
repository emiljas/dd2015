(function () {
  'use strict';

  angular
    .module('devDay2015')
    .directive('userItem', userItem);

  /** @ngInject */
  function userItem() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/user-item/user-item.html',
      scope: {
        firstName: '=',
        lastName: '=',
        buttonClickExpression: '&',
        buttonChar: '@'
      }
    };
  }

})();
