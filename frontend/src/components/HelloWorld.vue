<template>
      <nav  class="navbar navbar-fixed-top navbar-expand" style="background-color: #e2bef0; list-style: none;">
        <div class="container-fluid" style="background-color: #e2bef0;">
        <a class="navbar-brand"  href="#" >
        <img src="../assets/SMILJE.png" alt="" width="500" height="250" >
 
   
        </a>
   
            <div class="buttonIn">
      <input v-model="search" id="enter" class="form-control" style="height:50px; margin-top:3%; " type="search" placeholder="Претражите производ по имену или бренду" aria-label="Search">
      <button  @click='change()' id="clear" class="btn btn-outline" style="margin-top:3%; height:50px;  " type="submit">
        <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" class="bi bi-search-heart" viewBox="0 0 16 16">
        <path d="M6.5 4.482c1.664-1.673 5.825 1.254 0 5.018-5.825-3.764-1.664-6.69 0-5.018Z"/>
        <path d="M13 6.5a6.471 6.471 0 0 1-1.258 3.844c.04.03.078.062.115.098l3.85 3.85a1 1 0 0 1-1.414 1.415l-3.85-3.85a1.007 1.007 0 0 1-.1-.115h.002A6.5 6.5 0 1 1 13 6.5ZM6.5 12a5.5 5.5 0 1 0 0-11 5.5 5.5 0 0 0 0 11Z"/>
        </svg></button>
        </div>
     
        <button type="button" class="btn btn-outline" style="margin-right:7%; height:50px">Пријави се</button>
        <button type="button" class="btn btn-outline" style="margin-right:1%; height:50px">Региструј се</button>

        </div>
      </nav>
<navComponent></navComponent>
<br>
<h1 style="text-align:left;  padding-left: 2%">СМИЉЕ АКЦИЈЕ</h1>
<br>
<div style="width:100%;" class="row">
    <div class="col-md-2">
  <div style="   margin-left: 2%; height: 970px " class="card">
  <div style="text-align:left" class="card-body">
    <br>
    <b><label >Сортирај производе по:</label></b>
    <select style="margin-top: 2%" class="form-select" aria-label="Default select example">
  <option selected disabled>Изаберите начин</option>
  <option value="1">НАЈЈЕФТИНИЈИ</option>
  <option value="2">НАЈСКУПЉИ</option>
  <option value="3">НАЈБОЉЕ ОЦИЈЕЊЕНИ</option>
</select>
<hr/>

<b><label  style="margin-top: 2%">Филтрирај производе по цијени:</label></b>
<br>
<b><label  style="margin-top: 2%">Од:</label></b>
<input v-model="rangeFrom" type="range" class="form-range" min="100" max="10000" step="50" id="customRange3">
<b><label  style="margin-top: 2%">До:</label></b>
<input v-model="rangeTo" type="range" class="form-range" max="10000" min="100" step="-50" id="customRange4">
<br>
<div class="row">
  <div class="col">
    <b><label  style="margin-top: 2%">Од:</label></b>
    <input style="height:40px" v-model="rangeFrom" class="form-control"  type="number">
  </div>
  <div class="col">
    <b><label  style="margin-top: 2%">До:</label></b>
    <input style="height:40px" v-model="rangeTo" class="form-control"  type="number">
  </div>
</div>

<br>
<hr/>
<b><label  style="margin-top: 2%">БЛОГ БРИНЕМО О ВАМА:</label></b>
<br>
<br>
<ul class="list-group">
  <li style="border: #e2bef0 solid 3px" class="list-group-item ">ЗДРАВЉЕ</li>
  <br>
  <li style="border: #e2bef0 solid 3px" class="list-group-item ">ЉЕПОТА</li>
  <br>
  <li style="border: #e2bef0 solid 3px" class="list-group-item ">БЕБЕ И ДИЈЕЦА</li>
</ul>
<br>
<hr/>
  </div>
</div>
</div>
<div class="col-md-10">
  <div class="row">
<div class="col" v-for="(item, index) in items  "  :key="index">
  <div class="card" style="width: 18rem; margin-bottom: 5%">
  <img class="card-img-top" src="../assets/10452.jpg" alt="Card image cap">
  <div class="card-body">
    <h5 class="card-title">Еуцерин анти-пигмент дневна крема, 50 мл</h5>
    <hr/>
    <div class="row">
      <div style="text-align:left" class="col">
        <del>12 КМ</del>
        <b><p style="font-size:20px">9 КМ</p></b>
      </div>
      <div class="col">    
        <a href="#" style=" background-color: #a868f0; margin-top:30%" class="btn btn-primary">КУПИТЕ</a>
      </div>
    </div>
  </div>
</div>
</div>
</div>

</div>
</div>
  <footerComponent></footerComponent>
</template>
<script>
import  navComponent from './Navbar.vue'
import  footerComponent from './Footer.vue'
export default {
  name: 'app',
  data(){
         return{
          rangeFrom: 100,
          rangeTo: 10000,
          items: [],
          see: ''
         
         }
        },
  components: {
    navComponent,
    footerComponent,
  },
  mounted() {
      this.items.length =10
      console.log("AAA"+this.see)
     
       },

};
</script>

