<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>矿业和集团比例分配管理</title>
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
                /*ibox.toggleClass('').toggleClass('border-bottom');*/
                setTimeout(function () {
                   /* ibox.resize();
                    ibox.find('[id^=map-]').resize();*/
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
		<li class="active"><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/">矿业和集团比例分配列表</a></li>
		<shiro:hasPermission name="paramset:usercompanymng:systemCollieryGroup:edit"><li><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/form">矿业和集团比例分配添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="searchForm" modelAttribute="systemCollieryGroup" action="${ctx}/paramset/usercompanymng/systemCollieryGroup/" method="post"  class="form-inline">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		
		<div class="form-group">
				<label class="control-label">单位名称：</label>
				<div class="control-inline">
				<form:input path="unitid.name" htmlEscape="false" maxlength="10" class="form-control"/>
			</div>
			</div>
			<div class="form-group">
				<label class="control-label">煤矿企业名称：</label>
				<div class="control-inline">
				<form:input path="collieryname" htmlEscape="false" maxlength="100" class="form-control"/>
			</div>
			</div>
			
			<div class="form-group"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></div>
			
	</form:form>
			
	<div class="hr-line-dashed"></div>
	<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>单位名称</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="systemCollieryGroup"  varStatus="statusindex">
			<tr>

				<td>
					<c:if test="${systemCollieryGroup.cgList  != null && systemCollieryGroup.cgList.size()>0}">
						<a class="collapse-table">
							<i class="fa fa-chevron-up" trindex="company<c:out value="${statusindex.index}"/>" ></i>
						</a>
					</c:if>
				</td>
			<td><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/formView?id=${systemCollieryGroup.id}">
					${systemCollieryGroup.unitid.name}</a>
				</td>

				<td>
				<a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/formView?id=${systemCollieryGroup.id}">详情</a>
				<shiro:hasPermission name="paramset:usercompanymng:systemCollieryGroup:edit">
    				<a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/form?id=${systemCollieryGroup.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>">修改</a>
					<a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/delete?id=${systemCollieryGroup.id}<c:if test="${not empty searchUrlParam}">&searchUrlParam=${searchUrlParam}</c:if>" onclick="return confirmx('确认要删除该矿业和集团比例分配吗？', this.href)">删除</a>
				</shiro:hasPermission>
				</td>
			</tr>


			<c:if test="${systemCollieryGroup.cgList  != null && systemCollieryGroup.cgList.size()>0}">
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
								<c:forEach items="${systemCollieryGroup.cgList}" var="cg">
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