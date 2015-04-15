'use strict';

var _ = require('lodash');
var Solution = require('./solution.model');

// Get list of solutions
exports.index = function(req, res) {
  Solution.find(function (err, solutions) {
    if(err) { return handleError(res, err); }
    return res.json(200, solutions);
  });
};

// Get a single solution
exports.show = function(req, res) {
  Solution.findById(req.params.id, function (err, solution) {
    if(err) { return handleError(res, err); }
    if(!solution) { return res.send(404); }
    return res.json(solution);
  });
};

// Creates a new solution in the DB.
exports.create = function(req, res) {
  Solution.create(req.body, function(err, solution) {
    if(err) { return handleError(res, err); }
    return res.json(201, solution);
  });
};

// Updates an existing solution in the DB.
exports.update = function(req, res) {
  if(req.body._id) { delete req.body._id; }
  Solution.findById(req.params.id, function (err, solution) {
    if (err) { return handleError(res, err); }
    if(!solution) { return res.send(404); }
    var updated = _.merge(solution, req.body);
    updated.save(function (err) {
      if (err) { return handleError(res, err); }
      return res.json(200, solution);
    });
  });
};

// Deletes a solution from the DB.
exports.destroy = function(req, res) {
  Solution.findById(req.params.id, function (err, solution) {
    if(err) { return handleError(res, err); }
    if(!solution) { return res.send(404); }
    solution.remove(function(err) {
      if(err) { return handleError(res, err); }
      return res.send(204);
    });
  });
};

function handleError(res, err) {
  return res.send(500, err);
}