<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用电用户管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			var donemsg = "${message}";
			if(donemsg != ""){
				toastr.info(donemsg);
			}
	$('.collapse-table').click(function () {
			    
			    var button = $(this).find('i');
			   
			    var idstr = button.attr('trindex');
			    var content = $('#'+idstr);
			    content.slideToggle(200);
			    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
			    ibox.toggleClass('').toggleClass('border-bottom');
			    setTimeout(function () {
			        ibox.resize();
			        ibox.find('[id^=map-]').resize();
			    }, 50);
			});
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
		<li class="active"><a href="${ctx}/paramset/usercompanymng/userCompany/">用电用户管理列表</a></li>
		<shiro:hasPermission name="paramset:usercompanymng:userCompany:edit"><li><a href="${ctx}/paramset/usercompanymng/userCompany/form">用电用户管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="userCompany" action="${ctx}/paramset/usercompanymng/userCompany/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<div class="form-group">
				<label class="control-label">煤矿集团：</label>
				<div class="control-inline">
				<sys:itemselect id="colgroupid" name="colgroupid.id" value="${userCompany.colgroupid.id}" labelName="colgroupid.name" labelValue="${userCompany.colgroupid.name}"
title="煤矿集团" url="/dataprepare/groupcompany/groupCompany/listData" cssClass="required" allowClear="true" 
tablecolumn="name:集团名,power:有功总量"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">变电站：</label>
				<div class="control-inline">
				<sys:itemselect id="bdzid" name="bdzid.id" value="${userCompany.bdzid.id}" labelName="bdzid.name" labelValue="${userCompany.bdzid.name}"
title="煤矿集团" url="/paramset/substationmng/tmdlSubstation/listData" cssClass="required" allowClear="true" 
tablecolumn="name:变电站名称,code:变电站代码"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">用电单位编号：</label>
				<div class="control-inline">
				<form:input path="code" htmlEscape="false" maxlength="65" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">用电单位名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="200" class="form-control"/>
			</div>
			</div>
			
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>					
			<th style="width:10px;">&nbsp;</th>			
				<th class="sort-column a.name">用电单位名称</th>
				<th class="sort-column a.code">用电单位编号</th>
				<th>煤矿集团</th>
				<th>变电站</th>
				<th>企业代码</th>
				<th>标准照明电量</th>
				<th>电量序号</th>
				<th>用电序号</th>
				<th>电费类别</th>
				<th>比例</th>
				<th>银行托收</th>
				<th>有分配比例</th>
				<th>月度总功</th>
				<th>税率</th>
				<!-- 
				<th>更新者</th>
				<th>更新时间</th>
				 -->
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="userCompany"  varStatus="statusindex">
			<tr>
				<td>
				<c:if test="${userCompany.cgList  != null && userCompany.cgList.size()>0}">
					 <a class="collapse-table">
                         <i class="fa fa-chevron-up" trindex="company<c:out value="${statusindex.index}"/>" ></i>
                      </a>    
                      </c:if>
				</td>
				<td><a href="${ctx}/paramset/usercompanymng/userCompany/formView?id=${userCompany.id}">
					${userCompany.name}
					</a>
				</td>
				<td>
					${userCompany.code}
				</td>
				<td>
					${userCompany.colgroupid.name}
				</td>
				<td>
					${userCompany.bdzid.name}
				</td>
				<td>
					${userCompany.companyNo}
				</td>
				<td>
					${userCompany.standardIllumination}
				</td>
				<td>
					${userCompany.powercode}
				</td>
				<td>
					${userCompany.ueleccode}
				</td>
				<td>
					${fns:getDictLabel(userCompany.costcategory, 'TMDL_Electricity_fees_type', '')}
				</td>
				<td>
					${userCompany.ratio}
				</td>
				<td>
					${userCompany.tollmethod}
				</td>
				<td>
					${fns:getDictLabel(userCompany.isratio, 'yes_no', '')}
				</td>
				<td>
					${userCompany.zmyd}
				</td>
				<td>
					${userCompany.sl}
				</td>
				<!-- 
				<td>
					${userCompany.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${userCompany.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				 -->
				<td>
				<a href="${ctx}/paramset/usercompanymng/userCompany/formView?id=${userCompany.id}">详情</a>
				<shiro:hasPermission name="paramset:usercompanymng:userCompany:edit">
    				<a href="${ctx}/paramset/usercompanymng/userCompany/form?id=${userCompany.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/paramset/usercompanymng/userCompany/delete?id=${userCompany.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该用电用户管理吗？', this.href)">删除</a>
				</shiro:hasPermission>
				</td>
			</tr>
			
			<c:if test="${userCompany.cgList  != null && userCompany.cgList.size()>0}">
			<tr id ="company<c:out value="${statusindex.index}"/>" style="display:none;">
			<td colspan="18">
				<div style="overflow-x:auto;padding-left: 35px;">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>									
									<th>集团煤业名称</th>	
									<th>比例</th>
								</tr>
							</thead>
							<tbody id="cgList">
							   <c:forEach items="${userCompany.cgList}" var="cg">
							   <tr>
							   	<td>
							   		${cg.collieryname}
							   	</td>
							   		<td>
							   		
							   		${cg.ydbl}
							   		
							   	</td>
							   </tr>
							   </c:forEach>
							</tbody>
						</table>
					</div>
			</td>
			</tr>
			</c:if>
			
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