(function() {
  'use strict';

  angular
    .module('devDay2015')
    .config(['$logProvider', '$animateProvider', config]);

  /** @ngInject */
  function config($logProvider, $animateProvider) {
    // Enable log
    $logProvider.debugEnabled(true);

    // Restrict ngAnimate usage to elements
    $animateProvider.classNameFilter(/^((?!(no-animation)).)*$/);
  }

})();
