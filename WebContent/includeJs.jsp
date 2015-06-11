<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     	
     	<!-- jquery 2.1.4 -->
        <script src="<%=request.getContextPath() %>/admintle/js/jquery-2.1.4.min.js"></script>
        <!-- Bootstrap -->
        <script src="<%=request.getContextPath() %>/admintle/js/bootstrap.min.js" type="text/javascript"></script>      
        
         <!-- jQuery UI 1.10.3 -->
        <script src="<%=request.getContextPath() %>/admintle/js/jquery-ui-1.10.3.min.js" type="text/javascript"></script>
        <!-- Morris.js charts -->
        <script src="<%=request.getContextPath() %>/admintle/js/raphael-min.js"></script>
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/morris/morris.min.js" type="text/javascript"></script>
        <!-- Sparkline -->
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
        <!-- jvectormap -->
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
       <!-- jQuery Knob Chart -->
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/jqueryKnob/jquery.knob.js" type="text/javascript"></script>
       <!-- Bootstrap WYSIHTML5 -->
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js" type="text/javascript"></script>
        <!-- iCheck -->
        <script src="<%=request.getContextPath() %>/admintle/js/plugins/iCheck/icheck.min.js" type="text/javascript"></script>
        
		<script src="<%=request.getContextPath() %>/admintle/js/jquery.bootgrid.min.js" type="text/javascript"></script>
		
		<script src="<%=request.getContextPath() %>/admintle/js/bootstrapValidator.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="<%=request.getContextPath() %>/admintle/js/AdminLTE/app.js" type="text/javascript"></script>
        
        
        <script src="<%=request.getContextPath() %>/admintle/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        
        <script src="<%=request.getContextPath() %>/admintle/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

 		<script src="<%=request.getContextPath() %>/highcharts/js/highcharts.js" type="text/javascript"></script>
 		
 		<script src="<%=request.getContextPath() %>/highcharts/js/modules/exporting.js" type="text/javascript"></script>
        
        
        <script>
        $.fn.formEdit = function(data){
            return this.each(function(){
                var input, name;
                if(data == null){this.reset(); return; }
                for(var i = 0; i < this.length; i++){  
                    input = this.elements[i];
                    name = (input.type == "checkbox")? input.name.replace(/(.+)\[\]$/, "$1") : input.name;
                    if(data[name] == undefined) continue;
                    switch(input.type){
                        case "checkbox":
                            if(data[name] == ""){
                                input.checked = false;
                            }else{
                                //数组查找元素
                                if(data[name].indexOf(input.value) > -1){
                                    input.checked = true;
                                }else{
                                    input.checked = false;
                                }
                            }
                        break;
                        case "radio":
                            if(data[name] == ""){
                                input.checked = false;
                            }else if(input.value == data[name]){
                                input.checked = true;
                            }
                        break;
                        case "button": break;
                        default: input.value = data[name];
                    }
                }
            });
        };
        </script>
          