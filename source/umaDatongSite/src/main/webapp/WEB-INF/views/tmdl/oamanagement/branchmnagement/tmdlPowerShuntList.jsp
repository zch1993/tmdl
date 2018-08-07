<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分路管理</title>
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
		<li class="active"><a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/">分路管理列表</a></li>
		<shiro:hasPermission name="oamanagement:branchmnagement:tmdlPowerShunt:edit"><li><a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/form">分路管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlPowerShunt" action="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">路别名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="255" class="form-control"/>

					<label class="control-label">变电站名称：</label>
					<div class="control-inline">
						<sys:itemselect id="bdzid" name="bdzid.id" value="${tmdlPowerShunt.bdzid.id}" labelName="bdzid.name" labelValue="${tmdlPowerShunt.bdzid.name}"
										title="变电站" url="/paramset/substationmng/tmdlSubstation/listData" cssClass="required" allowClear="true"
										tablecolumn="name:变电站名称,code:变电站代码"  searchcolumn="name" checked="true" multiSelect="false"/>


						<label class="control-label">单位名称：</label>
						<div class="control-inline">
							<sys:itemselect id="unitId" name="unitId.id" value="${tmdlPowerShunt.unitId.id}" labelName="unitId.name" labelValue="${tmdlPowerShunt.unitId.name}"
											title="单位名称" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
											tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
						</div>

						<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
			</div>
			</div>

	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>开关编号</th>
				<th>路别名称</th>
				<th>变电站名称</th>
				<th>倍率</th>
				<th>电量加减</th>
				<th>需量1加减</th>
				<th>需量2加减</th>
				<th>计算倍数</th>
				<th>单位性质</th>
				<th>单位代码</th>
				<th>开关编号1(线路编号)</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlPowerShunt">
			<tr>
				<td><a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/formView?id=${tmdlPowerShunt.id}">
						${tmdlPowerShunt.unitId.name}
				</a>
				</td>
				<td>
						${tmdlPowerShunt.kgbh}
				</td>
				<td>
					${tmdlPowerShunt.name}
				</td>
				<td>
					${tmdlPowerShunt.bdzid.name}
				</td>
				<td>
					${tmdlPowerShunt.bl}
				</td>
				<td>
					${tmdlPowerShunt.dljj}
				</td>
				<td>
					${tmdlPowerShunt.xl1jj}
				</td>
				<td>
					${tmdlPowerShunt.xl2jj}
				</td>
				<td>
					${tmdlPowerShunt.jsbs}
				</td>
				<td>
						${fns:getDictLabel(tmdlPowerShunt.unitType, 'TMDL_COMPNY', '')}

				</td>
				<td>
					${tmdlPowerShunt.unitId.name}
				</td>
				<td>
					${tmdlPowerShunt.kgbh1}
				</td>
				<td>
				<a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/formView?id=${tmdlPowerShunt.id}">详情</a>
				<shiro:hasPermission name="oamanagement:branchmnagement:tmdlPowerShunt:edit">
    				<a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/form?id=${tmdlPowerShunt.id}">修改</a>
					<a href="${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/delete?id=${tmdlPowerShunt.id}" onclick="return confirmx('确认要删除该分路管理吗？', this.href)">删除</a>
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