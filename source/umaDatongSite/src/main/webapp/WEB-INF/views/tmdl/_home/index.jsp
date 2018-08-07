<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>首页</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	$(document).ready(function(){
		//折叠ibox
		$('.collapse-link').click(function () {
		    var ibox = $(this).closest('div.ibox');
		    var button = $(this).find('i');
		    var content = ibox.find('div.ibox-content');
		    content.slideToggle(200);
		    button.toggleClass('fa-chevron-up').toggleClass('fa-chevron-down');
		    ibox.toggleClass('').toggleClass('border-bottom');
		    setTimeout(function () {
		        ibox.resize();
		        ibox.find('[id^=map-]').resize();
		    }, 50);
		});
	});
	
	</script>
	 <style type="text/css">
	.weather {
    background: url("${ctxStatic}/Hplus-v.4.1.0/img//weather.png") no-repeat;
        background-size: auto auto;
    background-size: 100% 100%;
    width: 100%;
    height: 230px;
    margin-bottom: 10px;
}
</style>
</head>
<body>
<div class="wrapper wrapper-content">
<div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            
                            <div class="ibox-content weather">
                                	<iframe name="weather_inc" src="http://i.tianqi.com/index.php?c=code&id=2&num=5" width="650" height="70" frameborder="0" marginwidth="0" marginheight="0" scrolling="no"></iframe>
		          		</iframe>
                        </div>
                    </div>
 		</div>
    </div>
<div class="row">
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        
                        <h5>安全运行</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins"><SCRIPT LANGUAGE="JavaScript">
								var s1 = '2016-05-12';
								s1 = new Date(s1.replace(/-/g, "/"));
								s2 = new Date();//当前日期：
								var days = s2.getTime() - s1.getTime();
								var time = parseInt(days / (1000 * 60 * 60 * 24));
								document.write(time+"天");
							</SCRIPT>
						</h1>
                       <div class="stat-percent">2016-05-12开始
                        </div>
                        <small>&nbsp;</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-info pull-right">全站</span>
                        <h5>变电站数</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${substationCount} </h1>
                       <div class="stat-percent ">总量
                        </div>
                        <small>&nbsp;</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-primary pull-right">全站</span>
                        <h5>电费用户</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${userCompanyCount}</h1>
                       <div class="stat-percent"><script language="javascript">var oDate=new Date();document.write(oDate.getFullYear()+"-"+oDate.getMonth()+"-"+oDate.getDate());</script>
                        </div>
                         <small>&nbsp;</small>
                    </div>
                </div>
            </div>
            <div class="col-sm-3">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <span class="label label-danger pull-right">3分钟内活动</span>
                        <h5>在线活动用户</h5>
                    </div>
                    <div class="ibox-content">
                        <h1 class="no-margins">${activeUsers}</h1>
                        <div class="stat-percent ">
                        <script language="javascript">
                       		 var myDate=new Date();
                       		 document.write(myDate.getFullYear()+"-" +myDate.getMonth()+"-"+myDate.getDay()+" "
                       				 +myDate.getHours() + ":"
                       				+myDate.getMinutes() + ":"
                       				+myDate.getSeconds() );
                        </script>
                        </div>
                        <small>&nbsp;</small>
                    </div>
                </div>
            </div>
        </div>
       <%-- <div class="row">
                    <div class="col-sm-12">
                        <div class="ibox float-e-margins">
                            <div class="ibox-title">
                                <h5>待处理任务</h5>
                                <div class="ibox-tools">
                                    <a class="collapse-link">
                                        <i class="fa fa-chevron-up"></i>
                                    </a>                                   
                                </div>
                            </div>
                            <div class="ibox-content">
                                
                        </div>
                    </div>
 		</div>
    </div--%>>
    </div>
</body>
</html>