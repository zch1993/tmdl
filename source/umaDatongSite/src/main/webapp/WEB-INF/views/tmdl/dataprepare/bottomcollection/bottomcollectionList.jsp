<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表底采集保存成功管理</title>
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
                url: "${ctx}/dataprepare/bottomcollection/bottomcollection/dataSynchro",
                data: "",
                async: false,
                success:function(data){
                 console.log("success=="+data)


                },
                ajaxSuccess(data){
                    console.log("ajaxSuccess=="+data)
				}

            })


        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/dataprepare/bottomcollection/bottomcollection/">表底采集保存成功列表</a></li>
		<shiro:hasPermission name="dataprepare:bottomcollection:bottomcollection:edit"><li><a href="${ctx}/dataprepare/bottomcollection/bottomcollection/form">表底采集保存成功添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="bottomcollection" action="${ctx}/dataprepare/bottomcollection/bottomcollection/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
			<div class="form-group">
				<label class="control-label">分路ID：</label>
				<div class="control-inline">
                    <sys:itemselect id="shuntId" name="shuntId.id" value="${bottomcollection.shuntId.id}" labelName="shuntId.name" labelValue="${bottomcollection.shuntId.name}"
                                    title="分路" url="/oamanagement/branchmnagement/tmdlPowerShunt/listData" cssClass="required" allowClear="true"
                                    tablecolumn="name:分路名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${bottomcollection.time}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
            <div class="form-group"><input id="btncj" class="btn btn-primary" type="submit" value="采集" onclick="CJButton()"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>分路名称</th>
				<th>开关编号</th>
				<th>有功总量</th>
				<th>峰段总量</th>
				<th>谷段总量</th>
				<th>平段总量</th>
				<th>正有功总量</th>
				<th>反有功总量</th>
				<th>无功总量</th>
				<th>月度</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="bottomcollection">
			<tr>
				<td><a href="${ctx}/dataprepare/bottomcollection/bottomcollection/formView?id=${bottomcollection.id}">
					${bottomcollection.shuntId.name}
				</a></td>

				<td>
						${bottomcollection.shuntId.kgbh}
				</td>
				<td>
					${bottomcollection.wattfulgross}
				</td>
				<td>
					${bottomcollection.peaksegment}
				</td>
				<td>
					${bottomcollection.grainsegment}
				</td>
				<td>
					${bottomcollection.flatsegment}
				</td>
				<td>
					${bottomcollection.pluswatt}
				</td>
				<td>
					${bottomcollection.resewatt}
				</td>
				<td>
					${bottomcollection.idleroll}
				</td>
				<td>
					${bottomcollection.time}
				</td>

				<td>
				<a href="${ctx}/dataprepare/bottomcollection/bottomcollection/formView?id=${bottomcollection.id}">详情</a>
				<shiro:hasPermission name="dataprepare:bottomcollection:bottomcollection:edit">
    				<a href="${ctx}/dataprepare/bottomcollection/bottomcollection/form?id=${bottomcollection.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/dataprepare/bottomcollection/bottomcollection/delete?id=${bottomcollection.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该表底采集保存成功吗？', this.href)">删除</a>
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