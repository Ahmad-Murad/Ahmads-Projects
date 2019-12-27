var url = "http://localhost:8080/User/new";
var passwordCheck = false;
var passwordMatch = false;
function back() {
    window.open('../log-in/log-in.html', '_self');
}

function register() {
    var fname = document.forms["register"]["firstname"].value;
    var lname = document.forms["register"]["lastname"].value;
    var username = document.forms["register"]["user"].value;
    var psw = document.forms["register"]["psw"].value;

     if(passwordCheck && passwordMatch) {
         var newUser = JSON.stringify({
             "userName": username,
             "password": psw,
             "firstName": fname,
             "lastName": lname
         });
         var httpRequest = new XMLHttpRequest();
         httpRequest.open( "POST", url, true);
		 httpRequest.setRequestHeader("Content-Type","application/json");
         httpRequest.send(newUser);
         httpRequest.onreadystatechange = function() {
            if( httpRequest.readyState == 4 && httpRequest.status == 200) {
                var response = httpRequest.responseText;
                if(response == "true") {
                    sessionStorage.setItem("username", username);
                    window.open('../main-page/main-page.html', '_self');
                    errorLabel.classList.add('d-none');
                } else {
                    var errorLabel = document.getElementById("errorLabel");
                    errorLabel.innerHTML = "There was an error when trying to register";
                    errorLabel.classList.remove('d-none');
                }
            }
         }
     }

    
}


function pswCheck(val) {
    var pswRegex  = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.{8,})");
    if( val != null && val.match(pswRegex)) {
        passwordCheck = true;
        getNotification(true, "pswLabel");
    } else {
        passwordCheck = false;
        getNotification(false, "pswLabel");
    }
}

function pswMatch(val) {
    var psw = document.forms["register"]["psw"].value;
    if(psw == val){
        passwordMatch = true;
        getNotification(true, "pswMatch");
    } else {
        passwordMatch = false;
        getNotification(false, "pswMatch");
    }
}

function getNotification(bool, ID) {
    var label = document.getElementById(ID);
    if (label == null) {
        label.setAttribute( 'class', 'errorMessage' );
      }

		switch(ID) {
			case "pswLabel":
                label.innerHTML = bool ? "" : "*Password should be at 8 characters long and contain at least one lowercase letter, one uppercase letter and one number";
                if(bool) {
                    label.classList.add('d-none');
                } else {
                    label.classList.remove('d-none');
                }
				break;
			default:
                label.innerHTML = bool ? "" : "*Re-entered password does not match password";
                if(bool) {
                    label.classList.add('d-none');
                } else {
                    label.classList.remove('d-none');
                }
		}
		
    return label;
}

