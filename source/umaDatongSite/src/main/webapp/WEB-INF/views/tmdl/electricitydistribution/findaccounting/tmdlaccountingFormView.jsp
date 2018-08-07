<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核算表查询管理</title>
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
		<li><a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/">核算表查询列表</a></li>
		<li class="active"><a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/form?id=${tmdlaccounting.id}">核算表查询查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlaccounting" action="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>


		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.unitId.name	}		</label>
			
			</div>


			<label class="col-sm-2 control-label">月度：</label>
			<div class="col-sm-4">
				<label class="control-label">
					${tmdlaccounting.time}
				</label>

			</div>

		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">本月总用电：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.wattfulgross	}		</label>

			</div>

			<label class="col-sm-2 control-label">上月总用电：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.wattfulgross	}		</label>

			</div>
		</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">增减：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.peaksegment	}		</label>	
			
			</div>
			<label class="col-sm-2 control-label">增减百分比：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.grainsegment	}		</label>

			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">照明用电：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.grainsegment	}		</label>	
			
			</div>

			<label class="col-sm-2 control-label">动力用电：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.grainsegment	}		</label>

			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段总量：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.flatsegment	}		</label>	
			
			</div>

			<label class="col-sm-2 control-label">谷段总量：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.flatsegment	}		</label>

			</div>
		</div>
		<div class="form-group">

			<label class="col-sm-2 control-label">平段总量：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.flatsegment	}		</label>

			</div>
			<label class="col-sm-2 control-label">需量：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.demand}		</label>

			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">力率：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.resewatt	}		</label>	
			
			</div>

			<label class="col-sm-2 control-label">平均负荷：</label>
			<div class="col-sm-4">
				<label class="control-label">${tmdlaccounting.demnum	}		</label>

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