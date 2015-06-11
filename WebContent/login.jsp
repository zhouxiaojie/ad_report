<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>广告报表登录</title>
<%@include file="include.jsp"%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>
<body >
<div class="container" style="margin-top: 120px;">
      <div  class="col-lg-4 col-lg-offset-4 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2">
      <h3 style="text-align: center">报表系统</h3>
       <form class="form" id="loginForm" action="<%=request.getContextPath() %>/login.json" method="post">
        
  <div class="form-group">
    <label for="username" style="font-size: 18px">用户名</label>
    <input type="text" class="form-control" id="username" name="username" placeholder="Username" >
  </div>
  <div class="form-group">
    <label for="password" style="font-size: 18px" >密码</label>
    <input type="password" class="form-control" id="password" name="password" placeholder="Password" >
    
  </div>
  <p style="color: red" id="loginError"></p>
  <button  id="submit" onclick="login()" class="btn btn-lg btn-primary btn-block">登录</button>
</form>

  </div>
    </div> <!-- /container -->

</body>
<%@include file="includeJs.jsp"%>
<script type="text/javascript">

$(function(){
	$('#loginForm').bootstrapValidator({
        fields: {
            username: {
                
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
			password: {
                
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data
        $("#submit").button('loading')
        $.post($form.attr('action'), $form.serialize(), function(data) {
        	if (data.code == 200)
				window.location.href="<%=request.getContextPath()%>/admin/main.jsp"
			else
				$("#loginError").html("用户名或密码错误")
			
			$("#submit").button("reset")
        }, 'json');
    });
})

function login(){
	$("#loginForm").submit();
}

</script>
</html>