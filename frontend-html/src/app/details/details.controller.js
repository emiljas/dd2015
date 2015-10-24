(function () {
    'use strict';

    angular
      .module('devDay2015')
      .controller('DetailsController', DetailsController);

    /** @ngInject */
    function DetailsController(placeService, mapService, $routeParams, $scope) {
        var vm = this;
        var userId = 1;
        vm.id = $routeParams.placeId;
        vm.place = {
            userRate: 1,
            avergeRate: 2
        };


        vm.rate = function () {

            setTimeout(function() {
                placeService.changeRate(userId, vm.id, vm.place.userRate).success(function () {
                    vm.message = "Rated successfully";
                });
            }, 100);
        };

        placeService.getMyRate(userId, vm.id).success(function (rate) {
            if (rate == 0) rate = 1;
            vm.place.userRate = rate;
        });

        placeService.getRate(vm.id).success(function (rate) {
            if (rate == 0) rate = 1;
            vm.place.avergeRate = rate;
        });

        placeService.getPlaces().success(function (places) {
            for (var i = 0; i < places.length; i++) {
                var place = places[i];
                
                if (place.id == vm.id) {
                    vm.place.address = place.address;
                    vm.place.category = place.category;
                }
            }
        });
    }
})();
