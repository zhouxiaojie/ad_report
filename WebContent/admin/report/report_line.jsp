<%@page import="org.apache.commons.lang.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<% String msg = (String)request.getAttribute("message");
   String today = (String)request.getAttribute("today");
%>
<head>
<%@include file="../../include.jsp"%>
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
			<section class="content-header" >
				<h5>广告实时趋势图</h5>
				<ol class="breadcrumb">
					<li class="active">
						<select class="form-control" id="event_select" onchange="load(this);">
							<option value="reqad_s">请求成功</option>
							<option value="reqad_f">请求失败</option>
							<option value="show_s">展示成功</option>							
							<option value="show_f">展示失败</option>													
							<option value="init_s">初始化成功</option>																				
							<option value="init_f">初始化失败</option>
						</select>
					</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="box">

					<!-- /.box-header -->
					<div class="box-body chart-responsive">
                                    <div class="chart" id="container" style="height:300px"></div>
                                    <div class="chart" id="dyn_container" style="height:260px"></div>
                                </div>
					
					<!-- /.box-body -->
				</div>
				<!-- /.box -->



			</section>
		</aside>
	</div>

</body>
<%@include file="../../includeJs.jsp"%>
<script type="text/javascript">
$(function () {
	
	load(document.getElementById("event_select"));
	
	                                                        
                         
   
});

var si;
var dyn_si;
function load(obj){
	var event = $(obj).val();
	var interval=15;
	var point=60 *1000;
	if(si)
		clearInterval(si);
	if(dyn_si)
		clearInterval(dyn_si);
		
	$.post('<%=request.getContextPath()%>/admin/report/eventonmin.json',{'event':event,'interval':interval},function(json){
		if(json){
			var c = [];
			var y =[] ;
			var d = [];
			var dx = [];
			for(var i=0;i<json.c.length;i++){
				c.push(json.c[i].num);
			}
			for(var i=0;i<json.y.length;i++){
				y.push(json.y[i].num);
			}
			for(var i=0;i<json.d.length;i++){
				d.push(json.d[i].num);
				dx.push(json.d[i].time.substr(11,5));
			}
			
			var currMin=json.c[json.c.length-1].time;
			
			var dcurrMin;
			if(!json.d||json.d.length>0)
				dcurrMin=json.d[json.d.length-1].time;
			
			 $('#container').highcharts({
				 	title: {                                                                
			            text: '对比图'                                            
			        },
			        credits: {
		            	enabled:false
		        	},
		        	legend: {                                                               
			            enabled: true                                                      
			        },                                                                      
			        exporting: {                                                            
			            enabled: false                                                      
			        },  
			        chart:{
			        	type: 'spline',
			        	events: {
			                    load: function () {
			         				var series = this.series[1];
			                        si=setInterval(function () {
			                        	$.post('<%=request.getContextPath()%>/admin/report/eventincronmin.json',{'currMin':currMin,'event':event,'interval':interval},function(json){
	
			                        		if(json){
			                        			for(var i=0;i<json.length;i++){
			                        				 series.addPoint(json[i].num, true, false);
			                        			}
			                        			if(json.length>0)
			                        				currMin=json[json.length-1].time;
			                        		}
			                        	},'json')
			                            
			                           
			                        }, 1000*60);
			                    }
			                }
			        },
			        yAxis:{
			        	title: {
			                text: '数量'
			            }
			        },
			        xAxis: {
			            type: 'datetime',
			            dateTimeLabelFormats: {
			                day: '%H %M'
			            }
			        },
			        plotOptions: {
			            spline: {
			                
			                marker: {
			                    enabled: false
			                }
			            }
			        },
			        series: [{
			        	name:'昨天数据',
			            data: y,
			            pointInterval: interval * 60 * 1000 // one day
			        },
			        {	
			        	name:'今天数据',
			            data:c,
			            pointInterval: interval * 60 * 1000 // one day
			        }]
			    });
			 
			 $('#dyn_container').highcharts({                                                
			        chart: {                                                                
			            type: 'spline',                                                     
			            events: {                                                           
			                load: function() {                                              
			                                                                                
			                    // set up the updating of the chart each second    
			                	var series = this.series[0];
			                	dyn_si=setInterval(function () {
		                        	$.post('<%=request.getContextPath()%>/admin/report/eventincronmin.json',{'currMin':dcurrMin,'event':event,'interval':1},function(json){

		                        		if(json){
		                        			for(var i=0;i<json.length;i++){
		                        				 series.addPoint([json[i].time.substr(11,5),json[i].num], true, true);
		                        			}
		                        			if(json.length>0)
		                        				dcurrMin=json[json.length-1].time;
		                        		}
		                        	},'json')
		                            
		                           
		                        }, 1000*30);
			                                                                    
			                }                                                               
			            }                                                                   
			        },
			        credits: {
			        	enabled:false
			    	},
			    	plotOptions: {
			            spline: {
			                
			                marker: {
			                    enabled: false
			                }
			            }
			        },
			        title: {                                                                
			            text: '一小时动态图'                                            
			        },
			        xAxis: {
			            categories: dx,
			            tickPixelInterval:600
			        },
			        yAxis: {                                                                
			            title: {                                                            
			                text: '数量'                                                   
			            }                                                                
			        },                                                                                                                                           
			        legend: {                                                               
			            enabled: false                                                      
			        },                                                                      
			        exporting: {                                                            
			            enabled: false                                                      
			        },                                                                      
			        series: [{                                                              
			            name: '一小时动态数据',      
			            data:d
			        }]                                                                      
			    });
			 
		}
	},'json')                                                                       
                        
}


</script>
</html>