'use strict';

angular.module('codeBossApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('solveProblem', {
        url: '/solveProblem',
        templateUrl: 'app/solveProblem/solveProblem.html',
        controller: 'SolveProblemCtrl'
      });
  });