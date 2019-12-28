var teams = [];
var tourneyName = "";
var url = "http://localhost:8080/Tournaments/new";
var addUsersCount = 0;
var usersToAdd = [];

function back() {
    window.open('../main-page/main-page.html', '_self');
}

function create() {
    var username = sessionStorage.getItem("username");
	console.log(username);
    var numTeams = document.forms["create-tourney"]["numTeams"].value;
    tourneyName = document.forms["create-tourney"]["tourneyName"].value;
    var errorLabel = document.getElementById("errorLabel");
    var noName = document.getElementById("nameErrorLabel");
    var nameError = false;
    var teamError = false;

    if(tourneyName == "") {
        noName.innerHTML = "Please enter tournament name";
        noName.classList.remove('d-none');
        nameError = true;
    } else {
        noName.classList.add('d-none');
        nameError = false;
    }

    var teamElements = document.getElementById("teamNames").children;
    var length = 0;
    if(numTeams == "8") {
        length = 13;
    } else {
        length = 25
    }
    
    for(var i = 0; i < length; i++) {
        var element = teamElements[i];
        if( element.value != "" && element.type == "text") {
            teamError = false
            var teamName = {
                "name": element.value,
                "round1": true,
                "round2": false,
                "round3": false,
                "round4": false,
                "winner": false,
            };
            teams.push(teamName);
        } else if(element.value == "" && element.type == "text") {
            errorLabel.innerHTML = "Please enter all team/player names";
            errorLabel.classList.remove('d-none');
            teamError = true;
            break;
        }
    }

    if(!teamError) {
        errorLabel.classList.add('d-none')
    }

    if(!teamError && !nameError) {
         var tournament = {
             "username": username,
             "name": tourneyName,
             "size": parseInt(numTeams)
         };
        var createTourny = JSON.stringify({
             "tournament": tournament,
             "teams": teams,
             "invitees": this.usersToAdd
         })
         var httpRequest = new XMLHttpRequest();
         httpRequest.open( "POST", url, true);
         httpRequest.setRequestHeader("Content-Type","application/json");
         httpRequest.send(createTourny);
         httpRequest.onreadystatechange = function() {
            if( httpRequest.readyState == 4 && httpRequest.status == 200) {
                var response = httpRequest.responseText;
                 sessionStorage.setItem("tourneyID", response);
                 window.open('../view-tourney/view-tourney.html', '_self');
                 errorLabel.classList.add('d-none');
				
			}
         }
        
    }
   
}

function teamsUpdate() {
    var numTeams = document.forms["create-tourney"]["numTeams"].value;
    var display = document.getElementById("teamNames").children;

    if(numTeams == "8") {
        for(var i = 13; i < display.length; i++) {
            var element = display[i];
            element.classList.add('d-none');
        }
    } else {
        for(var i = 13; i < display.length; i++){
            var element = display[i];
            element.classList.remove('d-none');
        }
    }
}

function addUser() {
    var addUser = document.getElementById('addUser');
    var user = addUser.value;
    var label = document.getElementById('viewers');
    if(user != '') {
        this.usersToAdd.push(user);
        label.classList.remove('d-none');
        console.log(this.usersToAdd);
        if(this.addUsersCount == 0){
            label.innerHTML = "Can View: " + user;
            this.addUsersCount++;
        } else {
            label.innerHTML = label.innerHTML + ", " + user
        }
    }
}
