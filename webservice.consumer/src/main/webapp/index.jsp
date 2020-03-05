<html>

<script src="https://code.jquery.com/jquery-1.10.2.js"
	type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		//alert("Hi");
		/*$.ajax({
			url:"ServiceConstroller",
			method:"GET",
			success:function(response){
				console.log("hi");
				alert(response);
				}
			})*/
		 	getData();
		 	$("#btnSave").click(function(){				
				saveData();
			})	 
	})	
	function saveData(){
		var fullName=document.getElementById("fullName").value;
		var email=document.getElementById("email").value;
		var password=document.getElementById("password").value;
		$.ajax({
			url:"ServiceConstroller",
			method:"POST",
			//headers: {"Content-Type": "application/json"},
			data:{
				fullName:fullName,
				email:email,
				password:password
			},			 
			success:function(data){				
					console.log(data);
					alert(data);			
					getData();
				},
			error:function(error){				
					console.log(error);			
					getData();
				}
		})
		
	}
	function getData()
	{
		$.get("http://localhost:8080/webservice/rest/users",{},			 
				function(data){				
					console.log(data);				
					//alert(data[0].fullName);
					var str="";
					for(i=0;i<data.length;i++){
						str+="<tr>"
							+"<td>"+data[i].id+"</td>"
							+"<td>"+data[i].fullName+"</td>"
							+"<td>"+data[i].email+"</td>"
							+"</tr>";
					}		
					document.getElementById("dataTable").innerHTML=str;	
				})
	}
</script>
<body>

<h2>Hello World!</h2>
<input type="text" id="fullName"/><br>
<input type="text" id="email"/><br>
<input type="text" id="password"/><br>
<button id="btnSave">Save</button>
<table id="dataTable">
</table>
</body>
</html>
