<%-- 
    Document   : index
    Created on : Aug 15, 2013, 5:48:51 PM
    Author     : vcnduong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
        <title>JSP Page</title>
        
        <script type="text/javascript">
            $(document).ready(function(){
               $('#btnGetUser').click(function(){
                   $.ajax({
                        type: "POST",
                        url: "service/Authentication",
                        data: "username=vcnduong&password=sddfgdfgdfg",
                        dataType: "json",
                        success: function(data){
                            alert(data.status + " " + data.description);
                        },
                        error: function(){
                            alert('error');
                        }
                    });
               })
            });
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
