import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import axios from "axios";
import bootstrapVue from "bootstrap-vue";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

Vue.prototype.$axios = axios;
Vue.config.productionTip = false;
Vue.use(bootstrapVue);

new Vue({
  el: "#app",
  render: (h) => h(App),
  router,
});
