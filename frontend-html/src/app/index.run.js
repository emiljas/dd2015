(function () {
  'use strict';

  angular
    .module('devDay2015')
    .run(runBlock);

  /** @ngInject */
  function runBlock($rootScope, $location, tokenService) {

    // keep user logged in after page refresh
    if (tokenService.getSessionToken()) {
      tokenService.setAuthorizationToken(tokenService.getSessionToken());
    }

    $rootScope.$on('$locationChangeStart', function () {
      // redirect to login page if not logged in
      if ($location.path() !== '/' && !tokenService.getSessionToken()) {
        $location.path('/');
      }
    });
  }

})();
