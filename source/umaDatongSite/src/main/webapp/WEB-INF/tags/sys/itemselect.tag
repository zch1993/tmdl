<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="用来自定id，页面出现多个同样的控件时候使用"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="cssClass" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="onlyLable" type="java.lang.Boolean" required="false" description="仅显示lable"%>
<%@ attribute name="onchangeCallback" type="java.lang.String" required="false" description="内容变更时的回调函数"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="tablecolumn" type="java.lang.String" required="false" description="列表头"%>
<%@ attribute name="searchcolumn" type="java.lang.String" required="false" description="搜索字段"%>
<%@ attribute name="multiSelect" type="java.lang.Boolean" required="false" description="多选还是单选"%>
<%@ attribute name="activeParamFun" type="java.lang.String" required="false" description="动态参数JS函数返回"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<div class="input-group">

<c:if test="${onlyLable != '' and onlyLable != null }">
	<label class="${cssClass}">${value}</label>
 </c:if> 
 <c:if test="${onlyLable == null or onlyLable == '' }">
<input id="${id}Id" name="${name}"  type="hidden" value="${value}"/>
    <input id="${id}Name"   name="${labelName}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		class="form-control ${cssClass}" style="${cssStyle}"  />
     <span class="input-group-btn"> <button id="${id}Button" type="button" class="btn btn-primary" ${allowInput?'':'readonly="readonly"'} ><i class="glyphicon glyphicon-search  "></i>&nbsp;
    </button> </span>
 </c:if>   
</div>
                                    

<script type="text/javascript">
  var layerindex${fn:replace(id, ".","")}${extId} = -1;
 
  function clicklayer${fn:replace(id, ".","")}${extId}(){
	  //alert("layui-layer-iframe" + layerindex${id}${extId});//layui-layer-iframe
	  var frameid = "layui-layer-iframe" + layerindex${id}${extId};
	  
	  //alert(window.frames[frameid].document.getElementById("selectedid").value);
	  var ids = window.frames[frameid].document.getElementById("selectedid").value;//layero.find("iframe")[0].contentWindow.getElementById("selectedid");
		var names = window.frames[frameid].document.getElementById("selectedname").value;//layero.find("iframe")[0].contentWindow.getElementById("selectedname");
			
				$("#${id}Id").val(ids);
				$("#${id}Name").val(names);
				layer.close(layerindex${id}${extId});
				
				<c:if test="${onchangeCallback != '' and onchangeCallback != null }"> ${onchangeCallback}; </c:if>
				//alert("${id}Id:" +ids +  " | ${id}Name:" + names);
  }
	$("#${id}Button, #${id}Name").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").hasClass("disabled")){
			return true;
		}
		
		var urlstr = "${url}";
		<c:if test="${activeParamFun != null and activeParamFun != '' }">
		urlstr = "${url}${fn:indexOf(url,'?')==-1?'?':'&'}" + ${activeParamFun};
		</c:if> 
		
		layerindex${fn:replace(id, ".","")}${extId} = layer.open({
				  type: 2,
				  maxmin: true,
				  shadeClose: true,
					title: "选择${title}",
				  area: ['550px', '450px'],
				  fixed: false, //不固定
				  
				  content: "${ctx}/tag/itemselect?url="+encodeURIComponent(urlstr)+"&eleid=${id}&checked=${checked}&multiSelect=${multiSelect}&extId=${extId}&isAll=${isAll}&tablecolumn="+encodeURI(encodeURI("${tablecolumn}"))+"&searchcolumn=${searchcolumn}",
				  success: function(layero, index){
						//var info = '<font color="red" class="pull-left mt10">提示：双击选择图标。</font>';
						//layero.find('.layui-layer-btn').append(info);
					},
					btn: ['<i class="fa fa-close"></i> 选择',
						'<i class="fa fa-eraser"></i> 清除'],
					btn1: function(index, layero){
							 
						
						var ids = $(layero).find("iframe")[0].contentWindow.document.getElementById("selectedid").value;//layero.find("iframe")[0].contentWindow.getElementById("selectedid");
						var names = $(layero).find("iframe")[0].contentWindow.document.getElementById("selectedname").value;//layero.find("iframe")[0].contentWindow.getElementById("selectedname");
							
							 
								$("#${id}Id").val(ids);
								$("#${id}Name").val(names);
								<c:if test="${onchangeCallback != '' and onchangeCallback != null }"> ${onchangeCallback}; </c:if>
						},
						btn2: function(index, layero){
							$("#${id}Id").val("");
							$("#${id}Name").val("");
						}
				
			
		});
		
		
	});
</script>