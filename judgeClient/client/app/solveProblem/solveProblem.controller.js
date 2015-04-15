angular.module('codeBossApp')
  .controller('SolveProblemCtrl', function ($scope, $http, $location, $stateParams, Auth) {

  		console.log($stateParams.problemId);
       $scope.getCurrentUser = Auth.getCurrentUser;

  	 
  	  $scope.clickCount = 0;	

      $http.get('/api/problems/' + $stateParams.problemId).success(function(solveProblem) {
        $scope.problem = solveProblem;
        $scope.problemDifficulty = $scope.problem.difficulty;
        $scope.problemID = $scope.problem._id;
        $scope.problemName = $scope.problem.name;
        $scope.problemDescription = $scope.problem.description;
        $scope.example = $scope.problem.example;
        $scope.theInput = $scope.problem.input;
        $scope.output = $scope.problem.output;
        $scope.timeoutNumber = 5000;
        $scope.theLanguage = "java";
      });	

       $scope.sendSubmission = function(theCode,userID,problemID,problemDiff,input,output) {


        console.log(theCode,userID,problemID,problemDiff,input,output, $scope.timeoutNumber);

        $http.post('/api/solutions', {code:theCode, userId:userID, problemId:problemID,
          problemDifficulty:problemDiff, testInput:input, expectedOutput:output,
          language:$scope.theLanguage,timeout:$scope.timeoutNumber}).
        
          success(function(data, status, headers, config) {
          // this callback will be called asynchronously
          // when the response is available
          $scope.outputAfter = status;
          if(status==201){
            $scope.outputAfter = "Entered Response Succesfully";
          }

          }).
          error(function(data, status, headers, config) {
            // called asynchronously if an error occurs
          // or server returns response with an error status.
          }); 
      };
	
  });
