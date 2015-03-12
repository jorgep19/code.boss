'use strict';

angular.module('codeBossApp')
  .controller('NavbarCtrl', function ($scope, $location, Auth) {
    $scope.menu = [{
      'title': 'Home',
      'link': '/home'
    },
    {
      'title': 'Problems',
      'link': '/problems'
    },
    {
      'title': 'Statistics',
      'link': '/statistics'
    },
    {
      'title': 'Suggest a Problem',
      'link': '/suggest'
    }
    ];

    $scope.isCollapsed = true;
    $scope.isLoggedIn = Auth.isLoggedIn;
    $scope.isAdmin = Auth.isAdmin;
    $scope.getCurrentUser = Auth.getCurrentUser;

    $scope.logout = function() {
      Auth.logout();
      $location.path('/login');
    };

    $scope.isActive = function(route) {
      return route === $location.path();
    };
  });