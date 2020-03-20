'use strict'

const MySQL = require('mysql')
const Hapi = require('@hapi/hapi')

const Config = require('./File/Config')

const { DEBUG } = require('./File/Utlity')

process.on('uncaughtException', (Error) => DEBUG('AppUncaughtException', { Error: Error }))
process.on('unhandledRejection', (Error) => DEBUG('AppUnhandledRejection', { Error: Error }))

const Main = async() =>
{
    const DB = MySQL.createPool({ host: Config.DB_HOST, user: Config.DB_USERNAME, password: Config.DB_PASSWORD, database: Config.DB_DATABASE })

    DB.on('acquire', (Connection) =>
    {
        DEBUG('DB', { Tag: 'Acquire', ID: Connection.threadId })
    })

    DB.on('connection', (Connection) =>
    {
        DEBUG('DB', { Tag: 'Connection', ID: Connection.threadId })
    })

    DB.on('release', (Connection) =>
    {
        DEBUG('DB', { Tag: 'Release', ID: Connection.threadId })
    })

    DB.on('enqueue', () =>
    {
        DEBUG('DB', { Tag: 'Enqueue' })
    })

    const Server = Hapi.server({ host: Config.HAPI_HOST, port: Config.HAPI_PORT })

    await Server.start()

    require('./File/RouteClient')(Server, DB)

    DEBUG('Hapi', { Info: Server.info.uri })
}

Main()
