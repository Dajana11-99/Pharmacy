import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import VueSweetalert2 from 'vue-sweetalert2';
import 'sweetalert2/dist/sweetalert2.min.css';
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import store from '../src/store/index'

createApp(App).use(router).use(VueSweetalert2).use(store).mount('#app')
