angular.module('codeBossApp')
  .controller('SolveProblemCtrl', function ($scope, $http, $location) {


  	 
  	  $scope.clickCount = 0;	

      $http.get('/api/problems').success(function(solveProblem) {
        $scope.problems = solveProblem;
        $scope.prblDescription = $scope.problems[1].description;
        
      });		
  });
