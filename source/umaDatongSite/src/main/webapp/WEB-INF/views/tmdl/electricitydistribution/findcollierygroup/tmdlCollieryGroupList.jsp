<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>煤业和集团查询管理</title>
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
		<li class="active"><a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/">煤业和集团查询列表</a></li>
		<shiro:hasPermission name="electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit"><li><a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/form">煤业和集团查询添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlCollieryGroup" action="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
					<sys:itemselect id="unitId" name="unitId.id" value="${tmdlPowerCompany.unitId.id}" labelName="unitId.name" labelValue="${tmdlPowerShunt.unitId.name}"
									title="单位名称" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
									tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="${tmdlCollieryGroup.time}"
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
				<th>单位代码</th>
				<th>单位名称</th>
				<th>煤矿企业名称</th>
				<th>总用电</th>
				<th>照明</th>
				<th>动力</th>
				<th>峰段</th>
				<th>谷段</th>
				<th>平段</th>
				<th>需量</th>
				<th>月度</th>
				<th>功率因素</th>
				<th>外转供电量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlCollieryGroup">
			<tr>
				<td><a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/formView?id=${tmdlCollieryGroup.id}&collieryname=${tmdlCollieryGroup.unitId.colgroupid.name}&time=${tmdlCollieryGroup.time}">
					${tmdlCollieryGroup.unitId.companyNo}
				</a></td>
				<td>${tmdlCollieryGroup.unitId.name}</td>

				<td>${tmdlCollieryGroup.unitId.colgroupid.name}</td>

				<td>
						${tmdlCollieryGroup.wattfulgross}
				</td>
				<td></td>
				<td></td>

				<td>
					${tmdlCollieryGroup.peaksegment}
				</td>
				<td>
					${tmdlCollieryGroup.grainsegment}
				</td>
				<td>
					${tmdlCollieryGroup.flatsegment}
				</td>
				<td>
					${tmdlCollieryGroup.demand}
				</td>

				<td> ${tmdlCollieryGroup.time}

						</td>
				<td></td>
				<td></td>

				<td>
				<a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/formView?id=${tmdlCollieryGroup.id}&collieryname=${tmdlCollieryGroup.unitId.colgroupid.name}&time=${tmdlCollieryGroup.time}">详情</a>
				<shiro:hasPermission name="electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit">
    				<a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/form?id=${tmdlCollieryGroup.id}&time=${tmdlCollieryGroup.time}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/electricitydistribution/findcollierygroup/tmdlCollieryGroup/delete?id=${tmdlCollieryGroup.id}&time=${tmdlCollieryGroup.time}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该煤业和集团查询吗？', this.href)">删除</a>
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