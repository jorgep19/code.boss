'use strict';

angular.module('codeBossApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('solveProblem', {
        url: '/solveProblem/:problemId',
        templateUrl: 'app/solveProblem/solveProblem.html',
        controller: 'SolveProblemCtrl'
      });
  });