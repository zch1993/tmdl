<%@ page import="com.umasoft.umafrmsite.modules.tmdl._common.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>需量管理管理</title>
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
		<li class="active"><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/">需量管理列表</a></li>
		<shiro:hasPermission name="paramset:demandmanagement:tmdlPowerDemand:edit"><li><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/form">需量管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlPowerDemand" action="${ctx}/paramset/demandmanagement/tmdlPowerDemand/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">用电单位：</label>
				<div class="control-inline">
					<sys:itemselect id="unitId" name="unitId.id" value="${tmdlPowerDemand.unitId.id}" labelName="unitId.name" labelValue="${tmdlSystemCollieryGroup.unitId.name}"
									title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
									tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">变电站：</label>
				<div class="control-inline">
					<sys:itemselect id="stationId" name="stationId.id" value="${tmdlPowerDemand.stationId.id}" labelName="stationId.name" labelValue="${tmdlPowerDemand.stationId.name}"
									title="变电站" url="/paramset/substationmng/tmdlSubstation/listData" cssClass="required" allowClear="true"
									tablecolumn="name:变电站名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">分路：</label>
				<div class="control-inline">
					<sys:itemselect id="shuntId" name="shuntId.id" value="${tmdlPowerDemand.shuntId.id}" labelName="shuntId.name" labelValue="${tmdlPowerDemand.shuntId.name}"
									title="分路" url="/oamanagement/branchmnagement/tmdlPowerShunt/findallData" cssClass="required" allowClear="true"
									tablecolumn="name:分路名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>



			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>


			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用电单位</th>
				<th>变电站</th>
				<th>分路</th>
				<th>需量</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlPowerDemand">
			<tr>

				<td>
					${tmdlPowerDemand.unitId.name}
				</td>
				<td>
						${tmdlPowerDemand.stationId.name}
				</td>
				<td>
						${tmdlPowerDemand.shuntId.name}
				</td>

				<td>
						${tmdlPowerDemand.demand}
				</td>
				<td>
				<a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/formView?id=${tmdlPowerDemand.id}">详情</a>
				<shiro:hasPermission name="paramset:demandmanagement:tmdlPowerDemand:edit">

					<c:set var="nowmonth">
						<fmt:formatDate value="<%=DateUtils.getSomeDate(0)%>" pattern="yyyy-MM" type="date"/>
					</c:set>

					<c:set var="othermonth">
						<fmt:formatDate value="${tmdlPowerDemand.month}" pattern="yyyy-MM"/>
					</c:set>
					<c:choose>
					<c:when test="${othermonth>=nowmonth}">
    				<a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/form?id=${tmdlPowerDemand.id}">修改</a>
					<a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/delete?id=${tmdlPowerDemand.id}" onclick="return confirmx('确认要删除该需量管理吗？', this.href)">删除</a>
					</c:when>
						<c:otherwise></c:otherwise>
					</c:choose>
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