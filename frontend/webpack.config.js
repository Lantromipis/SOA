const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')

module.exports = {
    // Where files should be sent once they are bundled
    output: {
        path: path.join(__dirname, '../src/main/resources/static'),
        filename: 'index.bundle.js'
    },
    // webpack 5 comes with devServer which loads in development mode
    devServer: {
        port: 3000,
        proxy: {
            '/api': 'http://localhost:8080',
        }
    },
    // Rules of how webpack will take our files, compile & bundle them for the browser
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: /nodeModules/,
                use: {
                    loader: 'babel-loader'
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    },
    plugins: [new HtmlWebpackPlugin({ template: './public/index.html' })],
}
