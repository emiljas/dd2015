(function () {
  'use strict';

  angular.module('devDay2015')
    .directive('place', place);

  /** @ngInject */
  function place() {
    return {
      restrict: 'E',
      templateUrl: 'app/components/places/place.html',
      scope: {
        model: '='
      }
    };
  }
})();
