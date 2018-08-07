<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>开户管理管理</title>
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
		<li class="active"><a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/">开户管理列表</a></li>
		<shiro:hasPermission name="oamanagement:openaccount:tmdlSystemBranch:edit"><li><a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/form">开户管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlSystemBranch" action="${ctx}/oamanagement/openaccount/tmdlSystemBranch/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
					<sys:itemselect id="unitid" name="unitid.id" value="${tmdlSystemBranch.unitid.id}" labelName="unitid.name" labelValue="${tmdlSystemBranch.unitid.name}"
									title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
									tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>



				<label class="control-label">核准照明电量：</label>
				<div class="control-inline">
					<form:input path="fixation" htmlEscape="false" maxlength="18" class="form-control"/>
				</div>
			</div>

			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>单位代码</th>
				<th>核准照明电量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlSystemBranch">
			<tr>
				<td><a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/formView?id=${tmdlSystemBranch.id}">
					${tmdlSystemBranch.unitid.name}
				</a></td>

				<td>
					 ${tmdlSystemBranch.unitcode}
				</td>
				<td>
					${tmdlSystemBranch.fixation}
				</td>

				<td>
				<a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/formView?id=${tmdlSystemBranch.id}">详情</a>
				<shiro:hasPermission name="oamanagement:openaccount:tmdlSystemBranch:edit">
    				<a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/form?id=${tmdlSystemBranch.id}">修改</a>
					<a href="${ctx}/oamanagement/openaccount/tmdlSystemBranch/delete?id=${tmdlSystemBranch.id}" onclick="return confirmx('确认要删除该开户管理吗？', this.href)">删除</a>
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