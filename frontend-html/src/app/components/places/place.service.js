(function () {
  'use strict';

  angular.module('devDay2015')
    .service('placeService', function ($http) {

      this.getPlaces = function () {
        return $http.get('/pietryna/api/places');
      };

      this.changeRate = function (userId, placeId, rate) {
          return $http.post('pietryna/api/places/change-rate', {
              UserId: userId,
              PlaceId: placeId,
              Rate: rate
          });
      };

      this.getMyRate = function (userId, placeId) {
          return $http.get('pietryna/api/places/get-my-rate?userId=' + userId + '&placeId=' + placeId);
      };

      this.getRate = function (placeId) {
          return $http.get('pietryna/api/places/get-rate?placeId=' + placeId);
      };


    });
})();
