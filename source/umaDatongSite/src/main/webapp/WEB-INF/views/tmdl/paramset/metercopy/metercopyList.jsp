<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>抄表本管理</title>
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
		<li class="active"><a href="${ctx}/paramset/metercopy/metercopy/">抄表本列表</a></li>
		<shiro:hasPermission name="paramset:metercopy:metercopy:edit"><li><a href="${ctx}/paramset/metercopy/metercopy/form">抄表本添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="metercopy" action="${ctx}/paramset/metercopy/metercopy/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">开关编号：</label>
				<div class="control-inline">
				<form:input path="kgbh" htmlEscape="false" maxlength="255" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">路别名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="255" class="form-control"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>开关编号</th>
				<th>路别名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="metercopy">
			<tr>
				<td><a href="${ctx}/paramset/metercopy/metercopy/formView?id=${metercopy.id}">
					${metercopy.kgbh}
				</a></td>
				<td>
					${metercopy.name}
				</td>

				<td>
				<a href="${ctx}/paramset/metercopy/metercopy/formView?id=${metercopy.id}">详情</a>
				<shiro:hasPermission name="paramset:metercopy:metercopy:edit">
    				<a href="${ctx}/paramset/metercopy/metercopy/form?id=${metercopy.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/paramset/metercopy/metercopy/delete?id=${metercopy.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该抄表本吗？', this.href)">删除</a>
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