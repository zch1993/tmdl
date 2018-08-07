<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>变电站管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			loadAddDataRow();
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
		
		function addRow(list, idx, tpl, row){
			
			$(list).append(Mustache.render(tpl, {
				idx: idx, delBtn: true, row: row
			}));
			$(list+idx).find("select").each(function(){
				$(this).val($(this).attr("data-value"));
			});
			$(list+idx).find("input[type='checkbox'], input[type='radio']").each(function(){
				var ss = $(this).attr("data-value").split(',');
				for (var i=0; i<ss.length; i++){
					if($(this).val() == ss[i]){
						$(this).attr("checked","checked");
					}
				}
			});
		}
		function delRow(obj, prefix){
			var id = $(prefix+"_id");
			var delFlag = $(prefix+"_delFlag");
			if (id.val() == ""){
				$(obj).parent().parent().remove();
			}else if(delFlag.val() == "0"){
				delFlag.val("1");
				$(obj).html("&divide;").attr("title", "撤销删除");
				$(obj).parent().parent().addClass("error");
			}else if(delFlag.val() == "1"){
				delFlag.val("0");
				$(obj).html("&times;").attr("title", "删除");
				$(obj).parent().parent().removeClass("error");
			}
		}
		
		function getCompany(flag,obj,prefix){
			var searchstr = "";
			if(flag == 'name'){
				searchstr = "name=" + $("#" +prefix+"_unitid_name").val();
			}
			else{
				searchstr = "code=" + $("#" +prefix+"_unitid_code").val();
			}
			var url = "${ctx}/paramset/usercompanymng/userCompany/listData?" + searchstr;
			
			$.get(url, function(datas){	
				if(typeof datas.list == "object"){	
					if(datas.list.length>0){
						$("#" +prefix+"_unitid_code").val(datas.list[0].code);
						$("#" +prefix+"_unitid_name").val(datas.list[0].name);
						$("#" +prefix+"_unitid_id").val(datas.list[0].id);			
						
					}else{
						//$("#" +prefix+"_unitid_name").html("<font color='red'>错误编号</font>");
						toastr.warning('查询单位数据失败！');
					}
				}else{
					//$("#" +prefix+"_unitid_name").html("<font color='red'>错误编号</font>");
					toastr.warning('查询单位数据失败！');
				}
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/paramset/substationmng/tmdlSubstation/">变电站管理列表</a></li>
		<li class="active"><a href="${ctx}/paramset/substationmng/tmdlSubstation/form?id=${tmdlSubstation.id}">变电站管理<shiro:hasPermission name="paramset:substationmng:tmdlSubstation:edit">${not empty tmdlSubstation.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="paramset:substationmng:tmdlSubstation:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="tmdlSubstation" action="${ctx}/paramset/substationmng/tmdlSubstation/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		
		<div class="form-group">
			<label class="col-sm-2 control-label">变电站名称<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="name" htmlEscape="false" maxlength="100" class="form-control required"/>
			
			</div>
	
			<label class="col-sm-2 control-label">变电站编号<font color="red">*</font>：</label>
			<div class="col-sm-4">
				<form:input path="code" htmlEscape="false" maxlength="60" class="form-control required"/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">地址位置：</label>
			<div class="col-sm-4">
				<form:input path="address" htmlEscape="false" maxlength="100" class="form-control"/>
			
			</div>
	
			<label class="col-sm-2 control-label">电压等级：</label>
			<div class="col-sm-4">
				<form:input path="voltageid" htmlEscape="false" maxlength="60" class="form-control "/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="form-control "/>
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位：</label>
			<div class="col-sm-10">
				<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>单位编号<font color="red">*</font></th>	
								<th>单位名称<font color="red">*</font></th>																
								<th width="10">&nbsp;</th>
							</tr>
						</thead>
						<tbody id="substationCompanyRelatioList">
						</tbody>
					<tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#substationCompanyRelatioList', substationCompanyRelationRowIdx, substationCompanyRelationTpl);substationCompanyRelationRowIdx = substationCompanyRelationRowIdx + 1;" class="btn btn-primary">新增行</a></td></tr>
						</tfoot>
					</table>
					<script type="text/template" id="substationCompanyRelationTpl">//<!--
						<tr id="substationCompanyRelatioList{{idx}}">
							<td class="hide">
								<input id="substationCompanyRelatioList{{idx}}_id" name="substationCompanyRelatioList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="substationCompanyRelatioList{{idx}}_unitid_id" name="substationCompanyRelatioList[{{idx}}].unitid.id" type="hidden" value="{{row.unitid.id}}"/>
							    <input id="substationCompanyRelatioList{{idx}}_delFlag" name="substationCompanyRelatioList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>  <!-- 单位编号 -->
								<input id="substationCompanyRelatioList{{idx}}_unitid_code" name="substationCompanyRelatioList[{{idx}}].unitid.code" type="text" value="{{row.unitid.code}}" maxlength="255" class="form-control required" onchange="javascirpt:getCompany('code',this,'substationCompanyRelatioList{{idx}}');"/>
							</td>
							<td>  <!-- 单位名称 -->
								<input id="substationCompanyRelatioList{{idx}}_unitid_name" name="substationCompanyRelatioList[{{idx}}].unitid.name" type="text" value="{{row.unitid.name}}" maxlength="255" class="form-control required" onchange="javascirpt:getCompany('name',this,'substationCompanyRelatioList{{idx}}');"/>
							</td>
							
							
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#substationCompanyRelatioList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var substationCompanyRelationRowIdx = 0, substationCompanyRelationTpl = $("#substationCompanyRelationTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						
						function loadAddDataRow(){
							var data = ${fns:toJson(tmdlSubstation.substationCompanyRelatioList)};
							
							for (var i=0; i<data.length; i++){
								addRow('#substationCompanyRelatioList', substationCompanyRelationRowIdx, substationCompanyRelationTpl, data[i]);
								substationCompanyRelationRowIdx = substationCompanyRelationRowIdx + 1;
								
							}	
								
						}
					</script>
	</div>				
			</div>
		</div>
		
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="paramset:substationmng:tmdlSubstation:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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