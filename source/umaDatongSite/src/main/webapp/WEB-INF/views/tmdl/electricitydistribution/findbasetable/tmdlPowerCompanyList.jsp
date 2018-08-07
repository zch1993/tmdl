<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>基础表查询管理</title>
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

        function CJButton() {
            $.ajax({
                type: "POST",
                url: "${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/dataSynchro",
                data: "",
                async: false,
                success:function(data){
                    toastr.success(data);
                },ajaxSuccess(data){
                    toastr.success("ajaxSuccess==>"+data);
				}

            })


        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/">基础表查询列表</a></li>
		<shiro:hasPermission name="electricitydistribution:findbasetable:tmdlPowerCompany:edit"><li><a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/form">基础表查询添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlPowerCompany" action="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">单位ID：</label>
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
					value="${tmdlPowerCompany.time}"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			<div class="form-group"><input id="companycj" class="btn btn-primary" type="submit" value="采集" onclick="CJButton()"/></div>
			
	</form:form>



	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>上月表底</th>
				<th>本月表底</th>
				<th>本有功总量</th>
				<th>上月有功总量</th>
				<th>月度</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlPowerCompany">
			<tr>
				<td><a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/formView?id=${tmdlPowerCompany.id}">
					${tmdlPowerCompany.unitId.name}
				</a></td>
				<td>
						${tmdlPowerCompany.lastbase	}
				</td>

				<td>
						${tmdlPowerCompany.nowbase	}
				</td>
				<td>

						${tmdlPowerCompany.wattfulgross	}
				</td>
				<td>

						${tmdlPowerCompany.lastpower	}
				</td>
				<td>

					${tmdlPowerCompany.time}
				</td>
				<td>
				<a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/formView?id=${tmdlPowerCompany.id}">详情</a>
				<shiro:hasPermission name="electricitydistribution:findbasetable:tmdlPowerCompany:edit">
    				<a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/form?id=${tmdlPowerCompany.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/electricitydistribution/findbasetable/tmdlPowerCompany/delete?id=${tmdlPowerCompany.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该基础表查询吗？', this.href)">删除</a>
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