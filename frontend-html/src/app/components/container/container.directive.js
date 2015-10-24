(function () {
  'use strict';

  angular
    .module('devDay2015')
    .directive('container', container);

  /** @ngInject */
  function container() {
    return {
      restrict: 'E',
      transclude: true,
      templateUrl: 'app/components/container/container.html',
      scope: {
        fluid: '='
      }
    };
  }

})();
