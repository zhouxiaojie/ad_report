<%@page import="com.ocean.ad.report.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<% User user=(User)request.getSession().getAttribute("user"); %>
<!DOCTYPE html>
<html>
<head>
<title>主页面</title>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->
<%@include file="../include.jsp"%>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>
<body class="skin-blue">
	<!-- header logo: style can be found in header.less -->
	<header class="header">
		<a href="<%=request.getContextPath() %>/admin/main.jsp" class="logo"> <!-- Add the class icon to your logo image or logo icon to add the margining -->
			ocean
		</a>
		<!-- Header Navbar: style can be found in header.less -->
		<nav class="navbar navbar-static-top" role="navigation">
			<!-- Sidebar toggle button-->
			<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas"
				role="button"> <span class="sr-only">Toggle navigation</span> <span
				class="icon-bar"></span> <span class="icon-bar"></span> <span
				class="icon-bar"></span>
			</a>
			<div class="navbar-right">
				<ul class="nav navbar-nav">

					<li class="dropdown user user-menu"><a href="#"
						class="dropdown-toggle" data-toggle="dropdown"> <i
							class="glyphicon glyphicon-user"></i> <span><%=user.getUsername() %> <i
								class="caret"></i></span>
					</a>
						<ul class="dropdown-menu">
							
							<!-- Menu Footer-->
							<li class="user-footer">
								<div class="pull-left">
									<a href="#" class="btn btn-primary btn-flat" data-toggle="modal" data-target="#passModal">密码修改</a>
								</div>
								<div class="pull-right">
									<a href="<%=request.getContextPath() %>/loginout.do" class="btn btn-default btn-flat">登出</a>
								</div>
							</li>
						</ul></li>
				</ul>
			</div>
		</nav>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<aside class="left-side sidebar-offcanvas">
			<section class="sidebar">

				<!-- /.search form -->
				<!-- sidebar menu: : style can be found in sidebar.less -->
				<ul class="sidebar-menu">
					<li class="active"><a href="#" menu="<%=request.getContextPath() %>/admin/admin.jsp" onclick="addMenu(this)" ><span>账户管理</span>
					</a></li>
					<li class="treeview"><a href="#"> <span>报表</span>
					</a>
						<ul class="treeview-menu">
							<li><a href="#" menu="user_report.jsp" onclick="addMenu(this)" >用户报表</a></li>
							<li><a href="#" menu="user_report_on_day.jsp" onclick="addMenu(this)" >用户趋势图</a></li>
							<li><a href="#" menu="user_report_donut.jsp" onclick="addMenu(this)" >用户圆环图</a></li>
							<li><a href="#" menu="req_report_line.jsp" onclick="addMenu(this)" >广告展示数趋势图</a></li>
						</ul></li>

				</ul>
			</section>
		</aside>
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<iframe id="mainFrame" src="<%=request.getContextPath()%>/admin/admin.jsp" width="100%" height="1400px"  frameborder="no" border="0" marginwidth="0" marginheight="0" ></iframe>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->
	</div>
<div class="modal fade" id="passModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" style="width:450px">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="passLabel">修改密码</h4>
				</div>
				<div class="modal-body" style="padding:10px">
					<form class="form-horizontal" id="passForm" role="form" method="post" action="<%=request.getContextPath()%>/admin/user/updatepass.json">
						<input type="text" hidden="true" name="id" >
						<div class="form-group">
							<label for="oldpass" class="col-sm-3 control-label">原密码</label>
							
							<div class="col-sm-8">
								<input type="text" class="form-control" name="oldpass" />
								<p style="color: red" id="passError"></p>
							</div>
						</div>
						<div class="form-group">
							<label for="newpass" class="col-sm-3 control-label">新密码</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="newpass" />
							</div>
						</div>
						<div class="form-group">
							<label for="confirmpass" class="col-sm-3 control-label">验证新密码</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="confirmpass" />				
						
							</div>
						</div>
						<input type="reset" style="display: none;" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button id="passBtn" type="button" class="btn btn-primary"
						onclick="passFormCommit()">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<%@include file="../includeJs.jsp" %>
<script type="text/javascript">
function addMenu(obj){
	$("#mainFrame").attr("src",$(obj).attr("menu"))
}
$(function(){
	$('#passModal').on('hide.bs.modal', function () {
		$('#passForm input[type=reset]').trigger("click");
		$('#passForm').data('bootstrapValidator').resetForm();
		$("#passError").html("");
	})
	$('#passForm').bootstrapValidator({
        fields: {
            oldpass: {             
                validators: {
                    notEmpty: {
                        message: '请输入原密码'
                    }
                }
            },
            newpass: {             
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    },
                    identical: {
                        field: 'confirmpass',
                        message: '两次密码没有一致'
                    }
                }
            },
            confirmpass: {             
                validators: {
                    notEmpty: {
                        message: '请输入新密码'
                    },
                    identical: {
                        field: 'newpass',
                        message: '两次密码没有一致'
                    }
                }
            }
        }
    })
    .on('success.form.bv', function(e) {
        // Prevent form submission
        e.preventDefault();

        // Get the form instance
        var $form = $(e.target);

        // Get the BootstrapValidator instance
        var bv = $form.data('bootstrapValidator');

        // Use Ajax to submit form data
        $("#passBtn").button('loading')
        $.post($form.attr('action'), $form.serialize(), function(data) {
        	if (data.code == 200)
				$("#passModal").modal("hide");
        	else if(data.code==301)
        		$("#passError").html("原密码错误");
			$("#passBtn").button("reset")
        }, 'json');
    });
})
function passFormCommit(){
		$("#passForm").submit();
		
	}
</script>
</html>