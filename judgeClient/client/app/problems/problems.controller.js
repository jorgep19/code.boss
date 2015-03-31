'use strict';

angular.module('codeBossApp')
  .controller('ProblemsCtrl', function ($scope, $http, $location) {


  	  $scope.prblDescription = "hello";
  	  $scope.clickCount = 0;	

      $http.get('/api/problems').success(function(problems) {
        $scope.problems = problems;
      });

     	$scope.problem1 = true;  

       $scope.toggle = function(index) {

           console.log(index);
       		if($scope.clickCount == 0){
            $scope.problem1 = !$scope.problem1;
          }

          $scope.prblDescription = $scope.problems[index].description;
          $scope.clickCount = $scope.clickCount +1;
       	}

        $scope.isActive = function(route) {
        return route === $location.path();
    };

        $scope.solveProblem = '/solveProblem';

        $scope.solve = function(index) {
          
        }
       		
       		
  });
