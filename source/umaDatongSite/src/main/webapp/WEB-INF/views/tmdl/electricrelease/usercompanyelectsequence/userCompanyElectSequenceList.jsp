<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用电序列管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var donemsg = "${message}";
			if(donemsg != ""){
				toastr.info(donemsg);
			}
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/">用电序列列表</a></li>
		<!--  
		<shiro:hasPermission name="electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit"><li><a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/form">用电序列添加</a></li></shiro:hasPermission>
		-->
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="userCompanyElectSequence" action="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">用电单位编号：</label>
				<div class="control-inline">
				<form:input path="code" htmlEscape="false" maxlength="65" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">用电单位名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="200" class="form-control"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用电单位编号</th>
				<th>用电单位名称</th>
				<th>标准照明电量</th>
				<th>电量序号</th>
				<th>用电序号</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userCompanyElectSequence">
			<tr>
				<td><a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/formView?id=${userCompanyElectSequence.id}">
					${userCompanyElectSequence.code}
				</a></td>
				<td>
					${userCompanyElectSequence.name}
				</td>
				<td>
					${userCompanyElectSequence.standardIllumination}
				</td>
				<td>
					${userCompanyElectSequence.powercode}
				</td>
				<td>
					${userCompanyElectSequence.ueleccode}
				</td>

				<td>
					${userCompanyElectSequence.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${userCompanyElectSequence.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/formView?id=${userCompanyElectSequence.id}">详情</a>
				<shiro:hasPermission name="electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit">
    				<a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/form?id=${userCompanyElectSequence.id}">修改</a>
				<!-- 
					<a href="${ctx}/electricrelease/usercompanyelectsequence/userCompanyElectSequence/delete?id=${userCompanyElectSequence.id}" onclick="return confirmx('确认要删除该用电序列吗？', this.href)">删除</a>
				 -->
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="pagination">${page}</div>
	</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>