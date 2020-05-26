/**
 * 当标签出现在脚本代码后面时，不能使用$().click();绑定点击事件，使用on()方法
 * on()方法绑定事件称之为委托方式绑定,on()也需要标签已经被解析才可以绑定事件
 * 解决方法：调用jQuery的ready函数执行文档加载完成后处理事件绑定
 * @param {type} param
 */
$(function () {//文档加载完成后执行的回调函数
    $("td").on("click",".delbtn",function () {
        console.log("click invoke...");
        return confirm("确认删除吗？这个操作不可恢复");
    });
    var ids={ids:[]};
    $(".cid").click(function () {
        ids.ids=[];
        $(".cid:checked").each(function () {//遍历所有被选择的复选框
            ids.ids.push($(this).val());
        });
    });
    $(".delbtns").click(function () {//多条信息删除
        if (ids.ids.length<=0) return;
        if (confirm("确认要删除被选择的信息吗？这个操作不可恢复")){
            var json=JSON.stringify(ids);
            console.log("===="+json);
            $("#deleteids").val(json);
            $("#form1").attr("action","/deleteusers");
            $("#form1").submit();

        }
    });
});