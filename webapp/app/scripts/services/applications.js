'use strict';

/**
 * @ngdoc service
 * @name yamaApp.Applications
 * @description
 * # Applications
 * Factory in the yamaApp.
 */
angular.module('yama2showcaseApp').factory('Applications', function (Restangular) {
	return Restangular.service('applications');
});
