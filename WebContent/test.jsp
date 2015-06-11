<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

<head>
<%@include file="include.jsp"%>
<meta
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
	name='viewport'>
<!-- bootstrap 3.0.2 -->


<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
        <![endif]-->
</head>
<body>
	<div class="wrapper row-offcanvas row-offcanvas-left">
		<aside class="right-side" style="margin-left: 0px">
			<!-- Content Header (Page header) -->
			<section class="content-header" style="height: 40px">
				<h4>用户报表</h4>
				<ol class="breadcrumb">
					<li class="active">
					
							</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
<button onclick="updateData()">12</button>
				<div class="box">

					<!-- /.box-header -->
					<div class="box-body chart-responsive">
                                    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

                                </div>
					
					<!-- /.box-body -->
				</div>
				
				<!-- /.box -->



			</section>
		</aside>
	</div>

</body>
<%@include file="includeJs.jsp"%>
<script type="text/javascript">



</script>
</html>