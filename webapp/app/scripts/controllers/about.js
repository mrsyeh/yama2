'use strict';

/**
 * @ngdoc function
 * @name yamaApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the yamaApp
 */
angular.module('yama2showcaseApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
