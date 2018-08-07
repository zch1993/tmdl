<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>录入供电情况管理</title>
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
		<li><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/">录入供电情况列表</a></li>
		<li class="active"><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/form?id=${tmdlUsePowerSupply.id}">录入供电情况<shiro:hasPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">${not empty tmdlUsePowerSupply.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlUsePowerSupply" action="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.sysid.name}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">日期：</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${tmdlUsePowerSupply.time}" pattern="yyyy-MM"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">开始时间：</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${tmdlUsePowerSupply.begintime}" pattern="yyyy-MM-dd"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">结束时间：</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${tmdlUsePowerSupply.endtime}" pattern="yyyy-MM-dd"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">收入电量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.srdl	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">局外转供电量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.loser	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">局内转供电量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.council	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">原煤生产：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.coal	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">基本建设：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.build	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">非原煤生产：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.notcoal	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">非生产部门：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.notproduce	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">生活用电：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.llife	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">原煤总产量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.allcoal	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">矿井加漏天产量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.allseep	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">最大负荷：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.maxload	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平均负荷：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.meanload	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">负荷率：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.loadrate	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">功率因数：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.pf	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">利率奖罚：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.lljf	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功电量：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.wg	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">线变损：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.xbs	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				${tmdlUsePowerSupply.remarks	}
			
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