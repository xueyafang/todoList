var login=new Vue({
        el:'#app',
        data:{
			loginForm:{
				name:'',
				password:'',
			}
        },
        methods:{
        	submitForm:function(){
                $.ajax({
				   type: "post",
				   url: "/findUser",
					/*data:{"name":this.loginForm.name,"password":this.loginForm.password},*/
                    data : JSON.stringify(this.loginForm),
                    contentType : 'application/json',
                    dataType : 'json',
				   success: function(msg){
					 if(msg){
					 	//如果登陆成功,则进入主页面
					 	window.location.href = "/index";
					 }else{
					 	//如果登录失败,则在此页面,提示用户名或密码错误
                         login.$message({
                             message: '用户名或密码错误',
                             type: 'error'
                         });
					 }
				   },
				   error:function(){
					   alert("失败");
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
