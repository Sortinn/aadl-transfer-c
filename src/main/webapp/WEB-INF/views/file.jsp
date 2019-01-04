<%--
  Created by IntelliJ IDEA.
  User: sortinn
  Date: 2019/1/4
  Time: 4:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload File</title>
</head>
<body>
<div>
    <form method="post" action="${pageContext.request.contextPath}/upload/single" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit"/>
    </form>
</div>
</body>
</html>
