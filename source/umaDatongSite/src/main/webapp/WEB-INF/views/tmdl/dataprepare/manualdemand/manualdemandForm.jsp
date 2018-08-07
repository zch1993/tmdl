<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>手工录入需量管理</title>
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
		<li><a href="${ctx}/dataprepare/manualdemand/manualdemand/">手工录入需量列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/manualdemand/manualdemand/form?id=${manualdemand.id}">手工录入需量<shiro:hasPermission name="dataprepare:manualdemand:manualdemand:edit">${not empty manualdemand.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:manualdemand:manualdemand:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="manualdemand" action="${ctx}/dataprepare/manualdemand/manualdemand/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>


		<div class="form-group">
			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-4">
				<fmt:formatDate value="${manualdemand.time}" pattern="yyyy-MM"/>

			</div>

			<label class="col-sm-2 control-label">需量：</label>
			<div class="col-sm-4">
				<form:input path="demand" htmlEscape="false" maxlength="10" class="form-control  digits"/>

			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">分路名称：</label>
			<div class="col-sm-4">
					${manualdemand.shuntId.name}
			
			</div>

			<label class="col-sm-2 control-label">开关编号：</label>
			<div class="col-sm-4">
					${manualdemand.shuntId.kgbh}

			</div>
		</div>



		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量：</label>
			<div class="col-sm-4">
					${manualdemand.wattfulgross}

			</div>

			<label class="col-sm-2 control-label">峰段总量：</label>
			<div class="col-sm-4">
					${manualdemand.peaksegment}

			</div>
		</div>



		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量：</label>
			<div class="col-sm-4">
					${manualdemand.grainsegment}

			</div>

			<label class="col-sm-2 control-label">平段总量：</label>
			<div class="col-sm-4">
					${manualdemand.flatsegment}

			</div>
		</div>



		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-4">
					${manualdemand.pluswatt}

			</div>

			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-4">
					${manualdemand.resewatt}

			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-4">
					${manualdemand.idleroll}

			</div>
		</div>








		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="dataprepare:manualdemand:manualdemand:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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