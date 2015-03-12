'use strict';

describe('Controller: SuggestCtrl', function () {

  // load the controller's module
  beforeEach(module('codeBossApp'));

  var SuggestCtrl, scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    SuggestCtrl = $controller('SuggestCtrl', {
      $scope: scope
    });
  }));

  it('should ...', function () {
    expect(1).toEqual(1);
  });
});
