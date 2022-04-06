
$(function () {
    $("#submittext").click(
        function () {


        var data = $("#text-table").serialize();
        data = decodeURIComponent(data,true);
        var jsonData=DataDeal.formToJson(data);

        $.ajax(
            {
                type: 'POST',
                dataType: 'JSON',
                url: '/content/submitContent',
                data: jsonData,
                contentType: "application/json;charset=utf-8",
                success: function (res) {
                    if(res.success==true){
                        swal(
                            {
                                title: res.message,
                                icon: "success",

                            }
                        )
                    }else if(res.success==false) {
                        swal({
                            title: res.message,
                            text: "是否跳转登录页？",
                            icon: "error",
                            button: true,
                            dangerModel: true,
                        }).then((flag)=>{
                            if(flag){
                                window.location.href='/login';
                            }
                        })

                    }
                }
            }
        )
    }
    )
})

