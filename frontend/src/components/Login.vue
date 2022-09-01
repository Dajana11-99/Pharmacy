<template>
  <div>
        <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #e2bef0; list-style: none;">
        <div class="container-fluid" style="background-color: #e2bef0;">
        <a class="navbar-brand"  href="#" >
        <img src="../assets/SMILJE.png" alt="" width="500" height="250" >
 
   
        </a>
   
     
        <button type="button" class="btn btn-outline" style="margin-right:7%; height:50px">Пријави се</button>
        <button type="button" class="btn btn-outline" style="margin-right:1%; height:50px">Региструј се</button>

        </div>
      </nav>
    <br>
      <br>
      <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
      <br>
      <br>
      <div class="container">
          <div class="row justify-content-center" >
            <div class="col-md-8">
              <div class="card-group mb-0">
                <div class="card p-4">
                  <div class="card-body">
                    <h1>Пријави се</h1>
                    <p class="text-muted">Пријави се на свој налог</p>
                    <div class="input-group mb-3">
                      <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-person-fill" viewBox="0 0 16 16">
                      <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                    </svg></span>
                      <input   style="height:45px" v-model="LogInDto.email" type="email"  class="form-control" placeholder="мејл">
                    </div>
                    <div class="input-group mb-4">
                      <span class="input-group-text"> <svg xmlns="http://www.w3.org/2000/svg" width="22" height="22" fill="currentColor" class="bi bi-lock-fill" viewBox="0 0 16 16">
                      <path d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2z"/>
                    </svg></span>
                      <input style="height:45px" v-model="LogInDto.password" type="password" class="form-control" placeholder="лозинка">
                
                    </div>
                 
                       <div class="input-group mb-4">
                        <div style=" margin-left:0.2%" class="row">
                          <label v-if="error != ''" style="color: red;">{{error}}</label>
                    <button @click="sub()" style="height:40px; background-color: #a868f0; border-color: darkmagenta;" type="button" class="btn btn-outline-primary  active">Пријави се</button>
                  </div>
               
                       </div>
        
                  </div>
                </div>
                <div id="logincard"  class="card text-white py-5 d-md-down-none" >
                  <div style="margin-top: 25%" class="card-body text-center">
            
                      <h2>Региструј се</h2>
                      <p>Још увијек немаш налог?</p>
                      <input style="background-color:#a868f0; border-color: darkmagenta; width: min-content; color: blanchedalmond; height: 45px;" type="button" value="Региструј се одмах!">
                  </div>
               
                </div>
                
              </div>
            </div>
          </div>
        </div>

   
      </div>


</template>

<script>

//import axios from 'axios'
   export default{
    name: 'log-in',
     data(){
       return{
         LogInDto:{
           email: '',
           password:''
         },
         user:{
           username: ''
         },
         error:''
         
       }
     },
     methods: {
      
      async sub(){
        if(this.email != '' && this.password != ''){

      this.$store.dispatch('logIn', this.LogInDto).then((response)=>{
          console.log("VRATIOOSASASASAS"+response.data.userType)
           this.user.username=this.LogInDto.username
              /*    if(response.data.userType==='ADMIN'){
                       console.log("TOKEEEEEEEEEEEEEEEEEEEEEEEEEEN"+ localStorage.token )
                        axios.post(process.env.VUE_APP_BACKEND_URL+"account/passwordStatus",this.user)
                        .then(response => {
                            if(response.data==true){
                            this.$router.push('/profileAdmin/'+this.LogInDto.username);
                        }
                        else if(response.data==false) {
                            this.$router.push('/changedPasswordInfo/'+this.LogInDto.username);
                        }
                   })   
                  }
                  else if(response.data.userType==='BOATOWNER')
                     this.$router.push('/boatOwnerHome/'+this.LogInDto.username);
                  else if(response.data.userType==='CABINOWNER')
                     this.$router.push('/cabinOwnerHome/'+this.LogInDto.username);
                  else if(response.data.userType==='FISHINGINSTRUCTOR')
                     this.$router.push('/fishingInstructorHome/'+this.LogInDto.username);
                  else if(response.data.userType==='CLIENT')
                     this.$router.push('/clientHome/'+this.LogInDto.username);
*/
                   return response; 

      }) .catch((error) => {
                   
                        if (error.response == undefined) {
                           console.log("usao")
                            this.error="Incorrect credentials.";
                        }
                    
                })
        }

      },
      goToSignUp: function() {
           this.$router.push('/signup');
      },
    }
  }

</script> 

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}
#logincard{
  width: 47%;
  background-image:  url("../assets/login.png"); 
}


</style>
