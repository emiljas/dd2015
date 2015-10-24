(function () {
  'use strict';

  angular
    .module('devDay2015')
    .controller('MainController', MainController);

  /** @ngInject */
  function MainController(placeService, mapService) {
    var vm = this;

    vm.map = mapService.config;
    vm.topRatedPlaces = [];
    vm.places = [];

    vm.getIndex = function (row, col) {
      return col + (row * 4);
    };

    placeService.getPlaces().success(function (places) {
        console.log(places);
      vm.places = places;
      vm.topRatedPlaces = prepareTopRatedPlaces(places.slice(0, 8));
    });

    /* Groups places into 4 items group for easy display 4 per row by ng-repeat */
    var prepareTopRatedPlaces = function (places) {
      var result = [], groupSize = 4, i, len;
      for (i = 0, len = places.length; i < len; i += groupSize) {
        result.push(places.slice(i, i + groupSize));
      }
      return result;
    };

  }
})();
