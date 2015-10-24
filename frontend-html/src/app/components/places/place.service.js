(function () {
  'use strict';

  angular.module('devDay2015')
    .service('placeService', function ($http) {

      this.getPlaces = function () {
        return $http.get('/pietryna/api/places');
      };

    });
})();
