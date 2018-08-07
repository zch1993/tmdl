<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础表查询管理</title>
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
		<li><a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/">基础表查询列表</a></li>
		<li class="active"><a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/form?id=${tmdlPowerCompany.id}">基础表查询查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlPowerCompany" action="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.unitId.name	}		</label>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.wattfulgross	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.peaksegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.grainsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.flatsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.pluswatt	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.resewatt	}		</label>	
			
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.time}</label>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.idleroll	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.antiReactivePower	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上月表底：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.lastbase	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">本月表底：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.nowbase	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">天数：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.days	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上月用电量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.lastpower	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">外转供电：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.abversionpoe	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlPowerCompany.demand	}		</label>	
			
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