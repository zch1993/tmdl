<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>数据选择</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
	var selectDataId = [];
	var selectDataName = [];
	
	var columnamefeild = "";
	
     
		$(document).ready(function(){
			document.getElementById("selectedid").value=window.parent.document.getElementById("${eleid}Id").value;			
	      document.getElementById("selectedname").value=window.parent.document.getElementById("${eleid}Name").value;
	         
	      var idvalues = document.getElementById("selectedid").value.split(",");
			 for(var i=0;i<idvalues.length;i++){
				 selectDataId.push(idvalues[i]);
			 }
			 idvalues = document.getElementById("selectedname").value.split(",");
			 for(var i=0;i<idvalues.length;i++){
				 selectDataName.push(idvalues[i]);
			 }
			search("");
						
			
			//setTimeout("search();", "300");
		});
		
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			searchData(0);       
        }
		
		function checktable(){
			 
			$("#TablePagination").bootstrapTable("checkBy", 
					{field:"id", values:selectDataId}) ;
		}
		
		function isItemExist(datas,value){
			 var checkboxsel = "${multiSelect}";
			    if(checkboxsel != "true"){
			    	selectDataId.splice(0,selectDataId.length);
					selectDataName.splice(0,selectDataName.length);
			    	return -1;
			    }
			    	
			for(var i=0;i<datas.length;i++){
				if(datas[i] == value)
					return i;
			}
			return -1;
		}
		
		function searchData(btnclk){
			
			//var searchkey = "&${searchcolumn}="+document.getElementById("searchData").value;//("#searchData").val;
			//var pageNo = "&pageNo="+document.getElementById("pageNo").value;//$('#pageNo').val;
			//var pageSize = "&pageSize="+document.getElementById("pageSize").value;//$('#pageSize').val;
			//if(btnclk)pageNo="&pageNo=1";
			////search(searchkey + pageNo + pageSize);
			var url = "${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}&extId=${extId}&isAll=${isAll}&t="
				+ new Date().getTime()+"&checked=${checked}&multiSelect=${multiSelect}&extId=${extId}&isAll=${isAll}&tablecolumn=${tablecolumn}&searchcolumn=${searchcolumn}";
				
				//	url = url + searchkey + pageNo + pageSize;
				//alert(url);
				//$.get(url, function(zNodes){	
				//	//alert(JSON.stringify(zNodes));
				//	$('#pageNo').val(zNodes.pageNo);
				//	$('#pageSize').val(zNodes.pageSize);	
				//	$('#pagination').html(zNodes.html);	
				//	$("#TablePagination").bootstrapTable('load', zNodes.list);
				//});
				
				var pageNo = document.getElementById("pageNo").value;
				if(btnclk)pageNo="1";
				$.post(url,  { ${searchcolumn}: document.getElementById("searchData").value, 
					pageNo: pageNo,
					pageSize:document.getElementById("pageSize").value},function(zNodes){	
					//alert(JSON.stringify(zNodes));
					$('#pageNo').val(zNodes.pageNo);
					$('#pageSize').val(zNodes.pageSize);	
					$('#pagination').html(zNodes.html);	
					$("#TablePagination").bootstrapTable('load', zNodes.list);
					checktable();
				});
		}
		
		// 开始搜索
		function search(urlparam) {
			var url = "${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}&extId=${extId}&isAll=${isAll}&t="
				+ new Date().getTime()+"&checked=${checked}&multiSelect=${multiSelect}&extId=${extId}&isAll=${isAll}&tablecolumn=${tablecolumn}&searchcolumn=${searchcolumn}";
				if(urlparam !=""){
					url = url + urlparam;
				}
			//	alert(url);
				
				
			$.get(url, function(zNodes){				
				//alert(JSON.stringify(arr3));
						$('#pageNo').val(zNodes.pageNo);
						$('#pageSize').val(zNodes.pageSize);						
						$('#TablePagination').bootstrapTable(
								{data: zNodes.list,
									idField:"id",
									clickToSelect:true,
									onCheckAll:function(rows){//全选中
										for(var i=0;i<rows.length;i++){
											var index = isItemExist(selectDataId,rows[i]['id']);
											if(index == -1){
												selectDataId.push(rows[i]['id']);
												selectDataName.push(rows[i][columnamefeild]);
											}											
										}
										selecttableitem();
							          
							          },
							          onUncheckAll:function(rows){//全选不中
							        	  //document.getElementById("selectedid").value=""; 
							        	  //document.getElementById("selectedname").value="";
							        	  for(var i=0;i<rows.length;i++){
							        		  var index = isItemExist(selectDataId,rows[i]['id']);
												if(index >=0){
													selectDataId.splice(index,1);
													selectDataName.splice(index,1);
												}	
											}
							        	  selecttableitem();
							          },
							          onUncheck:function(row){//不选中
							        	  var index = isItemExist(selectDataId,row['id']);
											if(index >=0){
												selectDataId.splice(index,1);
												selectDataName.splice(index,1);
											}	
											selecttableitem();
							          },
							          onCheck:function(row){//选中
							        	  var index = isItemExist(selectDataId,row['id']);
											if(index == -1){
												selectDataId.push(row['id']);
												selectDataName.push(row[columnamefeild]);
											}	
											selecttableitem();
							          },
							          onDblClickRow:function(row, element){//选中
							        	  var mutisel = "${multiSelect}";
							        	  if(mutisel == "true"){//donothing
							        		  }
							        	  else{							        		 
							        		  //$('a.layui-layer-btn0', parent.document).click();//trigger("click");
							        		  //alert($('a.layui-layer-btn0', parent.document).html());
							        		  window.parent.clicklayer${eleid}${extId}();
							        		  //$('a.layui-layer-btn0', parent.document).parent.trigger("click");
							        	  }
							          }
									});
						$('#TablePagination').bootstrapTable('hideColumn', 'id');
						$('#pagination').html(zNodes.html);			
						checktable();
				
			});
		}
		
		function selecttableitem(){
			
			document.getElementById("selectedid").value=selectDataId.toString();
         document.getElementById("selectedname").value=selectDataName.toString();
         var checkboxsel = "${multiSelect}";
		    if(checkboxsel == "true"){
		    	
		    }else{
		    	document.getElementById("selectedid").value=selectDataId.toString().replace(",","");
		    	document.getElementById("selectedname").value=selectDataName.toString().replace(",","");
		    }
		}
		
	</script>
</head>
<body>
	
	<div id="search" class="form-group" >
				
		<div class="input-group">
         <input type="text" class="form-control" id="searchData" name="searchData" maxlength="50" > <span class="input-group-btn"> <button id="btn" onclick="searchData(1)" type="button" class="btn btn-primary">搜索
         </button> </span>
      </div>
	</div>
	
	<table id="TablePagination" >
       <thead >
           <tr>
              
               <th data-field="id"  data-mobile-responsive="true">ID</th>
                 
               	<script type="text/javascript">
              			var checked = "${checked}"; 
              			if(checked !=""){
              				    var checkboxsel = "${multiSelect}";
              				    if(checkboxsel == "true")
              						document.write("<th data-field=\"state\" data-checkbox=\"true\"></th>");
              				    else
              				  	document.write("<th data-field=\"state\"  data-radio=\"true\"></th>");
               				}
             			var tablecolumn = decodeURI("${tablecolumn}");
          			        var tablelolumns = tablecolumn.split(",");
          			       //alert(tablecolumn);
          			     for (var i in tablelolumns) {
          			    	 var columnname = tablelolumns[i].split(":");
          			     	 document.write("<th data-field=\"" + columnname[0] + "\" >");            			     	
          			   	 document.write(columnname[1]);
          			     	 document.write("</th>");
          			     	 if(i == 0)columnamefeild = columnname[0];
             						}
               	</script>
           </tr>
       </thead>
   </table>
   <input id="selectedid" value=""  type="hidden" /> <input id="selectedname" value=""  type="hidden" />
   <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
	<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
   <div class="pagination" id="pagination">
   
   </div>
	
</body>