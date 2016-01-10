var context, canvas;
var canvasBoundaries;
var renderer;
var mouseX = 15, mouseY = 15;
var board = new Array(50);
for (var i = 0; i < 50; i++) {
	board[i] = new Array(50);
}

var SHOW_ALL_EDGES = true;

var handleMouseMove = function (event) {
	var doc = document.documentElement;

	var top = (window.pageYOffset || doc.scrollTop)  - (doc.clientTop || 0);
	mouseX = event.x - canvasBoundaries.left;
	mouseY = event.y - canvasBoundaries.top + top;
}



window.onload = function () {
	canvas = document.getElementById("area");
	canvas.addEventListener("mousemove", handleMouseMove, true);
	canvasBoundaries = canvas.getBoundingClientRect();
	context = canvas.getContext("2d");

	renderer = new Renderer(context);
	processGraphData(inputGraphData);
	update();
};

var processGraphData = function (graphData) {
	var vertices = graphData.vertices;
	for (var vertexName in vertices) {
		var x = getRandomInt(15, 800);
		var y = getRandomInt(15, 800);
		while (board[Math.round(x / 50)][Math.round(y / 50)]) {
			var x = getRandomInt(15, 800);
			var y = getRandomInt(15, 800);
		}
		board[Math.round(x / 50)][Math.round(y / 50)] = vertexName;

		vertices[vertexName] = {
			x: Math.round(x / 50) * 50 + 25,
			y: Math.round(y / 50) * 50 + 25,
			neighbours: vertices[vertexName]
		};
	}
}

function getRandomInt(min, max) {
  return Math.floor(Math.random() * (max - min)) + min;
}

var update = function () {
	renderer.clear();

	// for (var i = 0; i < 20; i++) {
	// 	renderer.renderLine({ x: i * 50, y: 0}, { x: i * 50, y: 1000}, "black", 2);
	// 	renderer.renderLine({ x: 0, y: i * 50}, { x: 1000, y: i * 50}, "black", 2);
	// }

	// for (var i = 0; i < 50; i++) {
	// 	for (var j = 0; j < 50; j++) {
	// 		if (board[i][j]) {
	// 			renderer.renderRect(i * 50, j * 50, 50, 50, "yellow", 2);
	// 		}
	// 	}
	// }

	renderer.renderGraph(inputGraphData);

	// renderer.renderRect(Math.floor(mouseX / 50) * 50, Math.floor(mouseY / 50) * 50, 50, 50, "green", 1);

	if (board[Math.floor(mouseX / 50)][Math.floor(mouseY / 50)]) {
		renderer.renderText(mouseX, mouseY, board[Math.floor(mouseX / 50)][Math.floor(mouseY / 50)], "black");
	}

	setTimeout(update, 1000 / 60);
}

var Renderer = function (context) {
	this.context = context;
};

Renderer.prototype.renderVertices = function (graphData) {
	var vertices = graphData.vertices;
	for (var vertexName in vertices) {
		this.renderNode(vertexName, graphData);
		var neighbours = vertices[vertexName].neighbours;
		this.renderEdges(vertexName, graphData);
	}
}

Renderer.prototype.renderNode = function (vertexName, graphData) {
	var vertexData = graphData.vertices[vertexName];
	if (inPath.indexOf(vertexName) >= 0) {
		this.renderCircle(vertexData.x, vertexData.y, 15, "red", 1, "green");
		this.renderText(vertexData.x, vertexData.y, vertexName, "black");
	} else {
		this.renderCircle(vertexData.x, vertexData.y, 15, "red", 0);
	}
}

Renderer.prototype.renderEdges = function(vertexName, graphData) {
	var neighbours = graphData.vertices[vertexName].neighbours;
	var vertexData = graphData.vertices[vertexName];

	for (var i = 0; i < neighbours.length; i++) {
		var neighbourData = graphData.vertices[neighbours[i].neighbourKey];
		
		var dir = {
			x: vertexData.x - neighbourData.x,
			y: vertexData.y - neighbourData.y
		};
		var len = Math.sqrt(dir.x * dir.x + dir.y * dir.y);
		if (len != 0) {
			dir.x /= len;
			dir.y /= len;
		}

		var edgeColor = "blue";
		if (inPath.indexOf(vertexName) >= 0 && inPath.indexOf(neighbours[i].neighbourKey) >= 0) {
			edgeColor = "orange";

			this.renderLine({
				x: vertexData.x + (-1) * dir.x * 15,
				y: vertexData.y + (-1) * dir.y * 15
			}, {
				x: neighbourData.x + dir.x * 15,
				y: neighbourData.y + dir.y * 15
			}, edgeColor, LINE_WIDTH);
		} else if (SHOW_ALL_EDGES) {
			this.renderLine({
				x: vertexData.x + (-1) * dir.x * 15,
				y: vertexData.y + (-1) * dir.y * 15
			}, {
				x: neighbourData.x + dir.x * 15,
				y: neighbourData.y + dir.y * 15
			}, edgeColor, 1);
		}

		

		// if (edge.weight !== 0) {
		// 	context.font = DEFAULT_FONT; 
		// 	context.fillStyle = EDGE_WEIGHT_FILL_STYLE;
		// 	context.fillText(edge.weight, 
		// 					 (node1.x + node2.x) / 2,
		// 					 (node1.y + node2.y) / 2 - 5); // Magic

		// }
	}
};

Renderer.prototype.renderText = function (x, y, text, color) {
	this.context.font = DEFAULT_FONT;
	this.context.fillStyle = color  ? color : "blue";
	this.context.fillText(text, x, y);
}

Renderer.prototype.renderCircle = function (x, y, radius,
											outlineColor,
											isFilled,
											fillColor,
											lineWidth) {
	this.context.beginPath();
	this.context.lineWidth = lineWidth ? lineWidth : LINE_WIDTH;
	context.fillStyle = fillColor;
	context.strokeStyle = outlineColor;

	context.arc(x, y, radius, 0, Math.PI * 2);
	if (isFilled) {
		context.fill();
	}
	context.stroke();
}

Renderer.prototype.renderLine = function (from, to, color, lineWidth) {
	this.context.beginPath();
	this.context.lineWidth = lineWidth ? lineWidth : LINE_WIDTH;
	this.context.strokeStyle = color;

	this.context.moveTo(from.x, from.y);
	this.context.lineTo(to.x, to.y);

	this.context.stroke();
};

Renderer.prototype.renderRect = function (x, y, width, height, color, lineWidth) {
	this.context.beginPath();
	this.context.lineWidth = lineWidth ? lineWidth : LINE_WIDTH;
	this.context.fillStyle = color;

	this.context.fillRect(x, y, width, height);
};

Renderer.prototype.renderGraph = function (graph) {
	this.renderVertices(graph);
};

Renderer.prototype.clear = function () {
	this.context.clearRect(0, 0, 1500, 2000);
}


// Renderer.prototype.renderNodes = function (nodes) {
// 	for (var id in nodes) {
// 		var node = nodes[id];

// 		this.renderCircle(transform.x, transform.y,
// 						  transform.radius, 
// 						  animationState.color,
// 						  animationState.fill,
// 						  animationState.fillColor);

// 		if (state.level || state.level == 0) {
// 			this.renderText(transform.x, transform.y,
// 							state.level, LEVEL_FONT_COLOR);
// 		}

// 		if (state.distance) {
// 			this.renderText(transform.x, transform.y,
// 							state.distance != Infinity ? (state.distance) :
// 							"∞", DISTANCE_FONT_COLOR);
// 		}

// 		this.renderEdges(node.edges);
// 	}
// };


var VISITED_FILL_STYLE = "black";
var VISITED_OUTLINE_STYLE = "red";

var TO_BE_VISITED_FILL_STYLE = "green";
var TO_BE_VISITED_OUTLINE_STYLE = "blue";

var NORMAL_STROKE_STYLE = "black";
var NORMAL_FILL_STYLE = "blue";
var EDGE_STROKE_STYLE = "red";
var EDGE_WEIGHT_FILL_STYLE = "blue";

var ON_PATH_FILL_STYLE = "red"
var ON_PATH_OUTLINE_STYLE = "black";
var START_NODE_FILL_STYLE = "green";
var START_NODE_OUTLINE_STYLE = "black";
var GOAL_NODE_FILL_STYLE = "black";
var GOAL_NODE_OUTLINE_STYLE = "yellow";


var ITERATION_EDGE_COLORING = "blue";

var PROGRESS_BAR_BACKGROUND = "red";
var PROGRESS_BAR_FILLING = "blue";
var PROGRESS_BAR_OUTLINE = "black";

var DISTANCE_FONT_COLOR = "orange";
var LEVEL_FONT_COLOR = "orange";

var DEFAULT_FONT = "bold 20px Arial";

var LINE_WIDTH = 5;
var ARROW_LENGTH = 10;

var DEFAULT_NODE_RADIUS = 30;


var inputGraphData = {
  "start": "gosho",
  "target": "vanko42",
  "vertices": {
    "didi93": [{
      "neighbourKey": "secretdoor7",
      "distance": 10
    }, {
      "neighbourKey": "hacker123",
      "distance": 10
    }, {
      "neighbourKey": "mgeorgieva",
      "distance": 10
    }, {
      "neighbourKey": "peshooo",
      "distance": 10
    }, {
      "neighbourKey": "nikup",
      "distance": 9
    }, {
      "neighbourKey": "didito",
      "distance": 10
    }],
    "didito": [{
      "neighbourKey": "hacker123",
      "distance": 9
    }, {
      "neighbourKey": "vanko42",
      "distance": 10
    }, {
      "neighbourKey": "didi93",
      "distance": 9
    }],
    "gosho": [{
      "neighbourKey": "didi93",
      "distance": 8
    }, {
      "neighbourKey": "didito",
      "distance": 10
    }, {
      "neighbourKey": "vanko42",
      "distance": 10
    }, {
      "neighbourKey": "hacker123",
      "distance": 10
    }, {
      "neighbourKey": "nikup",
      "distance": 19
    }],
    "mongodb": [],
    "peshooo": [{
      "neighbourKey": "hacker123",
      "distance": 10
    }, {
      "neighbourKey": "mgeorgieva",
      "distance": 10
    }, {
      "neighbourKey": "secretdoor7",
      "distance": 10
    }, {
      "neighbourKey": "loloto",
      "distance": 10
    }],
    "dicarvallho": [],
    "loloto": [{
      "neighbourKey": "didi93",
      "distance": 10
    }, {
      "neighbourKey": "mgeorgieva",
      "distance": 10
    }],
    "lqlql": [{
      "neighbourKey": "didi93",
      "distance": 9
    }, {
      "neighbourKey": "nikup",
      "distance": 9
    }],
    "1079638422047848": [{
      "neighbourKey": "lqlql",
      "distance": 10
    }],
    "10204886624596580": [],
    "Test1": [{
      "neighbourKey": "tester",
      "distance": 10
    }, {
      "neighbourKey": "peshooo",
      "distance": 10
    }, {
      "neighbourKey": "nikup",
      "distance": 10
    }],
    "vanko42": [{
      "neighbourKey": "didi93",
      "distance": 10
    }],
    "tester": [{
      "neighbourKey": "loloto",
      "distance": 10
    }, {
      "neighbourKey": "vanko42",
      "distance": 10
    }],
    "Перванеца": [{
      "neighbourKey": "didi93",
      "distance": 10
    }],
    "admin": [{
      "neighbourKey": "dicarvallho",
      "distance": 10
    }],
    "hacker123": [{
      "neighbourKey": "didi93",
      "distance": 8
    }, {
      "neighbourKey": "vanko42",
      "distance": 9
    }, {
      "neighbourKey": "mgeorgieva",
      "distance": 10
    }],
    "tralala": [],
    "test123": [],
    "nikup": [{
      "neighbourKey": "didi93",
      "distance": 10
    }, {
      "neighbourKey": "peshooo",
      "distance": 10
    }],
    "secretdoor7": [],
    "mgeorgieva": [{
      "neighbourKey": "vanko42",
      "distance": 10
    }, {
      "neighbourKey": "didi93",
      "distance": 10
    }]
  }
};

var inPath = ["gosho", "vanko42", "didi93", "loloto"];