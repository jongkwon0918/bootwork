<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp화면</title>
</head>
<body>
	<h2>BOOT jsp 화면</h2>
	<button id="btn">demo저장</button>
	<script>
		btn.addEventListener("click", async e=>{
				const demo={
						"devName":"박종권",
						"devAge":7,
						"devGender":'M',
						"devLang":["C","JAVA"],
						"devEmail":"whdrnjs@naver"
				}
				const response=await fetch("/demo",{
					method:"post",
					headers:{
						"Content-Type":"application/json",
					},
					body:JSON.stringify(demo)
				})
				if(response.ok){
					const data=await response.json();
					console.log(data);
				}
		})
	</script>
</body>
</html>