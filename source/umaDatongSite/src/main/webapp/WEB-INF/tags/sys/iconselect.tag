<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<i id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a id="${id}Button" href="javascript:" class="btn">选择</a>&nbsp;&nbsp;

<script type="text/javascript">

$("#${id}Button").click(function(){  
	layer.open({
		  type: 2,
		  maxmin: true,
		  shadeClose: true,
			title: '图标选择',
		  area: ['700px', '450px'],
		  fixed: false, //不固定
		  
		  content: "${ctx}/tag/iconselect?value="+$("#${id}").val(),
		  success: function(layero, index){
				//var info = '<font color="red" class="pull-left mt10">提示：双击选择图标。</font>';
				//layero.find('.layui-layer-btn').append(info);
			},
			btn: ['<i class="fa fa-close"></i> 选择',
				'<i class="fa fa-eraser"></i> 清除'],
			btn1: function(index, layero){
					
					 var icon = layero.find("iframe")[0].contentWindow.$("#icon").val();
					 
					
					 //icon = $.trim(icon).substr(5);
	             //   	$("#${id}Icon").attr("class", "icon-"+icon);
	             $("#${id}Icon").attr("class", 'fa fa-' + icon);
		                $("#${id}IconLabel").text(icon);
		                $("#${id}").val(icon);
				},
				btn2: function(index, layero){
					$("#${id}Icon").attr("class", "icon- hide");
		            $("#${id}IconLabel").text("无");
		            $("#${id}").val("");
				}
		});
	
});

</script>
