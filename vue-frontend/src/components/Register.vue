<template>

    <div class = "container">
        
         <div class="modal-header">
            <h3 >Register</h3>
        </div>
            

        <div class="panel-body">
            <br>
            <form id="forma">
                 <fieldset>
                <div class="form-group">
                   <input type="text" class="form-control" id="firstname" aria-describedby="emailHelp" placeholder="Enter First Name..." required>
                </div>
                <div class="form-group">
                   <input type="text" class="form-control" id="lastname" aria-describedby="emailHelp" placeholder="Enter Last Name..." required>
                </div>
                <div class="form-group">
                    <input type="password" id="password" class="form-control" placeholder="Password..." required>
                </div>
                <div class="form-group">
                    <input type="password" id="password1" class="form-control" placeholder="Retype Password..." required>
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Enter Email" required>
                </div>
                <div class="form-group">
                    <input type="text" id="adress" class="form-control" required aria-describedby="emailHelp" placeholder="Enter Adress" >
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="city" required aria-describedby="emailHelp" placeholder="Enter City" >
                </div>	
                <div class="form-group">
                    <input type="text" class="form-control" id="state" required placeholder="Enter State">
                </div>
                
                <div class="form-group">
                    <input type="text" class="form-control" id="phone" required aria-describedby="emailHelp" placeholder="Enter Phone">
                </div>
                
                <div class="form-group">
                    <input type="number" class="form-control" id="uniqnum" required aria-describedby="emailHelp" placeholder="Enter Unique Number">
                </div>

            
                <p id="error" hidden="true"></p>
                <p id="success" hidden="true"></p>
                <button type="submit" v-on:click="register" class="btn btn-lg btn-primary btn-block">Register</button>
                 </fieldset>

            </form>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data:function(){
        return{

        }

    },
    methods:{
        register:function(event){
            event.preventDefault();
            let firstname=document.getElementById("firstname").value;
            let lastname=document.getElementById("lastname").value;
            let password=document.getElementById("password").value;
            let password2=document.getElementById("password1").value;
            let email=document.getElementById("email").value;
            
            let adress=document.getElementById("adress").value;
            let city=document.getElementById("city").value;
            let state=document.getElementById("state").value;
            let phone=parseInt(document.getElementById("phone").value);
            let uniquenum=parseInt(document.getElementById("uniqnum").value);
            //alert(uloga);
            if (password !== password2){
                alert("Password did not match")
                return
            }
            if(firstname==="" || lastname==="" || password==="" || email==="" || adress==="" || city==="" || state==="" || phone==="" || uniquenum===""){
				alert("Fill all the fields");
				return;
			}
		
            console.log(firstname + lastname)
           
             axios
                .post('http://localhost:8080/register',{
                    "firstName" : firstname,
                    "lastName" : lastname,
                    "password" : password,
                    "email":email,
                    "address":adress,
                    "city":city,
                    "state" : state,
                    "telephone":phone,
                    "uniqueNum" : uniquenum
                })
                .then(response=>{
                    if (response.status == 204) {
                        alert("This email  already exists");
                        return;
                    }
                    if(response.status==200){
                        alert("Registration successful");
                        this.$router.push('/login');
            
                    }
                })
                .catch(error => {
                    alert("Neuspesno" + error)});

        }
    },
    // mounted: function(){
    //     axios
    //         .get('rest/test')
    //         .then(response => {
    //             alert("Uspesno nakacen");
    //         })
    //         .catch(error => {
    //             alert("Neuspesno");
    //         });
}
</script>

<style scoped>
.container{
    margin-top: 5vh;
    max-width: 25vw;
    padding: 40px;
    background: #f1f1f1;
    border-radius: 10px;
    align-self: center;
    border: 1px dotted grey;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif

 }

.form {
    display: block !important;
    align-items: stretch;
    padding-top: 20px;
      
 }
.input {
    display: block;
    padding: 15px 10px;
    margin-bottom: 10px;
    width: 100%;
    border: 1px solid #ddd;
}

.panel-heading{
    width: 100%;
}

.modal-header{

  border-bottom: 2px solid #2c2c2c;
  width: 100%;
  padding: 15px;
  font-size: 20px;
  font-size: 3vh
}


</style>