<%@ page import="com.umasoft.umafrmsite.modules.tmdl._common.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>全局电量管理</title>
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
		<li class="active"><a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/">全局电量列表</a></li>
		<shiro:hasPermission name="electricalanalysis:globalpower:tmdlUsePowerGlobal:edit"><li><a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/form">全局电量添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlUsePowerGlobal" action="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">用电年月：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${tmdlUsePowerGlobal.time}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>用电年月</th>
				<th>全局收入电量</th>
				<th>最大负荷</th>
				<th>平均负荷</th>
				<th>负荷率</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlUsePowerGlobal">
			<tr>
				<td><a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/formView?id=${tmdlUsePowerGlobal.id}">
					<fmt:formatDate value="${tmdlUsePowerGlobal.time}" pattern="yyyy-MM"/>
				</a></td>
				<td>
					${tmdlUsePowerGlobal.globalElectricity}
				</td>

				<td>
						${tmdlUsePowerGlobal.maxload}
				</td>

				<td> ${tmdlUsePowerGlobal.meanload}</td>
				<td>
						${tmdlUsePowerGlobal.loadrate}
				</td>
				<td>


				<a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/formView?id=${tmdlUsePowerGlobal.id}">详情</a>
				<shiro:hasPermission name="electricalanalysis:globalpower:tmdlUsePowerGlobal:edit">


                    <c:set var="nowmonth">
                        <fmt:formatDate value="<%=DateUtils.getSomeDate(0)%>" pattern="yyyy-MM" type="date"/>
                    </c:set>

                    <c:set var="othermonth">
                        <fmt:formatDate value="${tmdlUsePowerGlobal.time}" pattern="yyyy-MM"/>
                    </c:set>

					<c:choose>
						<c:when test="${othermonth>=nowmonth}">
							<a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/form?id=${tmdlUsePowerGlobal.id}">修改</a>
							<a href="${ctx}/electricalanalysis/globalpower/tmdlUsePowerGlobal/delete?id=${tmdlUsePowerGlobal.id}" onclick="return confirmx('确认要删除该全局电量吗？', this.href)">删除</a>
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