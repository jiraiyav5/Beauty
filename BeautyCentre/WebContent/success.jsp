<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
success!!!

     <form id="form1" action="businessRegiste" method="post"  enctype="multipart/form-data" >
   <input type="text" name="user.username" size="30" maxlength="20" value="zhangyili">
   <input type="text" name="user.realname" size="30" maxlength="20" value="sun">
   <input type="text" name="user.password" size="30" maxlength="20" value="111">
   <input type="text" name="user.sex" size="30" maxlength="20" value=1>
   <input type="text" name="user.phone" size="30" maxlength="20" value="18014822689">
   <input type="text" name="user.email" size="30" maxlength="20" value="jiraiyav5@126.com">
  

    <button id="createPeriadBtn" type="submit" class="btn btn-default">确定 </button>
    </div>
    </form>
</body>
 <script type="text/javascript">        
 function addMore()
 {
     var td = document.getElementById("more");
     
    var br = document.createElement("br");
    var input = document.createElement("input");
    var button = document.createElement("input");
    
    input.type = "file";
    input.name = "file";
    
     button.type = "button";
    button.value = "Remove";
   
    button.onclick = function()
    {
         td.removeChild(br);
        td.removeChild(input);
        td.removeChild(button);
    }
     td.appendChild(br);
     td.appendChild(input);
     td.appendChild(button);
 }
</script>
</html>