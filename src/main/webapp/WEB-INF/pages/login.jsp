<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

    <!-- Bootstrap Core CSS -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="assets/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="assets/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body onload="getCookie()">
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">请登录</h3>
                    </div>
                    <div class="panel-body">

                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" name="username" id="username" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" name="password" id="password" type="password" value="">
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input name="rememberMe" id="rememberMe" type="checkbox">记住我
                                    </label>
                                </div>
                                <!-- Change this to a button or input when using this as a form -->
                                    <button class="btn btn-lg btn-success btn-block" onclick="getlogin()">登录</button>
                            </fieldset>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- jQuery -->
    <script src="assets/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="assets/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="assets/dist/js/sb-admin-2.js"></script>
    <script src="assets/dist/js/jquery.cookie.js"></script>
    <script src="assets/dist/js/jquery.base64.js"></script>

    <script type="text/javascript">
        $("#rememberMe").click(function () {
            var username = $("#username").val();
            var password = $("#password").val();
            var checked = $("#rememberMe:checked");
            if(checked){
                $.cookie("ims_login_username",username);
                $.cookie("ims_login_password",$.base64.encode(password));
            }else{
                $.removeCookie("ims_login_username");
                $.removeCookie("ims_login_password");
            }
        });

        function getCookie(){
            var username = $.cookie("ims_login_username");
            var password = $.cookie("ims_login_password");
            if(password){
                $("#rememberMe").attr("checked","true");
            }
            if(username){
                $("#username").val(username);
            }
            if(password){
                $("#password").val($.base64.decode(password));
            }
        }




        function getlogin(){

            alert($("#username").val());
            alert($("#password").val());

            $.ajax({
                type: "post",
                url: "login",
                data:{
                    "username":$("#username").val(),
                    "password":$("#password").val()
                },
                dataType:"json",
                xhrFields:{withCredentials:true},
                beforeSend: function(XMLHttpRequest){
                },
                //请求成功回调
                success: function(data){
                    if(data.data.success){

                        // localStorage.setItem("token",data.data.token);
                        $.cookie("token",data.data.token,{ expires: 7 });// 创建一个cookie并设置有效时间为7天

                        window.location.href ="main";

                    }

                },
                complete: function(XMLHttpRequest, textStatus){

                },
                error: function(){
                    alert("请求网络失败！。。。。。。");
                }
            });
        }



    </script>


</body>

</html>
