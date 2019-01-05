<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <div class="jumbotron jumbotron-fluid">
        <div class="container">
            <h1 class="display-4">AADL模型转换演示</h1>
            <p class="lead">本工具可以解析AADL模型，生成相应的C语言代码框架。</p>
        </div>
    </div>

</head>
<body>
<div align="center">
    <form method="post" action="${pageContext.request.contextPath}/upload/single" enctype="multipart/form-data">
        <%--<div class="form-group">--%>
        <%--<label for="fileupload">添加AADL模型文件</label>--%>
        <%--<input type="file" class="form-control" id="fileupload" name="file" aria-describedby="fileHelp"--%>
        <%--placeholder="添加文件">--%>
        <%--<small id="fileHelp" class="form-text text-muted">We'll never share your files with anyone else.</small>--%>

        <%--&lt;%&ndash;<!--进度条-->&ndash;%&gt;--%>
        <%--&lt;%&ndash;<div class="progress">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 45%">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<span class="sr-only">45% Complete</span>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <div class="col-md-4">
            <button type="button" class="btn btn-success" disabled="disabled">上传成功！</button>

            <button type="submit" class="btn btn-info">点击下载文件</button>
        </div>
</div>


<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://cdn.bootcss.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
