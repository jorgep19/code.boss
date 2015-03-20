'use strict';

describe('Service: Problem', function () {

  // load the service's module
  beforeEach(module('codeBossApp'));

  // instantiate service
  var Problem;
  beforeEach(inject(function (_Problem_) {
    Problem = _Problem_;
  }));

  it('should do something', function () {
    expect(!!Problem).toBe(true);
  });

});
