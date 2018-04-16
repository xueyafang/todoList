

//获取dialog属性
var dialogAttr = getDialogMergeAttr({
    el: '#save',
    data : {
        //与后台及元素交互的model
        saveModel: {
            name : '',
            type : '',
            description : '',
            applicationId: null
        },
        //前端验证
        saveRules: {
            applicationId: [
                { required: true, message: '请选择应用名称', trigger: 'blur' }
            ],
            name: [
                { required: true, message: '请输入角色名称', trigger: 'blur' },
                { min: 1, max: 32, message: '长度在1到32个字符', trigger: 'blur' }
            ],
            type: [
                { required: true, message: '请选择角色类型', trigger: 'blur' }
            ],
            description: [
                { min: 0, max: 256, message: '长度在0到256个字符', trigger: 'blur' }
            ]
        },

        //自定义参数
        typeoptions : [],
        appoptions: []
    },
    methods : {
        //确定按钮
        saveSubmit : function(){
            this.$refs["saveModel"].validate(function (valid) {
                if (valid) {//验证通过
                    var para = saveVue.saveModel;
                    /*var permissionIds = '';
                    $(":checkbox:checked").each(function(){
                        var permissionId = $(this).val();
                        permissionIds += permissionId + ',';
                    });
                    permissionIds = permissionIds.substring(0,permissionIds.length-1);
                    para.permissionIds = permissionIds;*/

                    var result = ak.msService("sys","roleManageApi/create",para);
                    if(null != result && result.code == 0){//操作成功
                        //提示
                        ak.success(result.msg);
                        //关闭窗口
                        saveVue.dialogClose();
                        //刷新表格
                        mainVue.tableRefresh();
                    }
                }
            });
        },
        //重置按钮
        saveReset : function(){
            this.$refs["saveModel"].resetFields();
        },
        //应用名称下拉框变化
        appchange:function(applicationId){
            this.loadRes(applicationId);
        },
        //加载权限
        loadRes :function(applicationId){
            /*//加载菜单
            var para = {
                applicationId:applicationId,
                loadPermission:'0'
            }
            var resources = ak.msService("sys","resourceApi/getAllRes",para);

            if(null != resources){//操作失败
                var html = "";
                var data = resources.data;
                $.each(data, function(idx, bean) {

                    html += "<tr data-tt-id='"+bean.id+"' data-tt-parent-id='"+bean.parentId+"'>";
                    html += "<td>"+bean.name+"</td>";

                    var permissions = bean.permissions;
                    $.each(permissions, function(idx, pms) {
                        html += "<td><input  type='checkbox' value='"+pms.id+"' name='"+pms.sn+"'>"+pms.name+"</input></td>";
                    });

                    //补空td
                    var len = 5 - permissions.length;
                    for(var i=0;i<len;i++){
                        html += "<td></td>";
                    }
                    html += "</tr>";
                });

                $("#permissionTb tbody").html(html);
                $('#permissionTb').removeClass('hidden');
                $("#permissionTb").treetable({
                    expandable : true
                });
            } */
        }
    },
    //页面初始化事件
    mounted: function () {
        this.typeoptions = mainVue.typeoptions;
        this.appoptions =  mainVue.appoptions;
    }
});
//生成vue对象
var saveVue = new Vue(dialogAttr);

//input添加change事件
$(function(){
    $("input,textarea").on('input',function(e){
        ak.filterJson(saveVue.saveModel);
    });
});

