'use strict';

describe('Controller: SolveProblemCtrl', function () {

  // load the controller's module
  beforeEach(module('codeBossApp'));

  var SolveProblemCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SolveProblemCtrl = $controller('SolveProblemCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
