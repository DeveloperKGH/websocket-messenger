import Vue from "vue";
import VueRouter from "vue-router";
import SignUp from "@/components/SignUp";
import ChattingRoom from "@/components/ChattingRoom";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  routes: [
    {
      path: "/",
      component: SignUp,
    },
    {
      path: "/enter",
      component: ChattingRoom,
    },
  ],
});

export default router;
