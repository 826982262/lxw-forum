$(function () {

    $("#jqGrid").jqGrid({
        url: '/admin/label/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'lId',  width: 50,  hidden: true,},
            {label: '分类名称', name: 'lName',  width: 240, align: "center"},
            {label: '排序值', name: 'ranking',  width: 120, align: "center"}
        ],
        height: "auto",
        rowNum: 10,
        rowList: [10, 20, 50],
        styleUI: 'Bootstrap',
        loadtext: '信息读取中...',
        rownumbers: false,
        rownumWidth: 20,
        autowidth: true,
        pgbuttons: true,
        multiselect: true,
        viewrecords: true,
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
        }
    });

    $(window).resize(function () {
        $("#jqGrid").setGridWidth($(".card-body").width());
    });

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

function labelAdd() {
    reset();
    $('.modal-title').html('分类添加');
    $('#labelModal').modal('show');
}





//绑定modal上的保存按钮
$('#saveButton').click(function () {
    var l_id = $("#l_id").val();
    var labelName = $("#labelName").val();
    var ranking = $("#ranking").val();

    if (!validCN_ENString2_18(labelName)) {
        $('#edit-error-msg').css("display", "block");
        $('#edit-error-msg').html("参数不能为空！");
    } else {
        var data = {
            "lId": "",
            "lName": labelName,
            "ranking": ranking
        };
        var url = '/admin/label/save';
        var id = getSelectedRowWithoutAlert();
        if (id != null) {
            url = '/admin/label/update';
            data = {
                "lId": l_id,
                "lName": labelName,
                "ranking": ranking
            };
        }
        $.ajax({
            type: 'POST',//方法类型
            url: url,
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                if (result.success == true) {
                    $('#labelModal').modal('hide');
                    swal("保存成功", {
                        icon: "success",
                    });
                    reload();
                } else {
                    $('#labelModal').modal('hide');
                    swal(result.message, {
                        icon: "error",
                    });
                }
                ;
            },
            error: function () {
                swal("操作失败", {
                    icon: "error",
                });
            }
        });
    }
});

function labelEdit() {
    reset();
    var id = getSelectedRow();
    if (id == null) {
        return;
    }
    var rowData = $("#jqGrid").jqGrid("getRowData", id);
    $('.modal-title').html('标签编辑');
    $('#labelModal').modal('show');
    $("#l_id").val(id);
    $("#labelName").val(rowData.lName);
    $("#ranking").val(rowData.ranking);
}

/**
 * 分类的删除会牵涉到多级分类的修改和商品数据的修改，请谨慎删除分类数据，
 * 如果在商城页面不想显示相关分类可以通过调整rank值来调整显示顺序，
 * 不过代码我也写了一部分，如果想保留删除功能的话可以在此代码的基础上进行修改。
 */
function deleteLabel() {

    var ids = getSelectedRows();
    if (ids == null) {
        return;
    }
    swal({
        title: "确认弹框",
        text: "确认要删除数据吗?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    }).then((flag) => {
            if (flag) {
                $.ajax({
                    type: "POST",
                    url: "/admin/label/delete",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function (res) {
                        if (res.success == true) {
                            swal("删除成功", {
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


function reset() {
    $("#labelName").val('');
    $("#ranking").val(0);
    $('#edit-error-msg').css("display", "none");


}