<html>
<head>
<title>Virtual Tabletop - Map Viewer</title>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jcanvas.min.js"></script>
<script type="text/javascript">
$.getJSON("assets/maps/sample.json",
function(data){
	var m = "version = " + data.version + "<br/>" +
		"width = " + data.width + " height = " + data.height + "<br/>" +
		"orientation = " + data.orientation + "<br/>";

	$("#map_info").html(m);
	
	displayMap(data.width, data.height, data.tilewidth, data.tileheight, data.tilesets, data.layers, true);
});

function displayMap(width, height, tilewidth, tileheight, tilesets, layers, gridlines) {

	if(gridlines) {
		drawGridLines(width, height, tilewidth, tileheight);
	}
}

function drawGridLines(width, height, tilewidth, tileheight) {
	for(var x=0; x<width; x++) {
		for(var y=0; y<height; y++) {
			$("canvas").drawRect({
				strokeStyle: "#000",
				strokeWidth: 1,
				x: tilewidth+(x*tilewidth), y: tileheight+(y*tileheight),
				width: tilewidth,
				height: tileheight
			});
		}
	}
}
</script>
</head>
<body>
<noscript>
    Please enable JavaScript to use the Map Viewer!
</noscript>

<div id="map_info"></div> 
<div id="canvas_container">
<canvas width="1000" height="1000"></canvas>
</div>

</body>
</html>