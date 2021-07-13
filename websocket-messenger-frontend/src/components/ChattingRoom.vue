<template>
<body>
<div class="container">
    <h3 class=" text-center">채팅</h3>
    <div class="messaging">
      <div class="inbox_msg">
        <div class="inbox_people">
          <div class="headind_srch">
            <div class="recent_heading">
              <h4>할 일 없는 사람들</h4>
            </div>
          </div>
          <div class="inbox_chat">
            <div class="chat_list">
              <div class="chat_people" v-for="(item, idx) in attendees" :key="idx">
                <div class="chat_img"> <img src="@/assets/default_profile.png" alt="sunil"> </div>
                <div class="chat_ib">
                  <h3>{{item}}</h3>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="mesgs">
          <div class="msg_history" v-for="(item, idx) in msgs_history" :key="idx">
            <p class=" text-center" v-if="item.type === 'ENTER'">{{item.content}}</p> 
            <div class="incoming_msg" v-if="item.type === 'CHAT' && item.sender !== nickname">
              <div class="incoming_msg_img"> 
                  <img src="@/assets/default_profile.png" alt="sunil"> 
             </div>
              <div class="received_msg">
                <div class="received_withd_msg">
                    <h5>{{ item.sender }}</h5>
                    <p>{{ item.content }}</p>
                  <span class="time_date">09:05 AM</span></div>
              </div>
            </div>
            <div class="outgoing_msg" v-if="item.type === 'CHAT' && item.sender === nickname">
              <div class="sent_msg">
                <p>{{ item.content }}</p>
                <span class="time_date"> 09:05 AM</span> </div>
            </div>
          </div>
          <div class="type_msg">
            <div class="input_msg_write">
              <input type="text" class="write_msg" v-model="message" placeholder="뭐라고 할지 나도 궁금하네" @keyup="sendMessage" />
            </div>
          </div>
        </div>
      </div>
    </div>
    </div>
</body>
</template>

<script>
import Stomp from 'webstomp-client'
import SockJS from 'sockjs-client'

export default {
  name: 'ChattingRoom',

  data() {
    return {
    nickname: '',
    message: '',
    attendees: [],
    msgs_history: []
    }
  },

    created() {
        this.connect()
        this.nickname = localStorage.getItem('nickname')
    },

  methods: {
    init () {
      this.message = ""
    },
    sendMessage (e) {
      if(e.keyCode === 13 && this.sender !== '' && this.message !== ''){
        this.send()
        this.message = ''
      }
    },    
    send() {
      console.log('보낼 메세지 : ' + this.message);
      if (this.stompClient && this.stompClient.connected) {
        const msg = { 
            type: 'CHAT',
            sender: this.nickname,
            content: this.message 
        };
        this.stompClient.send("/receive", JSON.stringify(msg), {});
      }
    },    
    connect() {
      const serverURL = "http://192.168.50.103:8080"
      let socket = new SockJS(serverURL);
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect(
        {},
        frame => {
          this.connected = true;
          console.log('소켓 연결 성공!!', frame);
          
          this.$axios.get('http://192.168.50.103:8080/users')
          .then(response =>{
            this.attendees = response.data;
            })

        const chattingRoom = { 
          users: this.attendees,
          message: {
            type: 'ENTER',
            sender: this.nickname,
          }
        };

          this.stompClient.send("/enter", JSON.stringify(chattingRoom), {});
          this.stompClient.subscribe("/send", res => {
            this.attendees = JSON.parse(res.body).users
            this.msgs_history.push(JSON.parse(res.body).message)
          });
        },
        error => {
          console.log('소켓 연결 실패ㅠㅠ', error);
          this.connected = false;
        }
      );        
    }
  }
  
}
</script>
<style>
.container{max-width:1170px; margin:auto;}
img{ max-width:100%;}
.inbox_people {
  background: #f8f8f8 none repeat scroll 0 0;
  float: left;
  overflow: hidden;
  width: 40%; border-right:1px solid #c4c4c4;
}
.inbox_msg {
  display: flex;
  border: 1px solid #c4c4c4;
  clear: both;
  overflow: hidden;
}
.top_spac{ margin: 20px 0 0;}


.recent_heading {float: left; width:40%;}
.srch_bar {
  display: inline-block;
  text-align: right;
  width: 60%;
}
.headind_srch{ padding:10px 29px 10px 20px; overflow:hidden; border-bottom:1px solid #c4c4c4;}

.recent_heading h4 {
  color: #05728f;
  font-size: 21px;
  margin: auto;
}
.srch_bar input{ border:1px solid #cdcdcd; border-width:0 0 1px 0; width:80%; padding:2px 0 4px 6px; background:none;}
.srch_bar .input-group-addon button {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  padding: 0;
  color: #707070;
  font-size: 18px;
}
.srch_bar .input-group-addon { margin: 0 0 0 -27px;}

.chat_ib h5{ font-size:15px; color:#464646; margin:0 0 8px 0;}
.chat_ib h5 span{ font-size:13px; float:right;}
.chat_ib p{ font-size:14px; color:#989898; margin:auto}
.chat_img {
  float: left;
  width: 11%;
}
.chat_ib {
  float: left;
  padding: 0 0 0 15px;
  width: 88%;
}

.chat_people{ overflow:hidden; clear:both;}
.chat_list {
  border-bottom: 1px solid #c4c4c4;
  margin: 0;
  padding: 18px 16px 10px;
}
.inbox_chat { height: 550px; overflow-y: scroll;}

.active_chat{ background:#ebebeb;}

.incoming_msg_img {
  display: inline-block;
  width: 6%;
}
.received_msg {
  display: inline-block;
  padding: 0 0 0 10px;
  vertical-align: top;
  width: 92%;
 }
 .received_withd_msg p {
  background: #ebebeb none repeat scroll 0 0;
  border-radius: 3px;
  color: #646464;
  font-size: 14px;
  margin: 0;
  padding: 5px 10px 5px 12px;
  width: 100%;
}
.time_date {
  color: #747474;
  display: block;
  font-size: 12px;
  margin: 8px 0 0;
}
.received_withd_msg { width: 57%;}
.mesgs {
  padding: 30px 15px 0 25px;
  width: 60%;
  height: 80vh;
  overflow-y: auto;
}

 .sent_msg p {
  background: #05728f none repeat scroll 0 0;
  border-radius: 3px;
  font-size: 14px;
  margin: 0; color:#fff;
  padding: 5px 10px 5px 12px;
  width:100%;
}
.outgoing_msg{ overflow:hidden; margin:26px 0 26px;}
.sent_msg {
  float: right;
  width: 46%;
}
.input_msg_write input {
  background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
  border: medium none;
  color: #4c4c4c;
  font-size: 15px;
  min-height: 48px;
  width: 100%;
}

.type_msg {border-top: 1px solid #c4c4c4;position: relative;}
.msg_send_btn {
  background: #05728f none repeat scroll 0 0;
  border: medium none;
  border-radius: 50%;
  color: #fff;
  cursor: pointer;
  font-size: 17px;
  height: 33px;
  position: absolute;
  right: 0;
  top: 11px;
  width: 33px;
}
.messaging { padding: 0 0 50px 0;}
.msg_history {
  overflow-y: auto;
}
</style>
