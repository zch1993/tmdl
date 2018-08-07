<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>转入转出电量录入管理</title>
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
		<li class="active"><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/">转入转出电量录入列表</a></li>
		<shiro:hasPermission name="dataprepare:powermovemng:powerMoveMng:edit"><li><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/form">转入转出电量录入添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="powerMoveMng" action="${ctx}/dataprepare/powermovemng/powerMoveMng/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">用电单位：</label>
				<div class="control-inline">
				<form:input path="unitid.name" htmlEscape="false" maxlength="20" class="form-control"/>
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
				<th>转入转出日期</th>
				<th>有功转入</th>
				<th>有功转出</th>
				<th>无功转入</th>
				<th>无功转出</th>
				<th>需量增减</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="powerMoveMng">
			<tr>
				<td><a href="${ctx}/dataprepare/powermovemng/powerMoveMng/formView?id=${powerMoveMng.id}">
					${powerMoveMng.unitid.name}
				</a></td>
				<td>
					<fmt:formatDate value="${powerMoveMng.createtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${powerMoveMng.ygzr}
				</td>
				<td>
					${powerMoveMng.ygzc}
				</td>
				<td>
					${powerMoveMng.wgzr}
				</td>
				<td>
					${powerMoveMng.wgzc}
				</td>
				<td>
					${powerMoveMng.xlzj}
				</td>
				<td>
					${powerMoveMng.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${powerMoveMng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a href="${ctx}/dataprepare/powermovemng/powerMoveMng/formView?id=${powerMoveMng.id}">详情</a>
				<shiro:hasPermission name="dataprepare:powermovemng:powerMoveMng:edit">
    				<a href="${ctx}/dataprepare/powermovemng/powerMoveMng/form?id=${powerMoveMng.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/dataprepare/powermovemng/powerMoveMng/delete?id=${powerMoveMng.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该转入转出电量录入吗？', this.href)">删除</a>
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