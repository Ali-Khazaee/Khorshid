'use strict'

const Path = require('path')

module.exports.ROOT = Path.join(__dirname, '..', Path.sep)
module.exports.PUBLIC = Path.join(__dirname, '..', Path.sep, 'Public', Path.sep)

module.exports.DB_HOST = '127.0.0.1'
module.exports.DB_USERNAME = 'root'
module.exports.DB_PASSWORD = '12345'
module.exports.DB_DATABASE = 'disruptor'

module.exports.HAPI_HOST = '127.0.0.1'
module.exports.HAPI_PORT = 80
