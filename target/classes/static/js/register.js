
function change(){
    getcode();
}

//进入页面执行
window.onload = function () {
    getcode()
}

//加载验证码
function getcode(){
    var url = 'getcode?_timer='+new Date().getTime();
    $.get(
        url,function(res){
            $("#codeimg").attr('src','data:image.png;base64,'+res)
        }
    )
}




//表单验证

function CheckItem(obj){
    // 判断switch哪个输入框
    switch($(obj).attr('name'))
    {

        /*检验用户名*/
        case "uname":
            if(obj.value==""){
                if ($("#uname").hasClass("is-valid")==true){$("#uname").removeClass("is-valid"),
                    $("#uname").addClass("is-invalid")}
                else {
                    $("#uname").hasClass("is-invalid")==true ? null : $("#uname").addClass("is-invalid")
                }
            }else if(obj.value!=""){
                var url="forum/exitname?name="+encodeURI($("#uname").val())
                $.get(url, function(res) {
                        if (res) {
                            $("#uname").hasClass("is-invalid")&&$("#uname").hasClass("is-valid")==true ? $("#uname").removeClass("is-valid") : $("#uname").addClass("is-invalid")
                        }else {
                            $("#uname").hasClass("is-invalid")==true ? $("#uname").removeClass("is-invalid") && $("#uname").addClass("is-valid") : $("#uname").addClass("is-valid")
                        }
                    })
            }
            break;

            /*检验密码*/
        // 检验密码不为空
        case "password":
            if(obj.value==""){
                if ($("#inputPassword").hasClass("is-valid")==true){$("#inputPassword").removeClass("is-valid")}
                $("#inputPassword").hasClass("is-invalid")==true ? null : $("#inputPassword").addClass("is-invalid")
            }else{
                $("#inputPassword").hasClass("is-invalid")==true ? $("#inputPassword").removeClass("is-invalid") && $("#inputPassword").addClass("is-valid"):$("#inputPassword").addClass("is-valid")
            }
            break;

            /*校验重复密码*/
        // 检验重复密码
        case "respassword":
            if(obj.value==""){

                if ($("#inputPassword3").hasClass("is-valid")==true){$("#inputPassword3").removeClass("is-valid")}
                $("#inputPassword3").hasClass("is-invalid")==true ? null : $("#inputPassword3").addClass("is-invalid")
            }else if($(obj).val() != $("#inputPassword").val()){
                swal({
                    text: "两次密码不一致",
                    icon: "warning",
                })
                if ($("#inputPassword3").hasClass("is-valid")==true){$("#inputPassword3").removeClass("is-valid")}
                $("#inputPassword3").hasClass("is-invalid")==true ? null : $("#inputPassword3").addClass("is-invalid")
            }else{
                $("#inputPassword3").hasClass("is-invalid")==true ? $("#inputPassword3").removeClass("is-invalid") && $("#inputPassword3").addClass("is-valid"):$("#inputPassword3").addClass("is-valid")
            }
            break;

        //检验手机号
        case "phone":
            if(obj.value==""){
                if ($("#inputphone").hasClass("is-valid")==true){$("#inputphone").removeClass("is-valid")}
                $("#inputphone").hasClass("is-invalid")==true ? null : $("#inputphone").addClass("is-invalid")
            }else{
                $("#inputphone").hasClass("is-invalid")==true ? $("#inputphone").removeClass("is-invalid") && $("#inputphone").addClass("is-valid"):$("#inputphone").addClass("is-valid")	}
            break;
        //检验手机号
        case "email":
            if(obj.value==""){
                if ($("#inputEmail3").hasClass("is-valid")==true){$("#inputEmail3").removeClass("is-valid")}
                $("#inputEmail3").hasClass("is-invalid")==true ? null : $("#inputEmail3").addClass("is-invalid")
            }else{
                $("#inputEmail3").hasClass("is-invalid")==true ? $("#inputEmail3").removeClass("is-invalid") && $("#inputEmail3").addClass("is-valid"):$("#inputphone").addClass("is-valid")	}
            break;

    }
}


var DataDeal = {
    //将从form中通过$('#form').serialize()获取的值转成json
    formToJson: function (data) {
        data=data.replace(/&/g,"\",\"");
        data=data.replace(/=/g,"\":\"");
        data="{\""+data+"\"}";
        return data;
    },
};

$(function () {
    $("#register").click(
        function () {
            var data = $("#regist_table").serialize();
            data = decodeURIComponent(data,true);
            var jsonData=DataDeal.formToJson(data);


            $.ajax(
                {
                    type: 'POST',
                    dataType: 'JSON',
                    url: '/forum/register',
                    data: jsonData,
                    contentType: "application/json;charset=utf-8",
                    success: function (res) {
                        if(res.success==true){
                            swal({
                                title: res.message,
                                text: "是否跳转登录页？",
                                icon: "success",
                                button: true,
                                dangerModel: true,
                            }).then((flag)=>{
                                if(flag){
                                    window.location.href='/login';
                                }
                            })
                        }else if(res.success==false) {
                            swal({
                                title: res.message,
                                icon: "error",
                            });
                            getcode();
                        }
                    }
                }
            )
        }
    )
})

