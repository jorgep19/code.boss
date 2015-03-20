'use strict';

var mongoose = require('mongoose'),
    Schema = mongoose.Schema;

var ProblemSchema = new Schema({
  name: String,
  active: Boolean,
  difficulty: String,
  description: String,
  tags:[String]
});

module.exports = mongoose.model('Problem', ProblemSchema);