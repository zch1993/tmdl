<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
		<li><a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/">开户管理列表</a></li>
		<li class="active"><a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/form?id=${tmdlSystemBranch.id}">开户管理<shiro:hasPermission name="oamanagement:openaccount:tmdlSystemBranch:edit">${not empty tmdlSystemBranch.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="oamanagement:openaccount:tmdlSystemBranch:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlSystemBranch" action="${ctx}/oamanagement/openaccount/tmdlSystemBranch/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-4">
				${tmdlSystemBranch.unitid.name}
			
			</div>

			<label class="col-sm-2 control-label">单位代码：</label>
			<div class="col-sm-4">
					${tmdlSystemBranch.unitcode}

			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">核准照明电量：</label>
			<div class="col-sm-10">
				${tmdlSystemBranch.fixation	}			
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">			
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