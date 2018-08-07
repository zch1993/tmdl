<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局电量管理</title>
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

		function loadrate(){
            var maxload=$("#maxload").val();
            var meanload=$("#meanload").val();
            if(maxload!='' && meanload!=''){

				var loadrate=maxload/meanload;
                $("#loadrate").val(loadrate)
			}
		}

	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/">全局电量列表</a></li>
		<li class="active"><a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/form?id=${tmdlUsePowerGlobal.id}">全局电量<shiro:hasPermission name="electricalanalysis:globalpower:tmdlUsePowerGlobal:edit">${not empty tmdlUsePowerGlobal.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="electricalanalysis:globalpower:tmdlUsePowerGlobal:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlUsePowerGlobal" action="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电年月<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate required"
					value="<fmt:formatDate value="${tmdlUsePowerGlobal.time}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			
			</div>

			<label class="col-sm-2 control-label">全局收入电量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="globalElectricity" htmlEscape="false" maxlength="10" class="form-control required digits"/>

			</div>
		</div>




		<div class="form-group">
			<label class="col-sm-2 control-label">最大负荷<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="maxload" htmlEscape="false" maxlength="10" class="form-control required digits" id="maxload"/>
			
			</div>

			<label class="col-sm-2 control-label">平均负荷<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="meanload" htmlEscape="false" maxlength="10" class="form-control required digits" id="meanload"/>

			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">负荷率<font color="red">(*)</font>：</label>
			<div class="col-sm-10">
				<form:input path="loadrate" htmlEscape="false" maxlength="10" class="form-control required digits" id="loadrate" onfocus="loadrate()"/>
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>

		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="electricalanalysis:globalpower:tmdlUsePowerGlobal:edit">
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