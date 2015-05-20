'use strict';

/**
 * @ngdoc service
 * @name yamaApp.Roles
 * @description
 * # Roles
 * Factory in the yamaApp.
 */
angular.module('yama2showcaseApp').factory('Roles', function (Restangular) {
	return Restangular.service('roles');
});
