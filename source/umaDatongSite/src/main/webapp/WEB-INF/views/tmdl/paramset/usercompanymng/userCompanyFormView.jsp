<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用电用户管理管理</title>
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
		<li><a href="${ctx}/paramset/usercompanymng/userCompany/">用电用户管理列表</a></li>
		<li class="active"><a href="${ctx}/paramset/usercompanymng/userCompany/formView?id=${userCompany.id}">用电用户管理查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="userCompany" action="${ctx}/paramset/usercompanymng/userCompany/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">煤矿集团：</label>
			<div class="col-sm-4">
			${userCompany.colgroupid.name}
			</div>
	
			<label class="col-sm-2 control-label">变电站：</label>
			<div class="col-sm-4">
			${userCompany.bdzid.name}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位编号：</label>
			<div class="col-sm-4">
				${userCompany.code	}			
			
			</div>
		
			<label class="col-sm-2 control-label">用电单位名称：</label>
			<div class="col-sm-4">
				${userCompany.name	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">企业代码：</label>
			<div class="col-sm-4">
				${userCompany.companyNo	}			
			
			</div>
		
			<label class="col-sm-2 control-label">标准照明电量：</label>
			<div class="col-sm-4">
				${userCompany.standardIllumination	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电量序号：</label>
			<div class="col-sm-4">
				${userCompany.powercode	}			
			
			</div>
	
			<label class="col-sm-2 control-label">用电序号：</label>
			<div class="col-sm-4">
				${userCompany.ueleccode	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电费类别：</label>
			<div class="col-sm-4">
				${fns:getDictLabel(userCompany.costcategory,'TMDL_Electricity_fees_type','')}
			
			</div>
	
			<label class="col-sm-2 control-label">比例：</label>
			<div class="col-sm-4">
				${userCompany.ratio	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">银行托收：</label>
			<div class="col-sm-4">						
			    ${fns:getDictLabel(userCompany.tollmethod,'TMDL_BANK_COLLECTION','')}
			</div>
	
			<label class="col-sm-2 control-label">是否使用分配比例：</label>
			<div class="col-sm-4">
				${fns:getDictLabel(userCompany.isratio,'yes_no','')}
			
			</div>
		</div>
		<c:if test="${userCompany.cgList  != null && userCompany.cgList.size()>0}">
		<c:forEach items="${userCompany.cgList}" var="cg">
		<div class="form-group">
			<label class="col-sm-2 control-label">集团煤业名称：</label>
			<div class="col-sm-4">
				${cg.collieryname}
			
			</div>
		
			<label class="col-sm-2 control-label">比例：</label>
			<div class="col-sm-4">
				${cg.ydbl}
			
			</div>
		</div>
		</c:forEach>
		</c:if>
		<div class="form-group">
			<label class="col-sm-2 control-label">月度总功：</label>
			<div class="col-sm-4">
				${userCompany.zmyd	}			
			
			</div>
		
			<label class="col-sm-2 control-label">税率：</label>
			<div class="col-sm-4">
				${userCompany.sl	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">是否参与计算：</label>
			<div class="col-sm-10">
				${fns:getDictLabel(userCompany.sfcyjs,'yes_no','1')}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				${userCompany.remarks	}
			
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