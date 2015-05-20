'use strict';

/**
 * @ngdoc service
 * @name yamaApp.Monitoring
 * @description
 * # Monitoring
 * Factory in the yamaApp.
 */
angular.module('yama2showcaseApp').factory('Monitoring', function (Restangular) {
	return {
		metrics: Restangular.service('actuator/metrics')
	};
});
