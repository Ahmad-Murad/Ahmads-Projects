var url = 'http://localhost:8080/Users/logIn/';
var response;
crossOrigin: null;
function register() {
    window.open('../register/register.html', '_self');
}

function login() {
    var username = document.forms["log-in"]["user"].value;
    var password = document.forms["log-in"]["psw"].value;
    var errorLabel = document.getElementById("errorLabel");

    if(username != "" && password != "") {
        var loginUrl = url + username + '/' + password; 
            var httpRequest = new XMLHttpRequest();
			httpRequest.withCredentials = false;
			httpRequest.open( "GET", loginUrl, true);
			console.log('Error: ' + httpRequest.status);
            httpRequest.send();			
				httpRequest.onreadystatechange = function() {
                    if( httpRequest.readyState == 4 && httpRequest.status == 200) {
                        response = httpRequest.responseText;
                        if(response == "true"){
                            sessionStorage.setItem("username", username);
                            window.open('../main-page/main-page.html', '_self');
                        } else {
                            errorLabel.innerHTML = "There was an error when trying to log in";
                            errorLabel.classList.toggle('d-none');
                        }
                    }
				};	
    } else {
        errorLabel.innerHTML = "Please enter a username and/or password";
        errorLabel.classList.toggle('d-none');
    }
}
