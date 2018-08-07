<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分路管理</title>
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
			<label class="col-sm-2 control-label">开关编号：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.kgbh	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">路别名称：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.name	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">变电站：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.bdzid.name	}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">倍率：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.bl	}			
			
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">电量加减：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.dljj	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量1加减：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.xl1jj	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量2加减：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.xl2jj	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">计算倍数：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.jsbs	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">单位性质：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.unitType	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.unitId.name}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">开关编号1(线路编号)：</label>
			<div class="col-sm-10">
				${tmdlPowerShunt.kgbh1	}			
			
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