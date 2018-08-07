<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <meta name="decorator" content="blank"/>
    <style type="text/css">
        html, body, table {
            background-color: #f5f5f5;
            width: 100%;
            text-align: center;
        }
        
        body.gray-bg0 {
    background: #18c8f6;
   
    background:url("${ctxStatic}/Hplus-v.4.1.0/img/background.png") no-repeat center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
  
}
.signin-info {
       margin: 0 1px 0px 50px;
       height:500px;
    
}

        .form-signin-heading {
            font-family: Helvetica, Georgia, Arial, sans-serif, 黑体;
            font-size: 36px;
            margin-bottom: 20px;
            color: #0663a2;
        }

        .form-signin {
         padding-top: 35px;
           
            text-align: left;
            width: 300px;
            padding: 25px 29px 29px;
            margin: 70px auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
            box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
        }

        .form-signin .checkbox {
            margin-bottom: 10px;
            color: #0663a2;
        }

        .form-signin .input-label {
            font-size: 16px;
            line-height: 23px;
            color: #999;
        }

        .form-signin .input-block-level {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px;
            *width: 283px;
            *padding-bottom: 0;
            _padding: 7px 7px 9px 7px;
        }

        .form-signin .btn.btn-large {
            font-size: 16px;
        }

        .form-signin #themeSwitch {
            position: absolute;
            right: 15px;
            bottom: 10px;
        }

        .form-signin div.validateCode {
            padding-bottom: 15px;
        }
        
        
        
        .signup-footer{
        color: #70a1a5;
    	font-size: 15px;
        margin:20px 0;
        padding-top: 5px;
        }

        .mid {
            vertical-align: middle;
        }

        .header {
            height: 60px;
            padding-top: 20px;
        }

        .alert {
            position: relative;
            width: 300px;
            margin: 0 auto;
            *padding-bottom: 0px;
        }

        label.error {
            background: none;
            width: 270px;
            font-weight: normal;
            color: inherit;
            margin: 0;
        }
        .logo-name2 {
    color: #70a1a5;
    font-size: 50px;
    font-weight: 800;
    letter-spacing: 2px;
    margin-bottom: 15px;
}

 .logo-bottom2 {
    color: #70a1a5;
    font-size: 50px;
    font-weight: 800;
    letter-spacing: 2px;
    margin-bottom: 15px;
}
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#loginForm").validate({
                rules: {
                    validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
                },
                messages: {
                    username: {required: "请填写用户名."}, password: {required: "请填写密码."},
                    validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
                },
                errorLabelContainer: "#messageBox",
                errorPlacement: function (error, element) {
                    error.appendTo($("#loginError").parent());
                }
            });
        });
        // 如果在框架或在对话框中，则弹出提示并跳转到首页
        if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0) {
            alert('未登录或登录超时。请重新登录，谢谢！');
            top.location = "${ctx}";
        }
    </script>
</head>
<body class="gray-bg0">
<!--[if lte IE 6]><br/>
<div class='alert alert-block' style="text-align:left;padding-bottom:10px;"><a class="close" data-dismiss="alert">x</a>
    <h4>温馨提示：</h4>
    <p>你使用的浏览器版本过低。为了获得更好的浏览体验，我们强烈建议您 <a href="http://browsehappy.com" target="_blank">升级</a> 到最新版本的IE浏览器，或者使用较新版本的
        Chrome、Firefox、Safari 等。</p></div><![endif]-->
<div class="header">

</div>
<h4 class="logo-name2">${fns:getConfig('productName')}</h3>
<div class=" text-center loginscreen  animated fadeInDown">
 <div class="row">
 <div class="col-sm-6 sign-left">
 
 <div class="signin-info">
 <img src="${ctxStatic}/Hplus-v.4.1.0/img/left.png" style="height:100%"/>
 </div>
 </div>
 <div class="col-sm-6">
    <form id="loginForm" role="form" action="${ctx}/login" class="form-signin" method="post">
        <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
            <font color="red">

                <label id="loginError" class="error">${message}<a data-dismiss="alert">X</a></label>
            </font>
        </div>
        <div class="form-group">
            <input id="username" name="username" type="username" class="form-control" placeholder="用户名" required="">
        </div>
        <div class="form-group">
            <input id="password" name="password" type="password" class="form-control" placeholder="密码" required="">
        </div>
        <c:if test="${isValidateCodeLogin}">
            <div class="validateCode">
                <label class="input-label mid" for="validateCode">验证码</label>
                <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
            </div>
        </c:if><%--
		<label for="mobile" title="手机登录"><input type="checkbox" id="mobileLogin" name="mobileLogin" ${mobileLogin ? 'checked' : ''}/></label> --%>
        <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>
        <p class="text-muted text-center"><input type="checkbox" id="rememberMe"
                                                 name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住我（公共场所慎用）
        </p>
    </form>

    
        </div>
        
</div>
<div class="signup-footer text-center">
    技术支持:青岛正有源机电设备有限公司
    </div>
</div>


</body>
</html>