'use strict'

const Hapi = require('@hapi/hapi')

const { DEBUG } = require('./File/Utlity')

process.on('uncaughtException', (Error) => DEBUG('AppUncaughtException', { Error: Error }))
process.on('unhandledRejection', (Error) => DEBUG('AppUnhandledRejection', { Error: Error }))

const Main = async() =>
{
    const Server = Hapi.server({ port: 3000, host: 'localhost' })

    await Server.start()

    DEBUG('Server', { Info: Server.info.uri })
}

Main()
