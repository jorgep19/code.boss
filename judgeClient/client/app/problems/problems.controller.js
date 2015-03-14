'use strict';

angular.module('codeBossApp')
  .controller('ProblemsCtrl', function ($scope) {

      $scope.problem1 = false;	
      $scope.problem2 = false;	
      $scope.problem2 = false;		
      $scope.problems = [
        {id:1 ,problem:'Find the area',difficulty: 'Easy', description: 'Find the area of a cirlce'},
        {id:2 ,problem:'2D arrays', difficulty: 'Medium' ,description: 'create a million 2D arrays'},
        {id:3 ,problem:'The best problem', difficulty: 'Medium', description: 'Solve this amazing problem and get a million dollars'}
    ];
       
       $scope.toggle = function(a) {
       	if( a == 1 ){
       		$scope.problem1  = !scope.problem1;
       }
       else {
       	console.log("nada");
       }
       
   };
    

  

  });
