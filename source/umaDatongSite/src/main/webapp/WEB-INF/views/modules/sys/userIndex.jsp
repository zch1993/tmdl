<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<style type="text/css">
		
	</style>
</head>
<body>
	<sys:message content="${message}"/>
	
	<div class="wrapper wrapper-content">
        <div class="row animated fadeInRight">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                   <h5> 组织机构</h5>
                    		<div class="ibox-tools">
                        <a class="accordion-toggle"><i class="fa fa-refresh" onclick="refreshTree();"></i></a>
                    </div>    
                    </div>
                    <div class="ibox-content">
                        
			<div id="ztree" class="ztree"></div>
                    </div>
                </div>
            </div>
            <div class="col-sm-9">
                <div class="ibox float-e-margins">
                    
                    <div class="ibox-content">

                        
                        <iframe id="officeContent" src="${ctx}/sys/user/list" width="100%" frameborder="0"></iframe>
<script type="text/javascript">
function reinitIframe(){
var iframe = document.getElementById("officeContent");
try{
var bHeight = iframe.contentWindow.document.body.scrollHeight;
var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
var height = Math.max(bHeight, dHeight);
iframe.height = height;
console.log(height);
}catch (ex){}
}
window.setInterval("reinitIframe()", 200);
</script>

                    </div>
                </div>

            </div>
        </div>
    </div>
    
	<!-- ============================================================ -->
	
	<script type="text/javascript">
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
			callback:{onClick:function(event, treeId, treeNode){
					var id = treeNode.id == '0' ? '' :treeNode.id;
					$('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name);
				}
			}
		};
		
		function refreshTree(){
			$.getJSON("${ctx}/sys/office/treeData",function(data){
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
			});
		}
		refreshTree();
		 
		
	</script>
	
</body>
</html>