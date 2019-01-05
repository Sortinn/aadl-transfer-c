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
    <form method="get" action="/transfer-c/show/download">
        <div class="col-md-4">
            <button id="success" type="button" class="btn btn-success" style="margin-top: 50px">上传成功！</button>
            <input onkeyup="value=value.replace(/[\W]/g,'') "
                   onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
                   onkeydown="if(event.keyCode==13)event.keyCode=9" type="text" class="form-control" id="filedownload" name="filename" style="margin-top: 30px" placeholder="为转换后的文件起个名字吧"/>
            <%--<div class="progress" style="margin-top: 30px">--%>
                <%--<div class="progress-bar" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: 50%;">--%>
                    <%--50%--%>
                <%--</div>--%>
            <%--</div>--%>
            <button id="downsuccess" type="submit" class="btn btn-info" style="margin-top: 30px">点击下载文件</button>
        </div>
    </form>
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
