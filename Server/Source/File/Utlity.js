'use strict'

const Util = require('util')
const Winston = require('winston')

const Logger = Winston.createLogger(
{
    format: Winston.format.combine(Winston.format.printf((Data) => Data.message.replace(/\n|\r/g, '').replace(/\s+/g, ' ').trim())),
    transports: [ new Winston.transports.Console({ json: false }) ]
})

module.exports.DEBUG = (Tag, Message = { }) =>
{
    Message.T = this.Time()

    Logger.log('error', `${Tag} - ${Util.inspect(Message, false, null)}`)
}

module.exports.IsUndefined = (Value) =>
{
    return Value === undefined || typeof Value === 'undefined' || Value == null
}

module.exports.IsDefined = (Value) =>
{
    return !this.IsUndefined(Value)
}

module.exports.TimeMili = () =>
{
    return Math.floor(Date.now())
}

module.exports.Time = () =>
{
    return Math.floor(Date.now() / 1000)
}

module.exports.IsVaildJSON = (Message) =>
{
    try
    {
        return JSON.parse(Message)
    }
    catch (Exception)
    {
        return null
    }
}
