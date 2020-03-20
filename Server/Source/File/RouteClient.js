module.exports = (Server) =>
{
    Server.route(
    {
        method: 'GET',
        path: '/',
        handler: (Request, H) =>
        {
            return 'Hello World!'
        }
    })
}
