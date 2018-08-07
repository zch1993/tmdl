<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>矿业和集团比例分配管理</title>
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
					if(!calInputRatio())return false;
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
		
		function calInputRatio(){
			var sum = 0;
						
			 $("#cgList input[type='text']").each(function(i){
				// alert($(this).attr('id') + " | " + $(this).val());
				 if($(this).attr('id').indexOf('ydbl') <= 0)return;
				 
			    var text = $(this).val();
			   			   
			    if(text ==""){
			    	toastr.warning('请填写完整，不能留空值');
			        return false;
			    }
			    if(!isNaN(text)){
			        sum += parseFloat(text);
			    }
			     
			});
			
			if(sum == 1){
			    return true;
			}else{
				toastr.warning('比例合并值要等于1，请确认。');
				return false;
			}
		}
		
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/">矿业和集团比例分配列表</a></li>
		<li class="active"><a href="${ctx}/paramset/usercompanymng/systemCollieryGroup/form?id=${systemCollieryGroup.id}">矿业和集团比例分配<shiro:hasPermission name="paramset:usercompanymng:systemCollieryGroup:edit">${not empty systemCollieryGroup.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="paramset:usercompanymng:systemCollieryGroup:edit">查看</shiro:lacksPermission></a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="systemCollieryGroup" action="${ctx}/paramset/usercompanymng/systemCollieryGroup/save?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">用电单位<font color="red">*</font>：</label>
			<div class="col-sm-10">
			<sys:itemselect id="unitid" name="unitid.id" value="${systemCollieryGroup.unitid.id}" labelName="unitid.name" labelValue="${systemCollieryGroup.unitid.name}"
title="用电用户" url="/paramset/usercompanymng/userCompany/listData?isratio=1" cssClass="required" allowClear="true" 
tablecolumn="name:单位名称"  searchcolumn="name" checked="true" multiSelect="false"/>
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-2 control-label">矿业和集团比例分配：</label>
			<div class="col-sm-10">
				<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>煤矿企业名称<font color="red">*</font></th>	
								<th>所占比例<font color="red">*</font></th>	
								<th>备注信息</th>																
								<th width="10">&nbsp;</th>
							</tr>
						</thead>
						<tbody id="cgList">
						</tbody>
					<tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#cgList', systemCollieryGroupRowIdx, systemCollieryGroupTpl);systemCollieryGroupRowIdx = systemCollieryGroupRowIdx + 1;" class="btn btn-primary">新增行</a></td></tr>
						</tfoot>
					</table>
					<script type="text/template" id="systemCollieryGroupTpl">//<!--
						<tr id="cgList{{idx}}">
							<td class="hide">
								<input id="cgList{{idx}}_id" name="cgList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="cgList{{idx}}_unitid_id" name="cgList[{{idx}}].unitid.id" type="hidden" value="{{row.unitid.id}}"/>
							    <input id="cgList{{idx}}_delFlag" name="cgList[{{idx}}].delFlag" type="hidden" value="0"/>
							</td>
							<td>  <!-- 煤矿企业名称 -->
								<input id="cgList{{idx}}_collieryname" name="cgList[{{idx}}].collieryname" type="text" value="{{row.collieryname}}" maxlength="255" class="form-control required" />
							</td>
							<td>  <!-- 所占比例 -->
								<input id="cgList{{idx}}_ydbl" name="cgList[{{idx}}].ydbl" type="text" value="{{row.ydbl}}" maxlength="255" class="form-control number required"/>
							</td>
							<td>  <!-- 备注信 -->
								<input id="cgList{{idx}}_remarks" name="cgList[{{idx}}].remarks" type="text" value="{{row.remarks}}" maxlength="255" class="form-control"/>
							</td>
							
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#cgList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
						var systemCollieryGroupRowIdx = 0, systemCollieryGroupTpl = $("#systemCollieryGroupTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						
						function loadAddDataRow(){
							var data = ${fns:toJson(systemCollieryGroup.cgList)};
							
							for (var i=0; i<data.length; i++){
								addRow('#cgList', systemCollieryGroupRowIdx, systemCollieryGroupTpl, data[i]);
								systemCollieryGroupRowIdx = systemCollieryGroupRowIdx + 1;
								
							}	
								
						}
					</script>
	</div>		
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="paramset:usercompanymng:systemCollieryGroup:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
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