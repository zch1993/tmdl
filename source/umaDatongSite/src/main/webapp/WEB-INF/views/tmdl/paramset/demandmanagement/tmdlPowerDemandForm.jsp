<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>需量管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
				var donemsg = "${message}";
				if(donemsg != ""){
					toastr.info(donemsg);
				}
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					toastr.info('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					toastr.info("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});


		function loadinfo() {
		    var shuntId=$("#shuntIdId").val();
		    console.log("shuntId"+shuntId);
            toastr.warning('查询分路数据失败！');
            var icon=""
            var span="";
            $.ajax({
				type:"POST",
				url:"${ctx}/paramset/demandmanagement/tmdlPowerDemand/findfl",
				data:"id="+shuntId,
				success:function (data) {
                   $("#shuntIdName").next("#icon").remove(icon);
                    $("#shuntIdName").next("#span").remove(span);

                    if(data == ""){
                        $("#unitIdId").val("");
                        $("#unitIdName").val("");
                        $("#stationIdId").val("");
                        $("#stationIdName").val("");
                        icon = "<i id='icon' class='fa fa-times-circle' style='color: red'></i>  ";
                        span="<span id='span' style='color: red'>本分路无对应变电站和单位信息</span>";
                        $("#shuntIdName").after(icon+span);
					}else if(data != null || data != undefined){
                        $("#unitIdId").val(data.COMPANYID);
                        $("#unitIdName").val(data.COMPANYNAME);
                        $("#stationIdId").val(data.STATIONID);
                        $("#stationIdName").val(data.STATIONNAME);



					}


					
                },
				error:function (data) {
					console.log(data);
                }


			})
			
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/">需量管理列表</a></li>
		<li class="active"><a href="${ctx}/paramset/demandmanagement/tmdlPowerDemand/form?id=${tmdlPowerDemand.id}">需量管理<shiro:hasPermission name="paramset:demandmanagement:tmdlPowerDemand:edit">${not empty tmdlPowerDemand.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="paramset:demandmanagement:tmdlPowerDemand:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlPowerDemand" action="${ctx}/paramset/demandmanagement/tmdlPowerDemand/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位<font color="red">(*)</font>：</label>
			<div class="col-sm-4">

				<sys:itemselect id="unitId" name="unitId.id" value="${tmdlPowerDemand.unitId.id}" labelName="unitId.name" labelValue="${tmdlSystemCollieryGroup.unitId.name}"
								title="单位" url="/paramset/usercompanymng/userCompany/listData" cssClass="required" allowClear="true"
								tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false" disabled="true"/>
			
			</div>
			<label class="col-sm-2 control-label">变电站<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="stationId" name="stationId.id" value="${tmdlPowerDemand.stationId.id}" labelName="stationId.name" labelValue="${tmdlPowerDemand.stationId.name}"
								title="变电站" url="/paramset/substationmng/tmdlSubstation/listData" cssClass="required" allowClear="true"
								tablecolumn="name:变电站名称"  searchcolumn="name" checked="true" multiSelect="false" disabled="true"/>



			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-2 control-label">分路<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<sys:itemselect id="shuntId" name="shuntId.id" value="${tmdlPowerDemand.shuntId.id}" labelName="shuntId.name" labelValue="${tmdlPowerDemand.shuntId.name}"
								title="分路" url="/oamanagement/branchmnagement/tmdlPowerShunt/findallData" cssClass="required" allowClear="true"
								tablecolumn="name:分路名称"  searchcolumn="name" checked="true" multiSelect="false" onchangeCallback="loadinfo()"/>
			
			</div>


		<div class="form-group">
			<label class="col-sm-2 control-label">需量<font color="red">(*)</font>：</label>
			<div class="col-sm-4">
				<form:input path="demand" htmlEscape="false" maxlength="255" class="form-control required number "/>
			
			</div>
		</div>

		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="paramset:demandmanagement:tmdlPowerDemand:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
		</div>
	</form:form>
	</div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>