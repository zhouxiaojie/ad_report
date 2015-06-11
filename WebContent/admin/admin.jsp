<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>

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
<aside class="right-side" style="margin-left:0px">
<!-- Content Header (Page header) -->
			<section class="content-header" style="height:40px">
				<h4>账户管理</h4>
				<ol class="breadcrumb">
					<li class="active"><input type="text" id="queryname"
						style="width: 200px; height: 35px" placeholder="用户名">
						<button class="btn btn-primary" onclick="loadData()">查询</button>
						<button class="btn btn-primary" data-toggle="modal"
							data-target="#addModal">新增</button></li>
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
									<th data-column-id="id" data-type="numeric">ID</th>
									<th data-column-id="username">用户名</th>
									<th data-column-id="remark">备注</th>
									<th data-column-id="createTime">创建时间</th>
									<th data-column-id="commands" data-formatter="commands" >操作</th>
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
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog"
		aria-hidden="true">
		<div class="modal-dialog" style="width:450px">
			<div class="modal-content" >
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="addLabel">新增用户</h4>
				</div>
				<div class="modal-body" style="padding:10px">
					<form class="form-horizontal" id="addForm" role="form" method="post" action="<%=request.getContextPath()%>/admin/user/addupdateuser.json">
						<input type="text" hidden="true" name="id" >
						<div class="form-group">
							<label for="username" class="col-sm-3 control-label">用户名</label>
							<div class="col-sm-8">
								<input type="text" id="username" class="form-control" name="username" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">备注</label>
							<div class="col-sm-8">
								<input type="text" class="form-control" name="remark" />
							</div>
						</div>

						<input type="reset" style="display: none;" />
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button id="addBtn" type="button" class="btn btn-primary"
						onclick="addFormCommit()">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
</body>
<%@include file="../includeJs.jsp" %>
<script type="text/javascript">
var addFormVali;
	$(function() {
		$('#addModal').on('hide.bs.modal', function () {
			$('#addForm input[type=reset]').trigger("click");
			$("#addLabel").html("新增用户");	
			$('#addForm').data('bootstrapValidator').resetForm();
		})
		
		loadData();
		formInit();
	});
	
	function formInit(){
		$('#addForm').bootstrapValidator({
            fields: {
                username: {
                    
                    validators: {
                        notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {
                            max: 20,
                            message: '用户名长度不能超过20'
                        },
                        remote: {
                            url: "<%=request.getContextPath()%>/admin/user/valiuser.do",
                            type: "post",
                            async: true,
                            delay:500,
                            data:
                                    {
                                        username: function(validator)
                                        {
                                            return $('#addForm :input[name="username"]').val();

                                        },
                                        id: function(validator)
                                        {
                                            return $('#addForm :input[name="id"]').val();

                                        }
                                    },
                            message: '用户名已存在'
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
            $("#addBtn").button('loading')
            $.post($form.attr('action'), $form.serialize(), function(data) {
            	if (data.code == 200)
					$("#addModal").modal("hide");
				
				$("#addBtn").button("reset")
            }, 'json');
        });
	
	}
	
	function loadData(){
		var url = "<%=request.getContextPath()%>/admin/user/selectuser.json?username=";
		url = url+$("#queryname").val();
		$("#grid-data").bootgrid("destroy")
		$("#grid-data").bootgrid(
				{	
					rowCount:[20],
					ajaxSettings: {
				        method: "GET",
				        cache: true
				    },
					navigation : 2,
					ajax : true,			
					url : url,
					formatters: {
				        "commands": function(column, row)
				        {
				            return "<button type=\"button\" class=\"btn btn-xs btn-default command-edit\" data-row-id=\"" + row.id + "\"><span class=\"fa fa-pencil\"></span></button> " 
				        }
				    }
				}).on("loaded.rs.jquery.bootgrid", function()
						{
				    /* Executes after data is loaded and rendered */
				    $("#grid-data").find(".command-edit").on("click", function(e)
				    {	
				    	
				    $("#addLabel").html("修改用户");
				    $("#addModal").modal("show");
				    	$.get("<%=request.getContextPath()%>/admin/user/selectuserbyid.json",{id:$(this).data("row-id")},function(json){
				    		if(json){
				    			
						    	$("#addForm").formEdit(json);
								
				    		}
				    	},"json")
				    })
				});
		
	}
	
	function addFormCommit(){
		$("#addForm").submit();
		
	}
</script>
</html>