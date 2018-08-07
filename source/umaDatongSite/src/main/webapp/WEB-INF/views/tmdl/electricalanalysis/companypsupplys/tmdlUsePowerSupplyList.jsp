<%@ page import="com.umasoft.umafrmsite.modules.tmdl._common.DateUtils" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>录入供电情况管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
		    var date =new Date();
		    date.getFullYear();
		    date.getMonth()+1;
		    date.getDay();
		    var month=date.getFullYear()+""+date.getMonth()+1+date.getDay();

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
		<li class="active"><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/">录入供电情况列表</a></li>
		<shiro:hasPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit"><li><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/form">录入供电情况添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlUsePowerSupply" action="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">

					<sys:itemselect id="sysid" name="sysid.id" value="${tmdlUsePowerSupply.sysid.id}" labelName="sysid.name" labelValue="${tmdlUsePowerSupply.sysid.name}"
									title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
									tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>


			</div>
			</div>
			<div class="form-group">
				<label class="control-label">日期：</label>
				<div class="control-inline">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate"
					value="<fmt:formatDate value="${tmdlUsePowerSupply.time}" pattern="yyyy-MM"/>"
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
				<th>开始时间</th>
				<th>结束时间</th>
				<th>收入电量</th>
				<th>局外转供电量</th>
				<th>局内转供电量</th>
				<th>原煤生产</th>
				<th>基本建设</th>
				<th>非原煤生产</th>
				<th>非生产部门</th>
				<th>生活用电</th>
				<th>原煤总产量</th>
				<th>矿井加漏天产量</th>
				<th>最大负荷</th>
				<th>平均负荷</th>
				<th>负荷率</th>
				<th>功率因数</th>
				<th>利率奖罚</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlUsePowerSupply">
			<tr>
				<td><a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/formView?id=${tmdlUsePowerSupply.id}">
					${tmdlUsePowerSupply.sysid.name}
				</a></td>
				<td>
					<fmt:formatDate value="${tmdlUsePowerSupply.begintime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${tmdlUsePowerSupply.endtime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${tmdlUsePowerSupply.srdl}
				</td>
				<td>
					${tmdlUsePowerSupply.loser}
				</td>
				<td>
					${tmdlUsePowerSupply.council}
				</td>
				<td>
					${tmdlUsePowerSupply.coal}
				</td>
				<td>
					${tmdlUsePowerSupply.build}
				</td>
				<td>
					${tmdlUsePowerSupply.notcoal}
				</td>
				<td>
					${tmdlUsePowerSupply.notproduce}
				</td>
				<td>
					${tmdlUsePowerSupply.llife}
				</td>
				<td>
					${tmdlUsePowerSupply.allcoal}
				</td>
				<td>
					${tmdlUsePowerSupply.allseep}
				</td>
				<td>
					${tmdlUsePowerSupply.maxload}
				</td>
				<td>
					${tmdlUsePowerSupply.meanload}
				</td>
				<td>
					${tmdlUsePowerSupply.loadrate}
				</td>
				<td>
					${tmdlUsePowerSupply.pf}
				</td>
				<td>
					${tmdlUsePowerSupply.lljf}
				</td>

				<td>
				<a href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/formView?id=${tmdlUsePowerSupply.id}">详情</a>
				<shiro:hasPermission name="electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit">

					<c:set var="nowmonth">
						<fmt:formatDate value="<%=DateUtils.getSomeDate(0)%>" pattern="yyyy-MM" type="date"/>
					</c:set>

					<c:set var="othermonth">
						<fmt:formatDate value="${tmdlUsePowerSupply.time}" pattern="yyyy-MM"/>
					</c:set>

					<c:choose>
						<c:when test="${othermonth>=nowmonth}">

							<a  id="update" href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/form?id=${tmdlUsePowerSupply.id}">修改</a>
							<a  id="delete" href="${ctx}/electricalanalysis/companypsupplys/tmdlUsePowerSupply/delete?id=${tmdlUsePowerSupply.id}" onclick="return confirmx('确认要删除该录入供电情况吗？', this.href)">删除</a>
						</c:when>
						<c:otherwise></c:otherwise>

					</c:choose>


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