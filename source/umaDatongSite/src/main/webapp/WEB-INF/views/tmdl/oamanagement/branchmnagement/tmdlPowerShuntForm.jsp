<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分路管理</title>
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
		<li><a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/">分路管理列表</a></li>
		<li class="active"><a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/form?id=${tmdlPowerShunt.id}">分路管理<shiro:hasPermission name="oamanagement:branchmnagement:tmdlPowerShunt:edit">${not empty tmdlPowerShunt.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oamanagement:branchmnagement:tmdlPowerShunt:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlPowerShunt" action="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">开关编号<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="kgbh" htmlEscape="false" maxlength="255" class="form-control required"/>
			
			</div>

			<label class="col-sm-2 control-label">路别名称<font color="red">(*)</font>：</label>
			<div class="col-sm-4">

				<form:input path="name" htmlEscape="false" maxlength="255" class="form-control required"/>


			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">变电站<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="bdzid" name="bdzid.id" value="${tmdlPowerShunt.bdzid.id}" labelName="bdzid.name" labelValue="${tmdlPowerShunt.bdzid.name}"
								title="变电站" url="/paramset/substationmng/tmdlSubstation/listData" cssClass="required" allowClear="true"
								tablecolumn="name:变电站名称,code:变电站代码"  searchcolumn="name" checked="true" multiSelect="false"/>
			
			</div>

			<label class="col-sm-2 control-label">倍率<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="bl" htmlEscape="false" maxlength="18" class="form-control required digits"/>

			</div>
		</div>




		<div class="form-group">
			<label class="col-sm-2 control-label">电量加减<<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="dljj" htmlEscape="false" maxlength="255" class="form-control required "/>
			
			</div>

			<label class="col-sm-2 control-label">需量2加减<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="xl2jj" htmlEscape="false" maxlength="255" class="form-control required"/>

			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">计算倍数<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="jsbs" htmlEscape="false" maxlength="18" class="form-control required digits"/>
			
			</div>

			<label class="col-sm-2 control-label">单位性质<font color="red">(*)</font>：</label>
			<div class="col-sm-4">

				<form:select path="unitType" class="form-control required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('TMDL_COMPNY')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>


			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="unitId" name="unitId.id" value="${tmdlPowerShunt.unitId.id}" labelName="company.name" labelValue="${tmdlPowerShunt.unitId.name}"
								title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
								tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>

			
			</div>
			<label class="col-sm-2 control-label">开关编号1(线路编号)<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="kgbh1" htmlEscape="false" maxlength="255" class="form-control required"/>

			</div>
		</div>

		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="oamanagement:branchmnagement:tmdlPowerShunt:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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