<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>煤业和集团查询管理</title>
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
		<li><a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/">煤业和集团查询列表</a></li>
		<li class="active"><a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/form?id=${tmdlCollieryGroup.id}">煤业和集团查询查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlCollieryGroup" action="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位ID：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.unitId	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.wattfulgross	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.peaksegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.grainsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平段总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.flatsegment	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">正有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.pluswatt	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反有功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.resewatt	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量表底：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.demnum	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.time}</label>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.idleroll	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">反无功总量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.antiReactivePower	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上月表底：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.lastbase	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">本月表底：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.nowbase	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">天数：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.days	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上月用电量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.lastpower	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">外转供电：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.abversionpoe	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.demand	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">插入途径:1:采集数据 2:手工录入：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.insertFlag	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-10">
				<label class="control-label">${tmdlCollieryGroup.remarks	}</label>
			
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