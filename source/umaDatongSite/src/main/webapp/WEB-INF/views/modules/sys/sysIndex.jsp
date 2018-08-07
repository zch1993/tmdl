<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/>
	
</head>
<body class="fixed-sidebar full-height-layout gray-bg fixed-nav" style="overflow:hidden">

<div id="wrapper">
        <!--左侧导航开始-->
       
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    
                    
                    		
                    		
								<c:forEach items="${fns:getMenuList()}" var="menu" varStatus="idxStatus">
									<c:if test="${menu.parent.id eq '1' && menu.isShow eq '1'}">			
									<li>							
											<c:if test="${empty menu.href}">	<a  href="#" ><i class="fa fa-${menu.icon}"></i><span class="nav-label">${menu.name}</span><span class="fa arrow"></span></a>	</c:if>
											<c:if test="${not empty menu.href}"><a class="J_menuItem"  href="${fn:indexOf(menu.href, '://') eq -1 ? ctx : ''}${menu.href}" ><i class="fa fa-${menu.icon}"></i><span class="nav-label">${menu.name}</span></a></c:if>			
											<c:set var="ulnoprint1" value="true"/>
											<c:forEach items="${fns:getMenuList()}" var="menusub" varStatus="idxStatussub">
												<c:if test="${menusub.parent.id == menu.id && menusub.isShow eq '1'}">
													<c:if test="${ulnoprint1}"><ul class="nav nav-second-level"></c:if>														
		                            				<li>
			                            				<c:if test="${empty menusub.href}"><a  href="#" ><i class="fa fa-${menusub.icon}"></i>${menusub.name}<span class="fa arrow"></span></a></c:if>
																<c:if test="${not empty menusub.href}"><a class="J_menuItem"  href="${fn:indexOf(menusub.href, '://') eq -1 ? ctx : ''}${menusub.href}" ><i class="fa fa-${menusub.icon}"></i> <span class="nav-label">${menusub.name}</span></a></c:if>	
			                            				<c:set var="ulnoprint2" value="true"/>
			                            				<c:forEach items="${fns:getMenuList()}" var="menusub3" varStatus="idxStatussub2">
																	<c:if test="${menusub3.parent.id == menusub.id && menusub3.isShow eq '1'}">
																		<c:if test="${ulnoprint2}"><ul class="nav nav-third-level"></c:if>
							                            				<li>
								                            				<c:if test="${empty menusub3.href}"><a  href="#" ><i class="fa fa-${menusub3.icon}"></i>${menusub3.name}<span class="fa arrow"></span></a></c:if>
																					<c:if test="${not empty menusub3.href}"><a class="J_menuItem"  href="${fn:indexOf(menusub3.href, '://') eq -1 ? ctx : ''}${menusub3.href}" > <i class="fa fa-${menusub3.icon}"></i>${menusub3.name}</a></c:if>	
							                            				</li>
							                        			
							                        			<c:set var="ulnoprint2" value="false"/>
																	</c:if>														
																</c:forEach>	<c:if test="${!ulnoprint2}"></ul></c:if>
		                            				</li>		                            				
		                        			
		                        			<c:set var="ulnoprint1" value="false"/>
												</c:if>										
											</c:forEach>	 <c:if test="${!ulnoprint1}"></ul></c:if>
										 </li>						
									</c:if>
								</c:forEach>
                   
                   <li>


                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-fixed-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                       
                                                         
                                <h2 style="margin-top:15px;">${fns:getConfig('productName')}</h2>
                           
                     
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle " data-toggle="dropdown" href="#" aria-expanded="false">
                                <img class="img-circle" style="heigth:22px;width:22px; border:0;" src="${fns:getUser().photo}" /> 您好, ${fns:getUser().name}<b class="caret"></b>
                            </a>
                            <ul class="dropdown-menu ">
                                 <li><a class="J_menuItem" href="${ctx}/sys/user/info">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="${ctx}/sys/user/modifyPwd">修改密码</a>
                                </li>
                                
                                <li class="divider"></li>
                                <li><a href="${ctx}/logout">安全退出</a>
                                </li>
                                
                            </ul>
                        </li>
                        
                        
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="${ctx}/tmdl/_home/Home">首页</a>
                    </div>
                </nav>
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
               
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx}/tmdl/_home/Home" frameborder="0" data-id="${ctx}/tmdl/_home/Home" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">技术支持:青岛正有源机电设备有限公司
                </div>
            </div>
        </div>
        <!--右侧部分结束-->   
       
    </div>
    
	
</body>
</html>