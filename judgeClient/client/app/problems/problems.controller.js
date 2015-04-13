'use strict';

angular.module('codeBossApp')
  .controller('ProblemsCtrl', function ($scope, $http, $location) {


  	  $scope.clickCount = 0;
      
      $http.get('/api/problems').success(function(problems) {
        $scope.problems = problems;
      });
     	$scope.problem1 = true;  

      //Function to return all the attributes form the problem entity
     $scope.toggle = function(description,id,example,input,output,active) {
           console.log(description);
       		     if($scope.clickCount == 0){
                  $scope.problem1 = !$scope.problem1;
          }
          $scope.prblDescription = description;
          $scope.problemID = id;
          $scope.active = active;
          $scope.example = example;
          $scope.theInput = input;
          $scope.output = output;
          console.log(active);
          $scope.clickCount = $scope.clickCount +1;
     	};


      $scope.isActive = function(route) {
        return route === $location.path();
      };

      $scope.solveProblem = function() {
        // go to solve problem view;
        console.log($scope.problemID);     
        $location.path('/solveProblem/'+$scope.problemID);
      };
  });
