/*
app.controller('CommentController', function($scope, CommentService){
	var commentBody=$scope.inputComment;
	var commentService=CommentSerivce;

	//commentService.setComment(commentBody);
	//commentService.addComment();
	
	$scope.CommentButton=function(){
    commentService.addComment().then(
        function(response) {
            if (response.data) {
            	console.log('addCommetn() success response: ');
            	console.log(response);
            	console.log('data: ');
            	console.log(response.data);
            	commentService.setComment(response.data);
            } else {
            	console.log('comment reponse.data is null');
            }
            
        },
        function(error) {
            console.log('addComment() error response: ');
            console.log(error);
        }
    );}
	
	//service.comment={service.user, say};
	
    
});

app.service('CommentService', function($http, $q){
	let service = this;
	var commenter ={id:'1', following:'', first_name:'a', last_name:'b', email:'ab@ab.com', password:'123', favouriteRecipes:'', createdRecipes:''};
	service.comment={commenter:commenter, commentDate:new Date(), content:'shit'};
	
	service.setComment=function(data){
		service.content=data;
	}
	
	service.getComment=function(){
		return service.comment;
	}
	
	service.addComment=function(){
		  return $http.post('yum/comment', null, service.comment);
	}
});
*/







window.onload = function(){
	loadComment();
	document.getElementById("addPic").addEventListener("click", addComment);
};

function loadComment(){
	var creator ={id:'1', following:'', first_name:'a', last_name:'b', email:'ab@ab.com', password:'123', favouriteRecipes:'', createdRecipes:''};
	var recipeDto ={id:'1', creator:creator, name:'shit', description:'shit', imageUrl:''};
	var tx = JSON.stringify(recipeDto);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			var listComment = JSON.parse(xhr.responseText);
			for(var i=listComment.length-1; i>=0; i--){
				
				var table = document.getElementById("commentList");
				var row = table.insertRow();
				var user = row.insertCell(0);
				var date = row.insertCell(1);
				var content = row.insertCell(2);
				user.innerHTML=listComment[i].user_id;
				date.innerHTML=listComment[i].comment_date;
				content.innerHTML=listComment[i].content;
			}
		}
	}	
	xhr.open("POST", "recipe", true);
	xhr.send(tx);
};

function addComment(){
	var commenter ={id:'1', following:'', first_name:'a', last_name:'b', email:'ab@ab.com', password:'123', favouriteRecipes:'', createdRecipes:''};
	var comment ={commenter:commenter, commentDate:new Date(), content:'shit'};
	var tx = JSON.stringify(comment);
	
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4 && xhr.status == 200){
			
		}
	}
	xhr.open("POST", "create", true);
	xhr.send(tx);
};
