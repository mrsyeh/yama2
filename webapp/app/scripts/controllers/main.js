'use strict';

/**
 * @ngdoc function
 * @name yamaApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the yamaApp
 */
angular.module('yama2showcaseApp').controller('MainCtrl', function ($scope, Monitoring) {
	Monitoring.metrics.one().get().then(function(metrics) {
		$scope.metrics = [];

		angular.forEach(metrics, function(value, key) {
			if (!angular.isFunction(value)) {
				this.push({ key: key, value: value });
			}
		}, $scope.metrics);
	});
});
