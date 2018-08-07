<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>${fns:getConfig('productName')}</title>
    <meta name="decorator" content="blank"/>
    <style type="text/css">
       html{height: 100%;}
body.signin {
    background: #18c8f6;
    height: auto;
    background:url("${ctxStatic}/Hplus-v.4.1.0/img/background.png") no-repeat center fixed;
    -webkit-background-size: cover;
    -moz-background-size: cover;
    -o-background-size: cover;
    background-size: cover;
    color: rgba(255,255,255,.95);
}

.signinpanel {
    width: 1000px;
    margin: 4% auto 0 auto;
}

.signinpanel .logopanel {
    float: none;
    width: auto;
    padding: 0;
    background: none;
}

.signinpanel .signin-info ul {
    list-style: none;
    padding: 0;
    margin: 20px 0;
   
 
}

 .logopanel{
 height:420px;

  background:url("${ctxStatic}/Hplus-v.4.1.0/img/left.png") no-repeat center fixed;
 }

.signinpanel .form-control {
    display: block;
    margin-top: 15px;
}

.uname {
    background: #fff url(${ctxStatic}/Hplus-v.4.1.0/img/user.png) no-repeat 95% center;color:#1c838c;
}

.pword {
    background: #fff url(${ctxStatic}/Hplus-v.4.1.0/img/locked.png) no-repeat 95% center;color:#1c838c;
}

.signinpanel .btn {
    margin-top: 15px;
}

.signinpanel form {
    background: rgba(255, 255, 255, 0.2);
    border: 1px solid rgba(255,255,255,.3);
    -moz-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
    -webkit-box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
    box-shadow: 0 3px 0 rgba(12, 12, 12, 0.03);
    -moz-border-radius: 3px;
    -webkit-border-radius: 3px;
    border-radius: 3px;
    padding: 30px;
}

.signup-footer{
	border-top: solid 1px rgba(255,255,255,.3);
	margin:20px 0;
	padding-top: 15px;
	 color: #387b81;
    	font-size: 15px;
	}

@media screen and (max-width: 768px) {
    .signinpanel,
    .signuppanel {
        margin: 0 auto;
        width: 420px!important;
        padding: 20px;
    }
    .signinpanel form {
        margin-top: 20px;
    }
    .signup-footer {
        margin-bottom: 10px;
    }
    .signuppanel .form-control {
        margin-bottom: 10px;
    }
    .signup-footer .pull-left,
    .signup-footer .pull-right {
        float: none !important;
        text-align: center;
    }
    .signinpanel .signin-info ul {
        display: none;
    }
}
.logo-name2 {
    color: #1c838c;
    font-size: 45px;
    font-weight: 800;
    letter-spacing: 2px;
    margin-bottom: 15px;
    text-align: center;
    padding: 30px;
}
.text-muted{
color: #c4eaf3;
}
@media screen and (max-width: 320px) {
    .signinpanel,
    .signuppanel {
        margin:0 20px;
        width:auto;
    }
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

<body class="signin">
<h4 class="logo-name2">${fns:getConfig('productName')}</h3>
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-8">
                <div class="signin-info">
                   <div class="logopanel">
                   <img src="${ctxStatic}/Hplus-v.4.1.0/img/left.png" style="height:100%"/>
                   </div>
                </div>
            </div>
            <div class="col-sm-4" style="padding-top:45px;">
                 <form id="loginForm" role="form" action="${ctx}/login" class="form-signin" method="post">
        <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
            <font color="red">

                <label id="loginError" class="error">${message}<a data-dismiss="alert">X</a></label>
            </font>
        </div>
        <div class="form-group">
            <input id="username" name="username" type="username" class="form-control uname" placeholder="用户名" required="">
        </div>
        <div class="form-group">
            <input id="password" name="password" type="password" class="form-control pword m-b" placeholder="密码" required="">
        </div>
        <c:if test="${isValidateCodeLogin}">
            <div class="validateCode">
                <label class="input-label mid" for="validateCode">验证码</label>
                <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;color:#1ab394;" />
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
        <div class="signup-footer">
            <div class="pull-left">
                技术支持:青岛正有源机电设备有限公司
            </div>
        </div>
    </div>
</body>



</body>
</html>