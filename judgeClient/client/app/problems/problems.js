'use strict';

angular.module('codeBossApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('problems', {
        url: '/problems',
        templateUrl: 'app/problems/problems.html',
        controller: 'ProblemsCtrl'
      });
  });