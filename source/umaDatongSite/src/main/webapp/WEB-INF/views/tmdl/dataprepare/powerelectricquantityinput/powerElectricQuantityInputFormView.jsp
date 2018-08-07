<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月度分路表底录入管理</title>
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
		<li><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/">月度分路表底录入列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/form?id=${powerElectricQuantityInput.id}">月度分路表底录入<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit">${not empty powerElectricQuantityInput.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="powerElectricQuantityInput" action="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">分路ID：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.shuntId.name	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.wattfulgross	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.peaksegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.grainsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.flatsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.pluswatt	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.resewatt	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.idleroll	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.antiReactivePower	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-10">
				<label class="control-label"><fmt:formatDate value="${powerElectricQuantityInput.time}" pattern="yyyy-MM-dd HH:mm:ss"/></label>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerElectricQuantityInput.remarks	}</label>
			
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