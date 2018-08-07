<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月度分路表底录入管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				var donemsg = "${message}";
				if(donemsg != ""){
					toastr.info(donemsg);
				}
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					toastr.info('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					toastr.info("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/">月度分路表底录入列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/form?id=${powerElectricQuantityInput.id}">月度分路表底录入<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit">${not empty powerElectricQuantityInput.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="powerElectricQuantityInput" action="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">分路<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				
			<sys:itemselect id="shuntId" name="shuntId.id" value="${powerElectricQuantityInput.shuntId.id}" labelName="shuntId.name" labelValue="${powerElectricQuantityInput.shuntId.name}"
title="分路" url="/oamanagement/branchmnagement/tmdlPowerShunt/listData" cssClass="required" allowClear="true" 
tablecolumn="name:路别名称,kgbh:开关编号"  searchcolumn="kgbh" checked="true" multiSelect="false"/>
			</div>
		
			<label class="col-sm-2 control-label">月度<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate required"
					value="<fmt:formatDate value="${powerElectricQuantityInput.time}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="wattfulgross" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
	
			<label class="col-sm-2 control-label">峰段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="peaksegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="grainsegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
	
			<label class="col-sm-2 control-label">平段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="flatsegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-4">
				<form:input path="pluswatt" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		
			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-4">
				<form:input path="resewatt" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-4">
				<form:input path="idleroll" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		
			<label class="col-sm-2 control-label">反无功总量：</label>
			<div class="col-sm-4">
				<form:input path="antiReactivePower" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-10">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</div>
	</form:form>
	</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>