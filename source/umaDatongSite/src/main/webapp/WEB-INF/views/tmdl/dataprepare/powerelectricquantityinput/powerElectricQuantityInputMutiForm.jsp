<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>月度分路表底录入管理</title>
	<meta name="decorator" content="default"/>

	<script type="text/javascript">
	$(document).ready(function() {
		//$("#name").focus();
		addRow('#inputList', powerElectricQuantityInputRowIdx, powerElectricQuantityInputTpl);
		powerElectricQuantityInputRowIdx = powerElectricQuantityInputRowIdx + 1;
		
		
		$("#inputForm").validate({
			submitHandler: function(form){
				toastr.info('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
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
	
	function getFenlu(obj,prefix){
		
		//toastr.info('查询分路数据...');
		var url = "${ctx}/oamanagement/branchmnagement/tmdlPowerShunt/listData?kgbh=" + $("#" +prefix+"_shuntId_kgbh").val();
		$.get(url, function(datas){	
			if(typeof datas.list == "object"){	
				if(datas.list.length>0){
					$("#" +prefix+"_shuntId_name").html(datas.list[0].name);
					$("#" +prefix+"_shuntId_id").val(datas.list[0].id);			
					//toastr.info('查询分路数据成功');
				}else{
					$("#" +prefix+"_shuntId_name").html("<font color='red'>错误编号</font>");
					toastr.warning('查询分路数据失败！');
				}
			}else{
				$("#" +prefix+"_shuntId_name").html("<font color='red'>错误编号</font>");
				toastr.warning('查询分路数据失败！');
			}
		});
	}
</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li ><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/">月度分路表底录入列表</a></li>
		<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit"><li class="active"><a href="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/mutiformView">月度分路表底录入批量添加</a></li></shiro:hasPermission>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
    <form:form id="inputForm" modelAttribute="powerElectricQuantityInput" action="${ctx}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/mutiSave?searchUrlParam=${searchUrlParam}" method="post" class="form-horizontal">                
	
	<div class="form-group">			
		
			<label class="col-sm-1 control-label">月度<font color="red">(*)</font>：</label>
			<div class="col-sm-11">
				<input name="time" type="text" readonly="readonly" maxlength="20" class="form-control Wdate required"
					value="<fmt:formatDate value="${powerElectricQuantityInput.time}" pattern="yyyy-MM"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM',isShowClear:false});"/>
			
			</div>
		</div>
					
<div style="overflow-x:auto;">
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
						<thead>
							<tr>
								<th class="hide"></th>
								<th>分路编号<font color="red">*</font></th>
								<th>分路名称</th>
								<th>变电站</th>
								<th>有功总量<font color="red">*</font></th>
								<th>峰段总量<font color="red">*</font></th>
								<th>谷段总量<font color="red">*</font></th>
								<th>平段总量<font color="red">*</font></th>
								<th>正有功总量</th>
								<th>反有功总量</th>
								<th>无功总量</th>
								<th>反无功总量</th>
								<th width="10">&nbsp;</th>
							</tr>
						</thead>
						<tbody id="inputList">
						</tbody>
					<tfoot>
							<tr><td colspan="12"><a href="javascript:" onclick="addRow('#inputList', powerElectricQuantityInputRowIdx, powerElectricQuantityInputTpl);powerElectricQuantityInputRowIdx = powerElectricQuantityInputRowIdx + 1;" class="btn btn-primary">新增行</a></td></tr>
						</tfoot>
					</table>
					<script type="text/template" id="powerElectricQuantityInputTpl">//<!--
						<tr id="inputList{{idx}}">
							<td class="hide">
								<input id="inputList{{idx}}_id" name="inputList[{{idx}}].id" type="hidden" value="{{row.id}}"/>
								<input id="inputList{{idx}}_shuntId_id" name="inputList[{{idx}}].shuntId.id" type="hidden" value="{{row.shuntId.id}}"/>
							
							</td>
							<td>  <!-- 分路编号 -->
								<input id="inputList{{idx}}_shuntId_kgbh" name="inputList[{{idx}}].shuntId.kgbh" type="text" value="{{row.shuntId.kgbh}}" maxlength="255" class="form-control required" onchange="javascirpt:getFenlu(this,'inputList{{idx}}');"/>
							</td>
							<td id="inputList{{idx}}_shuntId_name">  <!-- 分路名称 -->
								
							</td>
							<td  id="inputList{{idx}}_bdz_name"><!--变电站 -->
								
							</td>
							<td> <!-- 有功总量 -->
								<input id="inputList{{idx}}_wattfulgross" name="inputList[{{idx}}].wattfulgross" type="text" value="{{row.wattfulgross}}" class="form-control "/>
							</td>
								<td> <!-- 峰段总量 -->
								<input id="inputList{{idx}}_peaksegment" name="inputList[{{idx}}].peaksegment" type="text" value="{{row.peaksegment}}" class="form-control "/>
							</td>
							<td> <!-- 谷段总量 -->
							<input id="inputList{{idx}}_grainsegment" name="inputList[{{idx}}].grainsegment" type="text" value="{{row.grainsegment}}" class="form-control "/>
							</td>
							<td> <!-- 平段总量-->
							<input id="inputList{{idx}}_flatsegment" name="inputList[{{idx}}].flatsegment" type="text" value="{{row.flatsegment}}" class="form-control "/>
							</td>
							<td> <!-- 正有功总量 -->
							<input id="inputList{{idx}}_pluswatt" name="inputList[{{idx}}].pluswatt" type="text" value="{{row.pluswatt}}" class="form-control "/>
							</td>
							<td> <!-- 反有功总量 -->
							<input id="inputList{{idx}}_resewatt" name="inputList[{{idx}}].resewatt" type="text" value="{{row.resewatt}}" class="form-control "/>
							</td>
							<td> <!-- 无功总量 -->
							<input id="inputList{{idx}}_idleroll" name="inputList[{{idx}}].idleroll" type="text" value="{{row.idleroll}}" class="form-control "/>
							</td>
							<td> <!-- 反无功总量 -->
							<input id="inputList{{idx}}_antiReactivePower" name="inputList[{{idx}}].antiReactivePower" type="text" value="{{row.antiReactivePower}}" class="form-control "/>
							</td>
							<td class="text-center" width="10">
								{{#delBtn}}<span class="close" onclick="delRow(this, '#inputList{{idx}}')" title="删除">&times;</span>{{/delBtn}}
							</td>
						</tr>//-->
					</script>
					<script type="text/javascript">
					var powerElectricQuantityInputRowIdx = 0, powerElectricQuantityInputTpl = $("#powerElectricQuantityInputTpl").html().replace(/(\/\/\<!\-\-)|(\/\/\-\->)/g,"");
						
						function loadAddDataRow(){
							
								
						}
					</script>
	</div>		
	<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">
			<shiro:hasPermission name="dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
			
	</form:form>

	</div>
			</div>
		</div>
	</div>
	</div>
</body>
</html>