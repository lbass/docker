var http = require("http");
var path = require("path");
var express = require("express");
var logger = require("morgan");
var bodyParser = require("body-parser");
var winston = require('winston');


var logger = new (winston.Logger)({
   transports: [
       new (winston.transports.Console)(),
       new (winston.transports.File)({
          filename: '/tmp/somefile.log'
      })
   ]
});





var app = express();

// view settings
app.set("views", path.resolve(__dirname, "views"));
app.set("view engine", "ejs");


// store the contents
var entries = [];
app.locals.entries = entries;

logger.log('info', 'Hello distributed log files!');

출처: http://mcpaint.tistory.com/198 [MC빼인트와 함께]

// middleware chain
app.use(bodyParser.urlencoded({extended: false}));
app.use(express.static('public'));

app.get("/", function(request, response){
	console.log(entries.length);
	console.log(entries[0]);
	if( entries.length ){
		console.log(entries[0].title);
	}

	response.render("index");
});

app.get("/new-entry", function(request, response){
	response.render("new-entry");
});

app.post("/new-entry", function(request, response){
	console.log(request.body.title + " / " + request.body.body);
	if( !request.body.title || ! request.body.body){
		response.status(400).send("Entries must have a title and a body.");
		return;
	}

	entries.push({
		title: request.body.title,
		content: request.body.body,
		published : new Date()
	});

	response.redirect("/");
});

app.use(function(request, response){
	response.status(404).render("404");
});

var PORT = process.env.PORT || 8080;
http.createServer(app).listen(PORT, function(){
	console.log("Guestbook app started on port "  + PORT);
})
