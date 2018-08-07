<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站管理管理</title>
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
		<li><a href="${ctx}/paramset/substationmng/tmdlSubstation/">变电站管理列表</a></li>
		<li class="active"><a href="${ctx}/paramset/substationmng/tmdlSubstation/form?id=${tmdlSubstation.id}">变电站管理<shiro:hasPermission name="paramset:substationmng:tmdlSubstation:edit">${not empty tmdlSubstation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="paramset:substationmng:tmdlSubstation:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlSubstation" action="${ctx}/paramset/substationmng/tmdlSubstation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="form-group">
			<label class="col-sm-2 control-label">变电站名称：</label>
			<div class="col-sm-10">
				${tmdlSubstation.name	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">变电站编号：</label>
			<div class="col-sm-10">
				${tmdlSubstation.code	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				${tmdlSubstation.remarks	}
			
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