<template>
  <div id="SignUp">
    <input placeholder="당신은 누구신가요?" v-model="nickname" type="text" @keyup="signUp">
  </div>
</template>

<script>
export default {
  name: 'SignUp',

  data() {
    return {
      nickname : ''
    }
  },

  methods: {
    signUp (e) {
      if(e.keyCode === 13 && this.nickname !== ''){
        console.log('입력한 닉네임 : ' + this.nickname);
        
        this.$axios.post('http://192.168.50.103:8080/add-user', { nickname: this.nickname })
        .then(response => {
          if(response.status === 200) (
            localStorage.setItem('nickname', this.nickname),
            window.location.href = "http://192.168.50.103:3000/enter"
          )
          })
        .catch(error => {
          if(error.response.status === 400) (
            alert(error.response.data.detailMessage)
          )
        })
      }
    }
  }
  
}
</script>
<style>
.nickname {
  color: #000000;
}
</style>