'use strict';

/**
 * @ngdoc service
 * @name yamaApp.Users
 * @description
 * # Users
 * Factory in the yamaApp.
 */
angular.module('yama2showcaseApp').factory('Users', function (Restangular) {
	return Restangular.service('users');
});
