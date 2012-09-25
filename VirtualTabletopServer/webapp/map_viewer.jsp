<html>
<head>
<title>Virtual Tabletop - Map Viewer</title>
<script type="text/javascript" src="assets/js/jquery.min.js"></script>
<script type="text/javascript" src="assets/js/jcanvas.min.js"></script>
<script type="text/javascript">
$.getJSON("assets/maps/sample.json",
function(data){
/*
	var m = "version = " + data.version + "<br/>" +
		"width = " + data.width + " height = " + data.height + "<br/>" +
		"orientation = " + data.orientation + "<br/>";

	$("#map_info").html(m);
*/
	
	displayMap(data.width, data.height, data.tilewidth, data.tileheight, data.tilesets, data.layers, true);
});

function displayMap(width, height, tilewidth, tileheight, tilesets, layers, gridlines) {
	var id = 0;
	for(var y=0; y<layers[0].height; y++) {
		for(var x=0; x<layers[0].width; x++, id++) {
			var dx = tilewidth+(x*tilewidth);
			var dy = tileheight+(y*tileheight);
			
			drawTile(dx, dy, layers[0].data[id], tilesets);
		}
	}
	
	if(gridlines) {
		drawGridLines(width, height, tilewidth, tileheight);
	}
}

function drawTile(dx, dy, gid, tilesets) {
	if(gid == 0) return;
	for(var i=0; i<tilesets.length; ++i) {
		var cols = tilesets[i].imagewidth / tilesets[i].tilewidth;
		var rows = tilesets[i].imageheight / tilesets[i].tileheight;
		
		var imgx = ((gid - 1) % cols) * tilesets[i].tilewidth;
		var imgy = Math.floor((gid - 1) / cols) * tilesets[i].tileheight;
		var source = tilesets[i].image.replace("..", "assets");
		
		$("canvas").drawImage({
			source: source,
			x: dx, y: dy,
			sWidth: tilesets[i].tileheight,
			sHeight: tilesets[i].tilewidth,
			sx: imgx, sy: imgy,
			cropFromCenter: false,
			layer: true,
			draggable: true,
			bringToFront: true
		});
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
				height: tileheight,
				layer: true
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