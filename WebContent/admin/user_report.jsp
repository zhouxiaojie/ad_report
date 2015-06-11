<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<% String msg = (String)request.getAttribute("message");
   String today = (String)request.getAttribute("today");
%>
<head>
<%@include file="../include.jsp"%>
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
					<form class="form-inline" >
					<div class="form-group">
					开始时间
					</div>
					<div class="form-group">
						<div class="input-group date form_datetime  "
									data-date="<%=today %>" data-date-format="yyyy-mm-dd" style="width:200px">
									<input class="form-control" size="16"  type="text" id="start"
										value="<%=today%>" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-th"></span></span>
								</div>
					</div>
					<div class="form-group">
					结束时间
					</div>
					<div class="form-group">
						<div class="input-group date form_datetime "
									data-date="<%=today%>" data-date-format="yyyy-mm-dd" style="width:200px">
									<input class="form-control" size="16"  type="text" id="end"
										value="<%=today%>" readonly> <span
										class="input-group-addon"><span
										class="glyphicon glyphicon-th"></span></span>
								</div>
					</div>
						<button type="button" class="btn btn-primary" onclick="loadData()">查询</button>
						</form>
							</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="box">

					<!-- /.box-header -->

					<div class="box-body table-responsive">
						<table id="grid-data"
							class="table table-condensed table-hover table-striped">
							<thead>
								<tr>
									<th data-column-id="userNum">用户数</th>
									<th data-column-id="event">事件类型</th>
								</tr>
							</thead>
						</table>
					</div>
					<!-- /.box-body -->
				</div>
				<!-- /.box -->



			</section>
		</aside>
	</div>

</body>
<%@include file="../includeJs.jsp"%>
<script type="text/javascript">
	$(function() {
		
		loadData();
	
		$('.form_datetime').datetimepicker({
	        language:  'zh-CN',
	        todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			minView: 'month'
	    });
	});
	
	function loadData(){
		var url = "<%=request.getContextPath()%>/admin/report/selectusereport.json";
		$("#grid-data").bootgrid("destroy")
		$("#grid-data").bootgrid(
				{	
					ajaxSettings: {
				        method: "POST",
				        cache: false
				    },
				    post:function(){
				    	param={};
				    	param.start=$("#start").val();
				    	param.end=$("#end").val();
				    	return param;
				    },
					navigation : 0,
					ajax : true,			
					url : url,
					
				})

	}

</script>
</html>