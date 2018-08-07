<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转入转出电量录入管理</title>
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
		<li><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/">转入转出电量录入列表</a></li>
		<li class="active"><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/form?id=${powerMoveMng.id}">转入转出电量录入<shiro:hasPermission name="dataprepare:powermovemng:powerMoveMng:edit">${not empty powerMoveMng.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="dataprepare:powermovemng:powerMoveMng:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="powerMoveMng" action="${ctx}/dataprepare/powermovemng/powerMoveMng/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位：</label>
			<div class="col-sm-4">
				<label class="control-label">${powerMoveMng.unitid.name	}	</label>		
			
			</div>
		
			<label class="col-sm-2 control-label">转入转出日期：</label>
			<div class="col-sm-4">
				<label class="control-label"><fmt:formatDate value="${powerMoveMng.createtime}" pattern="yyyy-MM-dd"/></label>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">有功转入：</label>
			<div class="col-sm-4">
				<label class="control-label">${powerMoveMng.ygzr	}	</label>		
			
			</div>
		
			<label class="col-sm-2 control-label">有功转出：</label>
			<div class="col-sm-4">
				<label class="control-label">${powerMoveMng.ygzc	}		</label>	
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">无功转入：</label>
			<div class="col-sm-4">
				<label class="control-label">${powerMoveMng.wgzr	}		</label>	
			
			</div>
		
			<label class="col-sm-2 control-label">无功转出：</label>
			<div class="col-sm-4">
				<label class="control-label">${powerMoveMng.wgzc	}	</label>		
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量增减：</label>
			<div class="col-sm-10">
				<label class="control-label">${powerMoveMng.xlzj	}	</label>		
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				${powerMoveMng.remarks	}
			
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