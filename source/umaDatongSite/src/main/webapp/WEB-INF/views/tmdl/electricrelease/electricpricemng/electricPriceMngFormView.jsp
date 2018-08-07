<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>电价管理管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
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
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/">电价管理列表</a></li>
		<li class="active"><a href="${ctx}/electricrelease/electricpricemng/electricPriceMng/formView?id=${electricPriceMng.id}">电价管理查看</a></li>
	</ul>
	<div class="wrapper wrapper-content animated fadeInRight">
	 <div class="row">
            <div class="col-sm-12">
                <div class="float-e-margins">                    
                    <div class="ibox-content">
                    
	<form:form id="inputForm" modelAttribute="electricPriceMng" action="${ctx}/electricrelease/electricpricemng/electricPriceMng/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="form-group">
			<label class="col-sm-2 control-label">单位名称：</label>
			<div class="col-sm-10">
			${electricPriceMng.unitid.name}
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平段用电单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.pingp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">峰段用电单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.fengp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">谷段用电单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.gup	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">动力用电单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.dlp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">照明单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.zmp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">需量单价：</label>
			<div class="col-sm-10">
				${electricPriceMng.xlp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">外转供电价：</label>
			<div class="col-sm-10">
				${electricPriceMng.wzgp	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">税率：</label>
			<div class="col-sm-10">
				${electricPriceMng.taxrate	}			
			
			</div>
		</div>
		
		
		<div class="form-group">
			<label class="col-sm-2 control-label">税率：</label>
			<div class="col-sm-10">
				${electricPriceMng.sl	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">平价：</label>
			<div class="col-sm-10">
				${electricPriceMng.flatprice	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注信息：</label>
			<div class="col-sm-10">
				${electricPriceMng.remarks	}
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">操作员：</label>
			<div class="col-sm-10">
				${electricPriceMng.updateBy.name	}			
			
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">操作日期：</label>
			<div class="col-sm-10">
				<fmt:formatDate value="${electricPriceMng.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>		
			
			</div>
		</div>
		<div class="hr-line-dashed"></div>
		<div class="form-group"><div class="col-sm-4 col-sm-offset-2">			
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