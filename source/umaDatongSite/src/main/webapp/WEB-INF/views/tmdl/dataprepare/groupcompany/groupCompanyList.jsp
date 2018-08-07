<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>集团企业管理管理</title>
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
		<li class="active"><a href="${ctx}/dataprepare/groupcompany/groupCompany/">集团企业管理列表</a></li>
		<shiro:hasPermission name="dataprepare:groupcompany:groupCompany:edit"><li><a href="${ctx}/dataprepare/groupcompany/groupCompany/form">集团企业管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="groupCompany" action="${ctx}/dataprepare/groupcompany/groupCompany/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">集团名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="255" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">更新者：</label>
				<div class="control-inline">
				<form:input path="updateBy.id" htmlEscape="false" maxlength="64" class="form-control"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>集团名称</th>
				<th>有功总量</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="groupCompany">
			<tr>
				<td><a href="${ctx}/dataprepare/groupcompany/groupCompany/formView?id=${groupCompany.id}">
					${groupCompany.name}
				</a></td>
				<td>
					${groupCompany.power}
				</td>
				<td>
					<fmt:formatDate value="${groupCompany.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a href="${ctx}/dataprepare/groupcompany/groupCompany/formView?id=${groupCompany.id}">详情</a>
				<shiro:hasPermission name="dataprepare:groupcompany:groupCompany:edit">
    				<a href="${ctx}/dataprepare/groupcompany/groupCompany/form?id=${groupCompany.id}">修改</a>
					<a href="${ctx}/dataprepare/groupcompany/groupCompany/delete?id=${groupCompany.id}" onclick="return confirmx('确认要删除该集团企业管理吗？', this.href)">删除</a>
				</shiro:hasPermission>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
	</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>