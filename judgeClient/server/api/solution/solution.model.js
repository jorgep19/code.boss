'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var SolutionSchema = new Schema({
  code: String,	
  userId: String,
  problemId: String,
  problemDifficulty:Number,
  testInput: String,
  expectedOutput: String,
  language: String,
  timeout: Number
});

module.exports = mongoose.model('Solution', SolutionSchema);