  'use strict';

angular.module('codeBossApp')
  .controller('StatisticsCtrl', function ($scope, $http, Auth) {

   $scope.getCurrentUser = Auth.getCurrentUser;
  
  });
