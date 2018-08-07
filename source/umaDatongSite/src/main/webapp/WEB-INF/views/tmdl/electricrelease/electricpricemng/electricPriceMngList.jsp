<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电价管理管理</title>
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
		<li class="active"><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/">电价管理列表</a></li>
		<!-- 
		<shiro:hasPermission name="electricrelease:electricpricemng:electricPriceMng:edit"><li><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/form">电价管理添加</a></li></shiro:hasPermission>
		 -->
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="electricPriceMng" action="${ctx}/electricrelease/electricpricemng/electricPriceMng/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
				<sys:itemselect id="unitid" name="unitid.id" value="${electricPriceMng.unitid.id}" labelName="unitid.name" labelValue="${electricPriceMng.unitid.name}"
title="煤矿集团" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true" 
tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${electricPriceMng.addtime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
					
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">电价查询：</label>
				<div class="control-inline">				
					 <label class="control-label"><input type="radio" name="getprice" value="1" <c:if test="${electricPriceMng.getprice == 1}">checked="true"</c:if> class="form-control"/>查询电价</label>
					 <label class="control-label"><input type="radio" name="getprice" value="2" <c:if test="${electricPriceMng.getprice == 2}">checked="true"</c:if> class="form-control"/>设置电价</label>
					 <label class="control-label"><input type="radio" name="getprice" value="3" <c:if test="${electricPriceMng.getprice == 3}">checked="true"</c:if> class="form-control"/>所有电价记录</label>
				</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			<div class="form-group"><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/formSetAll?pageno=${page.pageNo}<c:if test="${not empty addtime}">&addtime=${addtime}</c:if>" class="btn btn-primary" >批量设置电价</a></div>
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>单位名称</th>
				<th>平段用电单价</th>
				<th>峰段用电单价</th>
				<th>谷段用电单价</th>
				<th>动力用电单价</th>
				<th>照明单价</th>
				<th>需量单价</th>
				<th>力率</th>
				<th>外转供电价</th>
				<th>税率</th>				
				
				<th>平价</th>
				<th>电价月度</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="electricPriceMng">
			<tr>
				<td><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/formView?id=${electricPriceMng.id}">
					${electricPriceMng.unitid.name}
				</a></td>
				<td>
					${electricPriceMng.pingp}
				</td>
				<td>
					${electricPriceMng.fengp}
				</td>
				<td>
					${electricPriceMng.gup}
				</td>
				<td>
					${electricPriceMng.dlp}
				</td>
				<td>
					${electricPriceMng.zmp}
				</td>
				<td>
					${electricPriceMng.xlp}
				</td>
				<td>
					${electricPriceMng.sl}
				</td>
				<td>
					${electricPriceMng.wzgp}
				</td>
				<td>
					${electricPriceMng.taxrate}
				</td>
				
				
				<td>
					${electricPriceMng.flatprice}
				</td>
				<td>
					<fmt:formatDate value="${electricPriceMng.addtime}" pattern="yyyy-MM"/>
				</td>
				<td>
					${electricPriceMng.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${electricPriceMng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:choose>

				<c:when test="${electricPriceMng.id != '' and electricPriceMng.id != null}">
				<a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/formView?id=${electricPriceMng.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">详情</a>
				<shiro:hasPermission name="electricrelease:electricpricemng:electricPriceMng:edit">
	
    				<a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/form?id=${electricPriceMng.id}&unitidid=${unitid.id}&pageno=${page.pageNo}&addtimeparam=${addtime}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/delete?id=${electricPriceMng.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该电价管理吗？', this.href)">删除</a>

				</shiro:hasPermission>
				</c:when>
					<c:otherwise>
					<a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/form?id=${electricPriceMng.id}&unitid.id=${electricPriceMng.unitid.id}&unitidid=${unitid.id}&pageno=${page.pageNo}<c:if test="${not empty addtime}">&addtimeparam=${addtime}</c:if><c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">添加</a>
					</c:otherwise>
					</c:choose>	
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