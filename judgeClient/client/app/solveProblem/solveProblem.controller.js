angular.module('codeBossApp')
  .controller('SolveProblemCtrl', function ($scope, $http, $location, $stateParams) {

  		console.log($stateParams.problemId);
  	 
  	  $scope.clickCount = 0;	

      $http.get('/api/problems/' + $stateParams.problemId).success(function(solveProblem) {
        $scope.problem = solveProblem;
        $scope.problemName = $scope.problem.name;
        $scope.problemDescription = $scope.problem.description
        $scope.example = $scope.problem.example;
        $scope.theInput = $scope.problem.input;
        $scope.output = $scope.problem.output;
        
      });		
  });
