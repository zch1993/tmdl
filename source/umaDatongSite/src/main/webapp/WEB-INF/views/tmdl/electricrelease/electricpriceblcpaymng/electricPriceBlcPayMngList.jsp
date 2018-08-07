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
		<li class="active"><a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/">电价补差管理列表</a></li>
		<!-- 
		<shiro:hasPermission name="electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit"><li><a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/form">电价补差管理添加</a></li></shiro:hasPermission>
		 -->
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="electricPriceBlcPayMng" action="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
				<sys:itemselect id="unitid" name="unitid.id" value="${electricPriceBlcPayMng.unitid.id}" labelName="unitid.name" labelValue="${electricPriceBlcPayMng.unitid.name}"
title="煤矿集团" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true" 
tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">月度：</label>
				<div class="control-inline">
				<input name="addtime" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${electricPriceBlcPayMng.addtime}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:true});"/>
					
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">电价查询：</label>
				<div class="control-inline">				
					 <label class="control-label"><input type="radio" name="getprice" value="1" <c:if test="${electricPriceBlcPayMng.getprice == 1}">checked="true"</c:if> class="form-control"/>查询电价补差</label>
					 <label class="control-label"><input type="radio" name="getprice" value="2" <c:if test="${electricPriceBlcPayMng.getprice == 2}">checked="true"</c:if> class="form-control"/>设置电价补差</label>
					 <label class="control-label"><input type="radio" name="getprice" value="3" <c:if test="${electricPriceBlcPayMng.getprice == 3}">checked="true"</c:if> class="form-control"/>所有电价补差记录</label>
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
				<th>平段用电单价</th>
				<th>峰段用电单价</th>
				<th>谷段用电单价</th>
				<th>动力用电单价</th>
				<th>照明单价</th>
				<th>需量单价</th>
				<th>外转供电价</th>
				<th>税率</th>				
				<th>税率</th>
				<th>平价</th>
				<th>电价月度</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="electricPriceBlcPayMng">
			<tr>
				<td><a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/formView?id=${electricPriceBlcPayMng.id}">
					${electricPriceBlcPayMng.unitid.name}
				</a></td>
				<td>
					${electricPriceBlcPayMng.pingp}
				</td>
				<td>
					${electricPriceBlcPayMng.fengp}
				</td>
				<td>
					${electricPriceBlcPayMng.gup}
				</td>
				<td>
					${electricPriceBlcPayMng.dlp}
				</td>
				<td>
					${electricPriceBlcPayMng.zmp}
				</td>
				<td>
					${electricPriceBlcPayMng.xlp}
				</td>
				<td>
					${electricPriceBlcPayMng.wzgp}
				</td>
				<td>
					${electricPriceBlcPayMng.taxrate}
				</td>
				
				<td>
					${electricPriceBlcPayMng.sl}
				</td>
				<td>
					${electricPriceBlcPayMng.flatprice}
				</td>
				<td>
					<fmt:formatDate value="${electricPriceBlcPayMng.addtime}" pattern="yyyy-MM"/>
				</td>
				<td>
					${electricPriceBlcPayMng.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${electricPriceBlcPayMng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<c:choose>
				<c:when test="${electricPriceBlcPayMng.id != '' and electricPriceBlcPayMng.id != null}">
				<a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/formView?id=${electricPriceBlcPayMng.id}">详情</a>
				<shiro:hasPermission name="electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit">
	
    				<a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/form?id=${electricPriceBlcPayMng.id}&unitidid=${unitid.id}&pageno=${page.pageNo}&addtimeparam=${addtime}">修改</a>
					<a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/delete?id=${electricPriceBlcPayMng.id}" onclick="return confirmx('确认要删除该电价管理吗？', this.href)">删除</a>

				</shiro:hasPermission>
				</c:when>
					<c:otherwise>
					<a href="${ctx}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/form?id=${electricPriceBlcPayMng.id}&unitid.id=${electricPriceBlcPayMng.unitid.id}&unitidid=${unitid.id}&pageno=${page.pageNo}<c:if test="${not empty addtime}">&addtimeparam=${addtime}</c:if>">添加</a>
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