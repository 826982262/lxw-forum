
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

var DataDeal = {
    //将从form中通过$('#form').serialize()获取的值转成json
    formToJson: function (data) {
        data=data.replace(/&/g,"\",\"");
        data=data.replace(/=/g,"\":\"");
        data="{\""+data+"\"}";
        return data;
    },
};

/*跳转页面*/
$(function () {
    $("#goregister").click(
        function () {
            window.location.href='/register'
        }
    )
})

$(function () {
    $("#login").click(
        function () {
            var data = $("#login_table").serialize();
            data = decodeURIComponent(data,true);
            var jsonData=DataDeal.formToJson(data);


            $.ajax(
                {
                    type: 'POST',
                    dataType: 'JSON',
                    url: '/forum/login',
                    data: jsonData,
                    contentType: "application/json;charset=utf-8",
                    success: function (res) {
                        if(res.success==true){
                            window.location.href='/index';
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




