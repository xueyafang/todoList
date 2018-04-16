var mainVue = new Vue({
    el: '#app',
    data:{
        //默认是全部事件
        tableSearchModel:{
            finishStatus:0,//是否完成
            endDate:[new Date(0,0,0,0,0),new Date()],
        },
        priorityStatus: 0,//紧急程度
        options: [{
            value: 2,
            label: '全部'
        }, {
            value: 1,
            label: '已完成'
        }, {
            value: 0,
            label: '未完成'
        }],
        //finishStatus:0,
        tableData:[],
        currentPage:1,
        pageSize:10,
        total:10,//总条数
        priorityList:[{
            value: 1,
            label: '重要且紧急'
        }, {
            value: 2,
            label: '重要但不紧急'
        }, {
            value: 3,
            label: '不重要但紧急'
        }, {
            value: 4,
            label: '不重要也不紧急'
        }],
        saveForm:{
            scheduleName:'',
            priority:'',
            cutoff:''

        },
        dialogSaveVisible:false,
        editForm:{
            scheduleName:'',
            priority:'',
            isFinished:'',
            cutoff:'',
        },
        finishStatus:[{
            value: 0,
            label: '未完成'
        }, {
            value: 1,
            label: '已完成'
        }],
        dialogEditVisible:false
    },
    methods:{
        changePriority:function(){
            //改变优先级重新获取数据
            this.getTotal();
            this.getTableData();
        },
        toggleFinish:function(scope){
            //改变完成状态
            var isFinished = 0;
            var sid = scope.row.sid;
            scope.row.isFinished = !scope.row.isFinished;
            if(scope.row.isFinished){
                isFinished = 1;
            }else{
                isFinished = 0;
            }
            $.ajax({
                url: "/changeFinishStatus",
                type: 'Post',
                dataType: 'json',    //第1处
                contentType:'application/json;charset=UTF-8',
                /*data:"{\"isFinished\":\""+1+"\",\"sid\":\"\""+"3"+"\"}",*/
                /*data:{"isFinished":1,"sid":"3"},*/
                data:JSON.stringify({"isFinished":isFinished,"sid":sid}),
                success:function(msg){
                    /*alert(msg);*/
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
            //表重新加载
            this.getTotal();
            this.getTableData();

        },
        addItem:function(){
            this.dialogSaveVisible = true;
        },
        //查询改变完成的状态
        changeFinishStatus:function(){
            //先将table清空,然后再赋值
            this.getTotal();
            this.getTableData();
        },
        handleSizeChange:function(val) {

        },
        handleCurrentChange:function(val){

        },
        changeDataRange:function(){
            console.log(this.daterange);
            alert(111);
        },
        handleDelete:function(a,schedule){
            var sid = schedule.sid;
            $.ajax({
                url: "/deleteSchedule",
                type: 'Post',
                dataType: 'json',    //第1处
                contentType: 'application/json;charset=UTF-8',
                /*data:"{\"isFinished\":\""+1+"\",\"sid\":\"\""+"3"+"\"}",*/
                /*data:{"isFinished":1,"sid":"3"},*/
                data: JSON.stringify({"sid": sid}),
                success: function (msg) {
                    mainVue.$message("删除成功");
                    //刷新表格数据
                    mainVue.getTotal();
                    mainVue.getTableData();
                },
                error: function (err) {
                    mainVue.$message("删除失败");
                }
            })
        },
        handleEdit:function(a,schedule){

            var sid = schedule.sid;
            //去获取值并填上
            $.ajax({
                url: "/findBySid",
                type: 'Post',
                dataType: 'json',    //第1处
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({"sid":sid}),
                success:function(msg) {
                    /*console.log(msg);*/
                    mainVue.editForm.scheduleName = msg.scheduleName;
                    mainVue.editForm.priorityStatus = msg.priority;
                    mainVue.editForm.isFinished = msg.isFinished;
                    mainVue.editForm.cutoff = msg.endTime;
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
            mainVue.dialogEditVisible = true;
        },
        resetEditForm:function(){

        },
        edit:function(){
            $.ajax({
                url: "/addSchedule",
                type: 'Post',
                dataType: 'json',    //第1处
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify(mainVue.editForm),
                success:function(msg) {
                    mainVue.$message("修改成功");
                    mainVue.getTotal();
                    mainVue.getTableData();
                    mainVue.dialogEditVisible = false;
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
            //更新列表
            mainVue.getTotal();
            mainVue.getTableData();
        },
        format: function (format, date) {
            //格式化日期 format('yyyy-MM-dd hh:mm:ss:SS 星期w 第q季度')
            if (!date) {
                date = new Date();
            } else if (typeof (date) == 'string') {
                date = new Date(date);
            }
            var Week = ['日', '一', '二', '三', '四', '五', '六'];
            var o = {
                'y+': date.getYear(), //year
                'M+': date.getMonth() + 1, //month
                'd+': date.getDate(), //day
                'h+': date.getHours(), //hour
                'H+': date.getHours(), //hour
                'm+': date.getMinutes(), //minute
                's+': date.getSeconds(), //second
                'q+': Math.floor((date.getMonth() + 3) / 3), //quarter
                'S': date.getMilliseconds(), //millisecond
                'w': Week[date.getDay()]
            }
            if (/(y+)/.test(format)) {
                format = format.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp('(' + k + ')').test(format)) {
                    format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length));
                }
            }
            return format;
        },
        getTotal:function(){
            //查询条件
            var priorityStatus = this.priorityStatus;
            var finishStatus = this.tableSearchModel.finishStatus;
            //如果优先级状态为0,则为全部
            if(priorityStatus==0){
                priorityStatus = null;
            }
            //如果完成状态为2,则为全部
            if(finishStatus==2){
                finishStatus=null;
            }
            var currentPage = this.currentPage;
            var pageSize  =this.pageSize;
            //获取总条数
            $.ajax({
                url: "/getTotal",
                type: 'Post',
                dataType: 'json',    //第1处
                crossDomain:'true',
                contentType:'application/json;charset=UTF-8',
                data:"{\"priorityStatus\":\""+priorityStatus+"\",\"isFinished\":\""+finishStatus+"\"}",
                success:function(msg) {
                    mainVue.total = msg;
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
        },
        getTableData:function(){
            //1.初始化tableData
            this.tableData = [];
            //查询条件
            var priorityStatus = this.priorityStatus;
            var finishStatus = this.tableSearchModel.finishStatus;
            //如果优先级状态为0,则为全部
            if(priorityStatus==0){
                priorityStatus = null;
            }
            //如果完成状态为2,则为全部
            if(finishStatus==2){
                finishStatus=null;
            }
            var currentPage = this.currentPage;
            var pageSize  =this.pageSize;
            //1.将表中的数据查出来
            $.ajax({
                url: "/paging",
                type: 'Post',
                dataType: 'json',    //第1处
                crossDomain:'true',
                contentType:'application/json;charset=UTF-8',
                data:"{\"priorityStatus\":\""+priorityStatus+"\",\"isFinished\":\""+finishStatus+"\",\"currentPage\":\""+currentPage+"\",\"pageSize\":\""+pageSize+"\"}",
                success:function(msg){
                    var isFinished = 0;
                    var endTime = '';
                    $.each(msg,function(i,j){
                        if(j.isFinished){
                            isFinished = true;
                        }else{
                            isFinished = false;
                        }
                        //格式化结束时间
                        endTime = mainVue.format("yyyy-MM-dd hh:mm:ss",j.endTime);
                        mainVue.tableData.push({
                            id:i,
                            sid:j.sid,
                            date:endTime,
                            name:j.scheduleName,
                            isFinished:isFinished
                        });
                        //刷新表格
                        //mainVue.refreshTable();
                    });
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
        },
        //添加(重置)
        resetSaveForm:function(){

        },
        //添加
        addForm:function(){
            var scheduleName = this.saveForm.scheduleName;
            var priorityStatus = this.saveForm.priority;
            var cutoff = this.saveForm.cutoff;
            cutoff = mainVue.format("yyyy-MM-dd hh:mm:ss",cutoff);
            var endTime = new Date(cutoff);
            $.ajax({
                url: "/addSchedule",
                type: 'Post',
                dataType: 'json',    //第1处
                contentType:'application/json;charset=UTF-8',
                data:JSON.stringify({"scheduleName":scheduleName,"priorityStatus":priorityStatus,"endTime":cutoff,"isFinished":0}),
                success:function(msg) {
                    mainVue.$message("添加成功");
                    mainVue.dialogSaveVisible = false;
                    mainVue.getTotal();
                    mainVue.getTableData();
                },
                error:function(err){
                    alert("err");
                    alert(JSON.stringify(err));
                }
            });
            /*alert(222);*/
            /*mainVue.dialogSaveVisible = true;*/
        }
    },
    mounted:function(){
        //获取总条数
        this.getTotal();
        //获取表格的数据
        this.getTableData();
    },
});