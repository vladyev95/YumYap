window.onload = function(){
	loadImg();
};

function loadImg(){
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var dto = JSON.parse(xhr.responseText);
			var imgLocation = dto.imgs[1].link;
			console.log(imgLocation);
			var img = document.createElement("img");
			img.src = imgLocation;
			var src = document.getElementById("here");
			src.appendChild(img);
		}
	}
	xhr.open("GET", "load", true);
	xhr.send();
};