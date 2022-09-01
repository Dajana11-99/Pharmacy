<template>
    <div>
  
      <ul class="nav justify-content-center" style="background-color: #a868f0;">
  
        <li  class="nav-item">
            <a    style="color: white; width: 155px;  background-color: #c875e3 "  class="nav-link active" href="#">
         CМИЉЕ АКЦИЈЕ</a>
        </li>
        <li  class="nav-item">
            <a  :style="[proba.length>=15 ?  {'width': '155px'} : {}]" style="color: white;"  class="nav-link active" href="#"> 
                ДИЈЕТЕТСКИ СУПЛЕМЕНТИ</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white"  class="nav-link active" href="#"> ДЕРМОКОЗМЕТИКА</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white;  width: 155px; "  class="nav-link active" href="#"> MАМЕ,БЕБЕ И ДИЈЕЦА</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white;  width: 155px; "  class="nav-link active" href="#">ХИГИЈЕНА,ЊЕГА И КОЗМЕТИКА</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white;  width: 155px; "  class="nav-link active" href="#">МЕДИЦИНСКА ОПРЕМА</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white;  width: 155px; "  class="nav-link active" href="#">ЗДРАВА ХРАНА И ЧАЈЕВИ</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white;  width: 155px; "  class="nav-link active" href="#">СМИЉЕ ЗДРАВО</a>
        </li>
        <li  class="nav-item">
            <a  style="color: white; width: 155px; "  class="nav-link active" href="#">СМИЉЕ ЕКСКЛУЗИВНО</a>
        
        </li>
   
       </ul>



    </div>
  
  

  </template>
  <script>
     export default{
        name: 'nav-bar',

       data(){
         return{
           proba:'ДИЈЕТЕТСКИ СУПЛЕМЕНТИ',
           isPredefined: false,
           search: ''
         
         
         }
       },
       methods: {
  
       mounted() {
        console.log("SMEEEEE U NAV SAMO 27 karakteraaa")
        this.proba='ДИЈЕТЕТСКИ СУПЛЕМЕНТИ';
          console.log("ja sam prop mounted nav  "+ this.proba.length )
       },
     
  
      },
     
      
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
      padding: 100px;
    }
    #logincard{
      width: 47%;
      background-image:  url("../assets/SMILJE.png"); 
    }
    #nav a {
      font-weight: bold;
      color: #2c3e50;
    }
    #nav a.router-link-exact-active {
      color: #42b983;
    }
    .buttonIn {
        width: 1000px;
        margin-right: 20%;
        position: relative;
    }
     
    input {
        margin: 0px;
        padding: 0px;
        width: 100%;
        outline: none;
        height: 30px;
        border-radius: 5px;
    }
     
    button {
        position: absolute;
        top: 0;
        border-radius: 5px;
        right: 0px;
        z-index: 2;
        border: none;
        top: 2px;
        height: 30px;
        cursor: pointer;
        color: white;
        background-color: #1e90ff;
        transform: translateX(2px);
    }
    .span-or {
    display: block;
    position: absolute;
    left: 50%;
    top: -2px;
    margin-left: -25px;
    background-color: #fff;
    width: 50px;
    text-align: center;
  }
  .hr-or {
    background-color: #cdcdcd;
    height: 1px;
    margin-top: 0px !important;
    margin-bottom: 0px !important;
  }
    </style>
  
