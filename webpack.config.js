module.exports = {
  context: __dirname + "/public/js/src",
  entry: './script.js',
  output: {
    path: __dirname + "/public/js/src",
    filename: "bundle.js"
  },
  watch: true
}
