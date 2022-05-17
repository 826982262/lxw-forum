$(function () {
    $("#jqGrid").jqGrid({
        url: '/admin/users/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'uid', index: 'uid', width: 50, key: true, hidden: true},
            {label: '账号', name: 'account', index: 'account',align: "center", width: 180},
            {label: '昵称', name: 'uname', index: 'uname', align: "center",width: 180},
            {label: '身份状态', name: 'flag', index: 'flag',align: "center", width: 60, formatter: lockedFormatter},
            // {label: '是否注销', name: 'isDeleted', index: 'isDeleted', width: 60, formatter: deletedFormatter},
            {label: '邮箱', name: 'email', index: 'email',align: "center", width: 120},
            {label: '注册时间', name: 'createTime', index: 'createTime',align: "center", width: 120}

        ],
        height: "auto",
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "list",
            page: "currPage",
            total: "totalPage",
            records: "total"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order",
        },

        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        },

        onSelectRow: function(uid){

            rowData = $("#jqGrid").jqGrid("getRowData", uid);
            var admin = "<button type=\"button\" class=\"btn btn-block btn-success btn-sm \" style=\"width: 70%;;margin:0 auto\">管理员</button>";

            if (rowData.flag!=null&&rowData.flag==admin){

                $("#jqGrid").jqGrid("setSelection", uid, false);//设置该行不能被选中。
            }
        },
        /*多选时触发*/
        onSelectAll:function(uid,status) {

            if (status == true) {
                var uid = $("#jqGrid").jqGrid('getDataIDs');//获取所有行id
                var admin = "<button type=\"button\" class=\"btn btn-block btn-success btn-sm \" style=\"width: 70%;;margin:0 auto\">管理员</button>";
                for (var i = 0; i < uid.length; i++) {
                    rowData = $("#jqGrid").jqGrid("getRowData", uid[i]);

                    if (rowData.flag != null && rowData.flag ==admin) {
                        $("#jqGrid").jqGrid("setSelection", uid[i], false);//设置该行不能被选中。
                    }
                }
            }

        }





    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });
    



    function lockedFormatter(cellvalue) {
        if (cellvalue == 3) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm \" style=\"width: 50%; ;margin:0 auto\">锁定</button>";
        } else if (cellvalue == 2) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm flag\" style=\"width: 50%;;margin:0 auto\">正常</button>";
        }else if (cellvalue == 1){
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm \" style=\"width: 70%;;margin:0 auto\">管理员</button>";
        }
    }

    function deletedFormatter(cellvalue) {
        if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-secondary btn-sm\" style=\"width: 50%;\">注销</button>";
        } else if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm\" style=\"width: 50%;\">正常</button>";
        }
    }

    /*解决bootstrap4的jqGrid下一页不显示*/
    $("#first_jqGridPager").html("<span class=\"oi oi-media-step-backward\"><i class=\"fa fa-step-backward\" aria-hidden=\"true\"></i></span>")

    $("#prev_jqGridPager").html("<span class=\"oi oi-caret-left\"><i class=\"fa fa-backward\" aria-hidden=\"true\"></i></span>")

    $("#next_jqGridPager").html("<span class=\"oi oi-caret-right\"><i class=\"fa fa-forward\" aria-hidden=\"true\"></i></span>")

    $("#last_jqGridPager").html("<span class=\"oi oi-media-step-forward\"><i class=\"fa fa-step-forward\" aria-hidden=\"true\"></i></span>")
});

/**
 * jqGrid重新加载
 */
function reload() {
    var page = $("#jqGrid").jqGrid('getGridParam', 'page');
    $("#jqGrid").jqGrid('setGridParam', {
        page: page
    }).trigger("reloadGrid");
}


function lockUser(lockStatus) {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    if (lockStatus != 2 && lockStatus != 3) {
        swal('非法操作', {
            icon: "error",
        });
    }
    swal({
        title: "确认弹框",
        text: "确认要修改账号状态吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/users/lock/" + lockStatus,
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (res) {
                        if (res.success == true) {
                            swal("操作成功", {
                                icon: "success",
                            });
                            $("#jqGrid").trigger("reloadGrid");
                        } else {
                            swal(res.message, {
                                icon: "error",
                            });
                        }
                    }
                });
            }
        }
    )
    ;
}