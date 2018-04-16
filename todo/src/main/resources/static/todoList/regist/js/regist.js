var registVue = new Vue({
        el:'#app',
        data:{
			registForm:{
				name:'',
				password:'',
				checkPass:''
			},
        },
        methods:{
        	submitForm:function(){
                $.ajax({
				   type: "post",
				   url: "/addUser",
					data:"{\"name\":\""+registVue.registForm.name+"\",\"password\":\""+registVue.registForm.password+"\"}",
					dataType:'json',
                    contentType : 'application/json',
				   success: function(msg){
				   	 if(msg){
				   	 	registVue.$message({
							message:'注册成功!',
							type:'success'
						});
				   	 	/*setTimeout(location="/login",3000);*/
				   	 	window.location.href = "/login";
					 }else{
                         registVue.$message({
                             message: '用户名已存在!请重新注册',
                             type: 'error'
                         });
					 }
				   },
				   error:function(msg){
				   	alert(JSON.stringify(msg));
				   }
				});
        	},
        	resetForm:function(formName){
        		
        	},
        	
        }
  });
/*$.ajax({
   type: "GET",
   url: "127.0.0.1:8080/user/add?name=xiao&password=123456",
   success: function(msg){
     alert("成功");
   },
   error:function(){
   	alert("失败");
   }
});*/
