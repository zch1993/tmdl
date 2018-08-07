<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站管理管理</title>
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
		<li class="active"><a href="${ctx}/paramset/substationmng/tmdlSubstation/">变电站管理列表</a></li>
		<shiro:hasPermission name="paramset:substationmng:tmdlSubstation:edit"><li><a href="${ctx}/paramset/substationmng/tmdlSubstation/form">变电站管理添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="tmdlSubstation" action="${ctx}/paramset/substationmng/tmdlSubstation/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
			<div class="form-group">
				<label class="control-label">变电站编号：</label>
				<div class="control-inline">
				<form:input path="code" htmlEscape="false" maxlength="60" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">变电站名称：</label>
				<div class="control-inline">
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
				<form:input path="searchCompany" htmlEscape="false" maxlength="100" class="form-control"/>
			</div>
			</div>
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>	
			    <th style="width:10px;">&nbsp;</th>			
				<th  class="sort-column a.name">变电站名称</th>
				<th>变电站编码</th>
				<th>地址位置</th>
				<th>电压等级</th>
				<th>更新者</th>
				<th>更新时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="tmdlSubstation" varStatus="statusindex">
			<tr>
			    <td>
			    <c:if test="${tmdlSubstation.substationCompanyRelatioList  != null && tmdlSubstation.substationCompanyRelatioList.size()>0}">
					 <a class="collapse-table">
                         <i class="fa fa-chevron-up" trindex="company<c:out value="${statusindex.index}"/>" ></i>
                      </a>    
                      </c:if>
				</td>
				<td><a href="${ctx}/paramset/substationmng/tmdlSubstation/formView?id=${tmdlSubstation.id}">
					${tmdlSubstation.name}
				</a></td>
				<td>
					${tmdlSubstation.code}
				</td>
				<td>
					${tmdlSubstation.address}
				</td>
				<td>
					${tmdlSubstation.voltageid}
				</td>
				<td>
					${tmdlSubstation.updateBy.name}
				</td>
				<td>
					<fmt:formatDate value="${tmdlSubstation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
				<a href="${ctx}/paramset/substationmng/tmdlSubstation/formView?id=${tmdlSubstation.id}">详情</a>
				<shiro:hasPermission name="paramset:substationmng:tmdlSubstation:edit">
    				<a href="${ctx}/paramset/substationmng/tmdlSubstation/form?id=${tmdlSubstation.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/paramset/substationmng/tmdlSubstation/delete?id=${tmdlSubstation.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该变电站管理吗？', this.href)">删除</a>
				</shiro:hasPermission>
				</td>
			</tr>
			<c:if test="${tmdlSubstation.substationCompanyRelatioList  != null && tmdlSubstation.substationCompanyRelatioList.size()>0}">
			<tr id ="company<c:out value="${statusindex.index}"/>" style="display:none;">
			<td colspan="8">
					<div style="overflow-x:auto;padding-left: 35px;">
						<table id="contentTable" class="table table-striped table-bordered table-condensed">
							<thead>
								<tr>									
									<th>单位编号</th>	
									<th>单位名称</th>
								</tr>
							</thead>
							<tbody id="substationCompanyRelatioList">
							   <c:forEach items="${tmdlSubstation.substationCompanyRelatioList}" var="substationCompanyRelation">
							   <tr>
							   	<td>
							   		${substationCompanyRelation.unitid.code}
							   	</td>
							   		<td>
							   		<a href="${ctx}/paramset/usercompanymng/userCompany/formView?id=${substationCompanyRelation.unitid.id}">
							   		${substationCompanyRelation.unitid.name}
							   		</a>
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
	<div class="pagination">${page}</div>
	</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>