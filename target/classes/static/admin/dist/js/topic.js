$(function () {
    var  audit= getUrlParam("audit")
    if(audit==null){ audit=null}
    $("#jqGrid").jqGrid({
        url: '/admin/topicmanage/list?audit='+audit,
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true, hidden: true},
            {label: '标签', name: 'lName', index: 'lName',align: "center", width: 60},
            {label: '创建时间', name: 'time', index: 'time', align: "center",width: 120},
            {label: '文章标题', name: 'title', index: 'title',align: "center", width: 180},
            {label: '作者', name: 'uname', index: 'uname',align: "center", width: 120},
            {label: '文章状态', name: 'audit', index: 'audit',align: "center", width: 70, formatter: lockedFormatter},
            // {label: '是否注销', name: 'isDeleted', index: 'isDeleted', width: 60, formatter: deletedFormatter},

              {label: '是否置顶', name: 'istop', index: 'istop',align: "center", width: 60, formatter: isTop}

        ],
        height: "auto",
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        loadonce: true,//进行分页跳转
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
    
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r!=null)return unescape(r[2]);
        return null;
    }


    function lockedFormatter(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-warning btn-sm \" style=\"width: 50%; ;margin:0 auto\">待审核</button>";
        } else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-info btn-sm \" style=\"width: 60%;;margin:0 auto\">人工审核</button>";
        }else if (cellvalue == 2){
            return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm \" style=\"width: 50%;;margin:0 auto\">已发布</button>";
        }else if (cellvalue ==3){
            return "<button type=\"button\" class=\"btn btn-block btn-danger btn-sm \" style=\"width: 75%;;margin:0 auto\">审核不通过</button>";
        }
    }

    function isTop(cellvalue) {
        if (cellvalue == 0) {
            return "<button type=\"button\" class=\"btn btn-block btn-warning btn-sm \" style=\"width: 65%; ;margin:0 auto\">非置顶</button>";
        } else if (cellvalue == 1) {
            return "<button type=\"button\" class=\"btn btn-block btn-info btn-sm \" style=\"width: 65%;;margin:0 auto\">已置顶</button>";
        }
    }


    // function check(cellvalue) {
    //     if (cellvalue != 1) {
    //         return "<button type=\"button\" class=\"btn btn-block btn-success btn-sm check\"  style=\"width: 50%;;margin:0 auto\">通过</button>";
    //     } else if (cellvalue ==0 ) {
    //         return "<button type=\"button\" class=\"btn btn-block  btn-secondary btn-sm check\"  style=\"width: 50%;;margin:0 auto\">不通过</button>";
    //     }
    // }

    // /*解决bootstrap4的jqGrid下一页不显示*/
    // $("#first_jqGridPager").html("<span class=\"oi oi-media-step-backward\"><i class=\"fa fa-step-backward\" aria-hidden=\"true\"></i></span>")
    //
    // $("#prev_jqGridPager").html("<span class=\"oi oi-caret-left\"><i class=\"fa fa-backward\" aria-hidden=\"true\"></i></span>")
    //
    // $("#next_jqGridPager").html("<span class=\"oi oi-caret-right\"><i class=\"fa fa-forward\" aria-hidden=\"true\"></i></span>")
    //
    // $("#last_jqGridPager").html("<span class=\"oi oi-media-step-forward\"><i class=\"fa fa-step-forward\" aria-hidden=\"true\"></i></span>")
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

// function checkSentity(audit) {
//     var ids = getSelectedRows();
//     $.ajax({
//         type: "POST",
//         url: "/admin/sensitivity/check/" + audit,
//         contentType: "application/json",
//         data: JSON.stringify(ids),
//         success: function (res) {
//             if (res.success == true) {
//                 swal("操作成功", {
//                     icon: "success",
//                 });
//                 $("#jqGrid").trigger("reloadGrid");
//             } else {
//                 swal(res.message, {
//                     icon: "error",
//                 });
//             }
//         }
//     });
// }
function doTop(isTop) {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    if (isTop != 0 && isTop != 1) {
        swal('非法操作', {
            icon: "error",
        });
    }
    swal({
        title: "确认弹框",
        text: "确认要修改文章状态吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/topicmanage/doTop/" + isTop,
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
function lockTopic(lockStatus) {
    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    if (lockStatus != 1 && lockStatus != 2) {
        swal('非法操作', {
            icon: "error",
        });
    }
    swal({
        title: "确认弹框",
        text: "确认要修改文章状态吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/topicmanage/check/" + lockStatus,
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