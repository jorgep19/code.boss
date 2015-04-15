/**
 * Broadcast updates to client when the model changes
 */

'use strict';

var Solution = require('./solution.model');

exports.register = function(socket) {
  Solution.schema.post('save', function (doc) {
    onSave(socket, doc);
  });
  Solution.schema.post('remove', function (doc) {
    onRemove(socket, doc);
  });
}

function onSave(socket, doc, cb) {
  socket.emit('solution:save', doc);
}

function onRemove(socket, doc, cb) {
  socket.emit('solution:remove', doc);
}