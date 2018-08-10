<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>需量管理管理</title>
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
		<li><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/">需量管理列表</a></li>
		<li class="active"><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/form?id=${tmdlPowerDemand.id}">需量管理<shiro:hasPermission name="paramset:demandmanagement:tmdlPowerDemand:edit">${not empty tmdlPowerDemand.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="paramset:demandmanagement:tmdlPowerDemand:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlPowerDemand" action="${ctx}/paramset/demandmanagement/tmdlPowerDemand/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位：</label>
			<div class="col-sm-10">
				${tmdlPowerDemand.unitId.name}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">变电站：</label>
			<div class="col-sm-10">
				${tmdlPowerDemand.stationId.name}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">分路：</label>
			<div class="col-sm-10">
				${tmdlPowerDemand.shuntId.name}
			
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">需量：</label>
			<div class="col-sm-10">
				${tmdlPowerDemand.demand	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-10">
				${tmdlPowerDemand.remarks	}
			
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