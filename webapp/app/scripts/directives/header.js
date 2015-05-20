'use strict';

/**
 * @ngdoc directive
 * @name yamaApp.directive:header
 * @description
 * # header
 */
angular.module('yama2showcaseApp').directive('header', function ($rootScope) {
	return {
		restrict: 'E',
		link: function postLink(scope, element, attrs) {
			$rootScope.header = attrs.header;
			$rootScope.subheader = attrs.subheader;
		}
	};
});
