<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月度分路表底录入管理</title>
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
		<li class="active"><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/">月度分路表底录入列表</a></li>
		<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit"><li><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/form">月度分路表底录入添加</a></li></shiro:hasPermission>
		<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit"><li><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/mutiformView">月度分路表底录入批量添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="powerElectricQuantityInput" action="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">分路编号：</label>
				<div class="control-inline">
				<form:input path="shuntId.kgbh" htmlEscape="false" maxlength="255" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">分路名称：</label>
				<div class="control-inline">
				<form:input path="shuntId.name" htmlEscape="false" maxlength="255" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${powerElectricQuantityInput.time}" pattern="yyyy-MM"/>"
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
				<th>分路编号</th>
				<th>分路名称</th>
				<th>变电站</th>
				<th>有功总量</th>
				<th>峰段总量</th>
				<th>谷段总量</th>
				<th>平段总量</th>
				<th>正有功总量</th>
				<th>反有功总量</th>
				<th>无功总量</th>
				<th>月度</th>
				<th>更新人</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="powerElectricQuantityInput">
			<tr>
				<td><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/formView?id=${powerElectricQuantityInput.id}">
					${powerElectricQuantityInput.shuntId.kgbh}
				</a></td>
				<td>
					${powerElectricQuantityInput.shuntId.name}
				</td>
				<td>
					${powerElectricQuantityInput.shuntId.bdzid.name}
				</td>
				<td>
					${powerElectricQuantityInput.wattfulgross}
				</td>
				<td>
					${powerElectricQuantityInput.peaksegment}
				</td>
				<td>
					${powerElectricQuantityInput.grainsegment}
				</td>
				<td>
					${powerElectricQuantityInput.flatsegment}
				</td>
				<td>
					${powerElectricQuantityInput.pluswatt}
				</td>
				<td>
					${powerElectricQuantityInput.resewatt}
				</td>
				<td>
					${powerElectricQuantityInput.idleroll}
				</td>
				<td>
					<fmt:formatDate value="${powerElectricQuantityInput.time}" pattern="yyyy-MM"/>
				</td>
				<td>
					${powerElectricQuantityInput.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${powerElectricQuantityInput.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/formView?id=${powerElectricQuantityInput.id}">详情</a>
				<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit">
    				<a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/form?id=${powerElectricQuantityInput.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/delete?id=${powerElectricQuantityInput.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该月度分路表底录入吗？', this.href)">删除</a>
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