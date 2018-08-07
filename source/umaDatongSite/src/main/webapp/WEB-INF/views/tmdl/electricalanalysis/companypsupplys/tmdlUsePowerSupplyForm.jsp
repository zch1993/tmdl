<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>录入供电情况管理</title>
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
		<li><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/">录入供电情况列表</a></li>
		<li class="active"><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/form?id=${tmdlUsePowerSupply.id}">录入供电情况<shiro:hasPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">${not empty tmdlUsePowerSupply.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlUsePowerSupply" action="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="sysid" name="sysid.id" value="${tmdlUsePowerSupply.sysid.id}" labelName="sysid.name" labelValue="${tmdlUsePowerSupply.sysid.name}"
								title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
								tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>

			
			</div>

			<label class="col-sm-2 control-label">日期<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control required Wdate  "
					   value="<fmt:formatDate value="${tmdlUsePowerSupply.time}" pattern="yyyy-MM"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>

			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">收入电量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="srdl" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>


			<label class="col-sm-2 control-label">局外转供电量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="loser" htmlEscape="false" maxlength="10" class="form-control required  digits"/>

			</div>
		</div>


		<div class="form-group">


			<label class="col-sm-2 control-label">局内转供电量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="council" htmlEscape="false" maxlength="10" class="form-control required digits"/>
			
			</div>

			<label class="col-sm-2 control-label">原煤生产<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="coal" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>
		</div>
		<div class="form-group">


			<label class="col-sm-2 control-label">基本建设<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="build" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">非原煤生产<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="notcoal" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

		</div>

		<div class="form-group">

			<label class="col-sm-2 control-label">生活用电<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="llife" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">原煤总产量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="allcoal" htmlEscape="false" maxlength="10" class="form-control  required digits"/>

			</div>
		</div>


		<div class="form-group">


			<label class="col-sm-2 control-label">矿井加漏天产量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="allseep" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">最大负荷<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="maxload" htmlEscape="false" maxlength="10" class="form-control required  digits"/>

			</div>
		</div>

		<div class="form-group">


			<label class="col-sm-2 control-label">平均负荷<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="meanload" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">负荷率<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="loadrate" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>
		</div>

		<div class="form-group">


			<label class="col-sm-2 control-label">功率因数<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="pf" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">利率奖罚<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="lljf" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>
		</div>
		<div class="form-group">


			<label class="col-sm-2 control-label">无功电量：</label>
			<div class="col-sm-4">
				<form:input path="wg" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

			<label class="col-sm-2 control-label">线变损：</label>
			<div class="col-sm-4">
				<form:input path="xbs" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>

		</div>


		<div class="form-group">
		<label class="col-sm-2 control-label">公式：</label>
		<div class="col-sm-4" style="color: red">
			1.转供电量和局内电量:	转供+局内=收入电量—实际
			2. 本企业综合用电量实际用电量:收入电量-转供-局内
			3.本企业综合用电量	:基本建设 + 生活 + 非生产 + 非原煤 + 原煤 = 实际
			4.平均负荷:收入电量/24小时/天数
		</div>
		</div>


		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">
				<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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