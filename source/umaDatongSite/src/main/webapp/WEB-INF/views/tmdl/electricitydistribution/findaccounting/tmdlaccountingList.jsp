<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>核算表查询管理</title>
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
		<li class="active"><a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/">核算表查询列表</a></li>
		<shiro:hasPermission name="electricitydistribution:findaccounting:tmdlaccounting:edit"><li><a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/form">核算表查询添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlaccounting" action="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
					<sys:itemselect id="unitId" name="unitId.id" value="${tmdlaccounting.unitId.id}" labelName="unitId.name" labelValue="${tmdlaccounting.unitId.name}"
									title="单位名称" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
									tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>

			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="${tmdlaccounting.time}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>月度</th>
				<th>本月总用电</th>
				<th>上月总用电</th>
				<th>增减</th>
				<th>增减百分比</th>
				<th>照明用电</th>
				<th>动力用电</th>
				<th>峰段总量</th>
				<th>谷段总量</th>
				<th>平段总量</th>
				<th>需量</th>
				<th>力率</th>
				<th>平均负荷</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlaccounting">
			<tr>
				<td><a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/formView?id=${tmdlaccounting.id}">
					${tmdlaccounting.unitId.name}
				</a></td>

				<td>
					${tmdlaccounting.time}
				</td>
				<td>
					${tmdlaccounting.wattfulgross}
				</td>

				<td>
						${tmdlaccounting.wattfulgross}
				</td>
				<td>
						${tmdlaccounting.wattfulgross}
				</td>
				<td>
						${tmdlaccounting.wattfulgross}
				</td>
				<td>
						${tmdlaccounting.wattfulgross}
				</td>
				<td>
						${tmdlaccounting.wattfulgross}
				</td>
				<td>
					${tmdlaccounting.peaksegment}
				</td>
				<td>
					${tmdlaccounting.grainsegment}
				</td>
				<td>
					${tmdlaccounting.flatsegment}
				</td>
				<td>
					${tmdlaccounting.demand}
				</td>

				<td>
						${tmdlaccounting.demand}
				</td>
				<td>
						${tmdlaccounting.demand}
				</td>
			<td>
				<a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/formView?id=${tmdlaccounting.id}">详情</a>
				<shiro:hasPermission name="electricitydistribution:findaccounting:tmdlaccounting:edit">
    				<a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/form?id=${tmdlaccounting.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/electricitydistribution/findaccounting/tmdlaccounting/delete?id=${tmdlaccounting.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该核算表查询吗？', this.href)">删除</a>
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