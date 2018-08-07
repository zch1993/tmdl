<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配角色</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/role/">角色列表</a></li>
		<li class="active"><a href="${ctx}/sys/role/assign?id=${role.id}"><shiro:hasPermission name="sys:role:edit">角色分配</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">人员列表</shiro:lacksPermission></a></li>
	</ul>
		<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    <form:form id=""  class="form-inline">

		
			<div class="form-group"><div class="control-inline"><span class="span4">角色名称: <b>${role.name}</b></span></div></div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="form-group"><div class="control-inline"><span class="span4">归属机构: ${role.office.name}</span></div></div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="form-group"><div class="control-inline"><span class="span4">英文名称: ${role.enname}</span></div></div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		
			<div class="form-group"><span class="span4">角色类型: ${role.roleType}</span></div>
			&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="form-group"><c:set var="dictvalue" value="${role.dataScope}" scope="page" />
			<span class="span4">数据范围: ${fns:getDictLabel(dictvalue, 'sys_data_scope', '')}</span></div>
		
	
	</form:form>
	
	<div class="hr-line-dashed"></div>
	<sys:message content="${message}"/>
	<div class="breadcrumb">
		<form id="assignRoleForm" action="${ctx}/sys/role/assignrole" method="post" class="hide">
			<input type="hidden" name="id" value="${role.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn btn-primary" type="submit" value="分配角色"/>
		<script type="text/javascript">
			$("#assignButton").click(function(){
				
				layer.open({
					  type: 2,
					  maxmin: true,
					  shadeClose: true,
						title: '分配角色',
					  area: ['800px', '450px'],
					  fixed: false, //不固定
					  
					  content: "${ctx}/sys/role/usertorole?id=${role.id}",
					  success: function(layero, index){
							//var info = '<font color="red" class="pull-left mt10">提示：双击选择图标。</font>';
							//layero.find('.layui-layer-btn').append(info);
						},
						btn: ['<i class="fa fa-close"></i> 确定分配',
							'<i class="fa fa-close"></i> 关闭',
							'<i class="fa fa-eraser"></i> 清除已选'],
						yes: function(index, layero){
								var pre_ids = layero.find("iframe")[0].contentWindow.pre_ids;
								var ids = layero.find("iframe")[0].contentWindow.ids;
								// 删除''的元素
								if(ids[0]==''){
									ids.shift();
									pre_ids.shift();
								}
								if(pre_ids.sort().toString() == ids.sort().toString()){
									toastr.warning("未给角色【${role.name}】分配新成员！");
									return false;
								};
						    	// 执行保存
						    	toastr.info('正在提交，请稍等...');
						    	var idsArr = "";
						    	for (var i = 0; i<ids.length; i++) {
						    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
						    	}
						    	$('#idsArr').val(idsArr);
						    	$('#assignRoleForm').submit();
							},
						
							btn3: function(index, layero){
								layero.find("iframe")[0].contentWindow.clearAssign();
								return false;
							}
					});
				
				
			});
		</script>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>归属公司</th><th>归属部门</th><th>登录名</th><th>姓名</th><th>电话</th><th>手机</th><shiro:hasPermission name="sys:user:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${userList}" var="user">
			<tr>
				<td>${user.company.name}</td>
				<td>${user.office.name}</td>
				<td><a href="${ctx}/sys/user/form?id=${user.id}">${user.loginName}</a></td>
				<td>${user.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
				<shiro:hasPermission name="sys:role:edit"><td>
					<a href="${ctx}/sys/role/outrole?userId=${user.id}&roleId=${role.id}" 
						onclick="return confirmx('确认要将用户<b>[${user.name}]</b>从<b>[${role.name}]</b>角色中移除吗？', this.href)">移除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>
