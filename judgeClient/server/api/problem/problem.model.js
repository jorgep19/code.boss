'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var ProblemSchema = new Schema({
  name: String,
  active: Boolean,
  difficulty: Number,
  description: String,
  example: String,
  input: String,
  output: String,
});

module.exports = mongoose.model('Problem', ProblemSchema);