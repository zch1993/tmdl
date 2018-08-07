<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站和用电单位对应关系管理</title>
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
		<li class="active"><a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/">变电站和用电单位对应关系列表</a></li>
		<shiro:hasPermission name="paramset:substationcompanyrelation:substationCompanyRelation:edit"><li><a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/form">变电站和用电单位对应关系添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="substationCompanyRelation" action="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">单位ID：</label>
				<div class="control-inline">
				<form:input path="unitid.id" htmlEscape="false" maxlength="64" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">变电站ID：</label>
				<div class="control-inline">
				<form:input path="bdzid.id" htmlEscape="false" maxlength="64" class="form-control"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位ID</th>
				<th>变电站ID</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="substationCompanyRelation">
			<tr>
				<td><a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/formView?id=${substationCompanyRelation.id}">
					${substationCompanyRelation.unitid.id}
				</a></td>
				<td>
					${substationCompanyRelation.bdzid.id}
				</td>
				<td>
					<fmt:formatDate value="${substationCompanyRelation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${substationCompanyRelation.remarks}
				</td>
				<td>
				<a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/formView?id=${substationCompanyRelation.id}">详情</a>
				<shiro:hasPermission name="paramset:substationcompanyrelation:substationCompanyRelation:edit">
    				<a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/form?id=${substationCompanyRelation.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/paramset/substationcompanyrelation/substationCompanyRelation/delete?id=${substationCompanyRelation.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该变电站和用电单位对应关系吗？', this.href)">删除</a>
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