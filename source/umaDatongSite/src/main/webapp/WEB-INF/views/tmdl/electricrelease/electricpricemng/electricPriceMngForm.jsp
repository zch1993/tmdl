<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电价管理管理</title>
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
		<li><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/">电价管理列表</a></li>
		<li class="active"><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/form?id=${electricPriceMng.id}">电价管理<shiro:hasPermission name="electricrelease:electricpricemng:electricPriceMng:edit">${not empty electricPriceMng.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="electricrelease:electricpricemng:electricPriceMng:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="electricPriceMng" action="${ctx}/electricrelease/electricpricemng/electricPriceMng/save?unitidparam=${unitidparam}&pagenoparam=${pagenoparam}&addtimeparam=${addtimeparam}&searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位<font color="red">*</font>：</label>
			<div class="col-sm-4">
			<sys:itemselect id="unitid" name="unitid.id" value="${electricPriceMng.unitid.id}" labelName="unitid.name" labelValue="${electricPriceMng.unitid.name}"
title="用电用户" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true" 
tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
	
			<label class="col-sm-2 control-label">平段用电单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="pingp" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段用电单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="fengp" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
		
			<label class="col-sm-2 control-label">谷段用电单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="gup" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">动力用电单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="dlp" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
		
			<label class="col-sm-2 control-label">照明单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="zmp" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量单价<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="xlp" htmlEscape="false" maxlength="18" class="form-control  number"/>
			
			</div>
		
			<label class="col-sm-2 control-label">外转供电价：</label>
			<div class="col-sm-4">
				<form:input path="wzgp" htmlEscape="false" maxlength="18" class="form-control  number "/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">税率<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="taxrate" htmlEscape="false" maxlength="18" class="form-control  number required"/>
			
			</div>
	
			<label class="col-sm-2 control-label">力率：</label>
			<div class="col-sm-4">
				<form:input path="sl" htmlEscape="false" maxlength="50" class="form-control  number"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平价：</label>
			<div class="col-sm-4">
				<form:input path="flatprice" htmlEscape="false" maxlength="18" class="form-control  number"/>
			
			</div>
		
			<label class="col-sm-2 control-label">价格月度<font color="red">*</font>：</label>
			<div class="col-sm-4">
		<input name="addtime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate required"
					value="<fmt:formatDate value="${electricPriceMng.addtime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
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
			<shiro:hasPermission name="electricrelease:electricpricemng:electricPriceMng:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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