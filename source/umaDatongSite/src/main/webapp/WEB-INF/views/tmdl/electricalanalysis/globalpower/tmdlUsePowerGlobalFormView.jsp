<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局电量管理</title>
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
			<label class="col-sm-2 control-label">用电年月：</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${tmdlUsePowerGlobal.time}" pattern="yyyy-MM"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">全局收入电量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerGlobal.globalElectricity	}			
			
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">最大负荷：</label>
			<div class="col-sm-10">
				${tmdlUsePowerGlobal.maxload	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平均负荷：</label>
			<div class="col-sm-10">
				${tmdlUsePowerGlobal.meanload	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">负荷率：</label>
			<div class="col-sm-10">
				${tmdlUsePowerGlobal.loadrate	}			
			
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