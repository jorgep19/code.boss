'use strict';

angular.module('codeBossApp')
  .config(function ($stateProvider) {
    $stateProvider
      .state('suggest', {
        url: '/suggest',
        templateUrl: 'app/suggest/suggest.html',
        controller: 'SuggestCtrl'
      });
  });