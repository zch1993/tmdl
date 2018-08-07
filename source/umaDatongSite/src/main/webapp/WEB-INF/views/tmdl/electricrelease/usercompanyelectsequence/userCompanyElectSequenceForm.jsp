<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用电序列管理</title>
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
		<li><a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/">用电序列列表</a></li>
		<li class="active"><a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/form?id=${userCompanyElectSequence.id}">用电序列<shiro:hasPermission name="electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit">${not empty userCompanyElectSequence.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="userCompanyElectSequence" action="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位编号：</label>
			<div class="col-sm-4">
				
			    <label class="control-label">${userCompanyElectSequence.code}</label>
			</div>
	
			<label class="col-sm-2 control-label">用电单位名称：</label>
			<div class="col-sm-4">
				
			   <label class="control-label">${userCompanyElectSequence.name}</label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电量序号<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="powercode" htmlEscape="false" maxlength="18" class="form-control  digits required"/>
			
			</div>
		
			<label class="col-sm-2 control-label">用电序号<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="ueleccode" htmlEscape="false" maxlength="18" class="form-control  digits required"/>
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-6 col-sm-offset-2">
			<shiro:hasPermission name="electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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