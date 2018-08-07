<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转入转出电量录入管理</title>
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
		<li><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/">转入转出电量录入列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/form?id=${powerMoveMng.id}">转入转出电量录入<shiro:hasPermission name="dataprepare:powermovemng:powerMoveMng:edit">${not empty powerMoveMng.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:powermovemng:powerMoveMng:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="powerMoveMng" action="${ctx}/dataprepare/powermovemng/powerMoveMng/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="unitid" name="unitid.id" value="${powerMoveMng.unitid.id}" labelName="unitid.name" labelValue="${powerMoveMng.unitid.name}"
title="用电用户" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true" 
tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			
			</div>
		
			<label class="col-sm-2 control-label">转入转出日期<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<input name="createtime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate required "
					value="<fmt:formatDate value="${powerMoveMng.createtime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功转入：</label>
			<div class="col-sm-4">
				<form:input path="ygzr" htmlEscape="false" maxlength="255" class="form-control  number"/>
			
			</div>
		
			<label class="col-sm-2 control-label">有功转出：</label>
			<div class="col-sm-4">
				<form:input path="ygzc" htmlEscape="false" maxlength="255" class="form-control  number"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功转入：</label>
			<div class="col-sm-4">
				<form:input path="wgzr" htmlEscape="false" maxlength="255" class="form-control  number"/>
			
			</div>
	
			<label class="col-sm-2 control-label">无功转出：</label>
			<div class="col-sm-4">
				<form:input path="wgzc" htmlEscape="false" maxlength="255" class="form-control  number"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量增减：</label>
			<div class="col-sm-10">
				<form:input path="xlzj" htmlEscape="false" maxlength="255" class="form-control  number"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="dataprepare:powermovemng:powerMoveMng:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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