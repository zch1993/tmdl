<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>读取失败管理</title>
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
		<li><a href="${ctx}/dataprepare/readfail/readfail/">读取失败列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/readfail/readfail/form?id=${readfail.id}">读取失败<shiro:hasPermission name="dataprepare:readfail:readfail:edit">${not empty readfail.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:readfail:readfail:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="readfail" action="${ctx}/dataprepare/readfail/readfail/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">分路ID<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="shuntId.id" htmlEscape="false" maxlength="255" class="form-control required"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="wattfulgross" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="peaksegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="grainsegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平段总量<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="flatsegment" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-10">
				<form:input path="pluswatt" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-10">
				<form:input path="resewatt" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-10">
				<form:input path="idleroll" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反无功总量：</label>
			<div class="col-sm-10">
				<form:input path="antiReactivePower" htmlEscape="false" maxlength="10" class="form-control  digits"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-10">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate "
					value="<fmt:formatDate value="${readfail.time}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			
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
			<shiro:hasPermission name="dataprepare:readfail:readfail:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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