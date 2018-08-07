<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>矿业和集团比例分配管理</title>
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
		<li><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/">矿业和集团比例分配列表</a></li>
		<li class="active"><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/form?id=${systemCollieryGroup.id}">矿业和集团比例分配查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="systemCollieryGroup" action="${ctx}/paramset/usercompanymng/systemCollieryGroup/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<div class="form-group">
			<label class="col-sm-2 control-label">单位：</label>
			<div class="col-sm-10">
				<label class="control-label">${systemCollieryGroup.unitid.name	}		</label>

			</div>
		</div>
		<%--<c:if test="${userCompany.cgList  != null && userCompany.cgList.size()>0}">
		<c:forEach items="${userCompany.cgList}" var="cg">--%>
		<c:if test="${systemCollieryGroup.cgList  != null && systemCollieryGroup.cgList.size()>0}">
			<c:forEach items="${systemCollieryGroup.cgList}" var="cg">
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