var url = 'http://localhost:8080/Tournaments/';
var updateURL = 'http://localhost:8080/Tournaments/update';
var teamsLength;
var numTeams = 0;
var tourney_id = "";

var id8 = [
  "rd-1-1",
  "rd-1-2",
  "rd-1-3",
  "rd-1-4",
  "rd-1-5",
  "rd-1-6",
  "rd-1-7",
  "rd-1-8",
  "rd-2-1",
  "rd-2-2",
  "rd-2-3",
  "rd-2-4",
  "rd-3-1",
  "rd-3-2"
];

var id16 = [
  "rd-1-1",
  "rd-1-2",
  "rd-1-3",
  "rd-1-4",
  "rd-1-5",
  "rd-1-6",
  "rd-1-7",
  "rd-1-8",
  "rd-1-9",
  "rd-1-10",
  "rd-1-11",
  "rd-1-12",
  "rd-1-13",
  "rd-1-14",
  "rd-1-15",
  "rd-1-16",
  "rd-2-1",
  "rd-2-2",
  "rd-2-3",
  "rd-2-4",
  "rd-2-5",
  "rd-2-6",
  "rd-2-7",
  "rd-2-8",
  "rd-3-1",
  "rd-3-2",
  "rd-3-3",
  "rd-3-4",
  "rd-4-1",
  "rd-4-2"
];

var display16 = [
  "display-16-1",
  "display-16-2",
  "display-16-3",
  "display-16-4",
  "display-16-5",
  "display-16-6",
  "display-16-7",
  "display-16-8"
];

var matchups = [
  "matchup1",
  "matchup2",
  "matchup3",
  "matchup4",
  "matchup5",
  "matchup6",
  "matchup7",
  "matchup8",
  "matchup9",
  "matchup10",
  "matchup11",
  "matchup12",
  "matchup13",
  "matchup14",
  "matchupC"
];

var matchups8 = [
  "matchup1",
  "matchup2",
  "matchup3",
  "matchup4",
  "matchup9",
  "matchup10",
  "matchupC"
]

var teamMatchups16 = [
  "m2-1",
  "m2-2",
  "m2-3",
  "m2-4",
  "m2-5",
  "m2-6",
  "m2-7",
  "m2-8",
  "m2-9",
  "m2-10",
  "m2-11",
  "m2-12",
  "m2-13",
  "m2-14",
  "m2-15",
  "m2-16",
  "m3-1",
  "m3-2",
  "m3-3",
  "m3-4",
  "m3-5",
  "m3-6",
  "m3-7",
  "m3-8",
  "m4-1",
  "m4-2",
  "m4-3",
  "m4-4",
  "mc-1",
  "mc-2"
];

var teamMatchups8 = [
  "m2-1",
  "m2-2",
  "m2-3",
  "m2-4",
  "m2-5",
  "m2-6",
  "m2-7",
  "m2-8",
  "m3-1",
  "m3-2",
  "m3-3",
  "m3-4",
  "mc-1",
  "mc-2"
]

window.onload = function() {
  this.tourney_id = sessionStorage.getItem("tourneyID");
  tourneyUrl = url + this.tourney_id;
  var errorLabel = document.getElementById("errorLabel");
  //get call
  var httpRequest = new XMLHttpRequest();
  httpRequest.withCredentials = false;
  httpRequest.open( "GET", tourneyUrl, true);
  console.log('Error: ' + httpRequest.status);
  httpRequest.send();			
  httpRequest.onreadystatechange = function() {
    if( httpRequest.readyState == 4 && httpRequest.status == 200) {
        response = JSON.parse(httpRequest.responseText);
        var title = document.getElementById("tourney-name");
        title.innerHTML = response.name;
        this.numTeams = response.numTeams;
        var teams = response.teams;
        sessionStorage.setItem("teamsLength", teams.length);
        if(this.numTeams > 8) {
          for( var x = 0; x < display16.length; x++) {
            e = document.getElementById(display16[x]);
            e.classList.remove('d-none');
          }
          if(teams.length < 17){
            for(var i = 0; i < 8; i++){
              matchup = document.getElementById(matchups[i]);
              matchup.classList.remove('d-none');
            }
          } else if(teams.length < 25) {
            for(var i = 8; i < 12; i++){
              matchup = document.getElementById(matchups[i]);
              matchup.classList.remove('d-none');
            }
          } else if(teams.length < 29) {
            for(var i = 12; i < 14; i++){
              matchup = document.getElementById(matchups[i]);
              matchup.classList.remove('d-none');
            }
          } else if(teams.length > 29) {
            matchup = document.getElementById(matchups[14]);
            matchup.classList.remove('d-none');
          }
        } else {
          if(teams.length < 9){
            for(var i = 0; i < 4; i++){
              matchup = document.getElementById(matchups[i]);
              matchup.classList.remove('d-none');
            }
          } else if(teams.length < 13) {
            for(var i = 8; i < 10; i++){
              matchup = document.getElementById(matchups[i]);
              matchup.classList.remove('d-none');
            } 
          } else if(teams.length > 13) {
            matchup = document.getElementById(matchups[14]);
            matchup.classList.remove('d-none');
          }
        }
        for(var i = 0; i < teams.length; i++) {
            if(numTeams > 8) {
              if(i == 23) {
                round = document.getElementById("rd-3");
                round.classList.add('current');
              } else if(i == 27) {
                round = document.getElementById("rd-4");
                round.classList.add('current');
              } else if(i == 29){
                round = document.getElementById("champion");
                round.classList.add('current');
              } else if(i == 30) {
                round = document.getElementById("champion");
                round.classList.add('current');
                element = document.getElementById("winner");
                element.innerHTML = teams[i];
              }
              element = document.getElementById(id16[i]);
              matchup = document.getElementById(teamMatchups16[i]);
              element.innerHTML = teams[i];
              matchup.value = teams[i];
              matchup.innerHTML = teams[i];
            } else {
              if(i == 11) {
                round = document.getElementById("rd-3");
                round.classList.add('current');
              } else if(i == 13) {
                round = document.getElementById("champion");
                round.classList.add('current');
              } else if(i == 14) {
                element = document.getElementById("winner");
                element.innerHTML = teams[i];
              }
              element = document.getElementById(id8[i]);
              matchup = document.getElementById(teamMatchups8[i]);
              element.innerHTML = teams[i];
              matchup.value = teams[i];
              matchup.innerHTML = teams[i];
          }
          
        }
    }
  };
}

function save() {
  var winners = [];
  var data = {};
  var teamsLength = sessionStorage.getItem("teamsLength");
  if(this.numTeams > 8){
    if(teamsLength < 17) {
      for(var i = 0; i < 8; i++){
        var winner = document.getElementById(matchups[i]);
        data = {
          "name": winner.value,
          "round1": true,
          "round2": true,
          "round3": false,
          "round4": false,
          "winner": false,
          "tourny_id": this.tourney_id
        }
        winners.push(data);
      }
    } else if(teamsLength < 25) {
      for(var i = 8; i < 12; i++){
        var winner = document.getElementById(matchups[i]);
        data = {
          "name": winner.value,
          "round1": true,
          "round2": true,
          "round3": true,
          "round4": false,
          "winner": false,
          "tourny_id": this.tourney_id
        }
        winners.push(data);
      }
    } else if(teamsLength < 29) {
      for(var i = 12; i < 14; i++){
        var winner = document.getElementById(matchups[i]);
        data = {
          "name": winner.value,
          "round1": true,
          "round2": true,
          "round3": true,
          "round4": true,
          "winner": false,
          "tourny_id": this.tourney_id
        }
        winners.push(data);
      }
    } else if(teamsLength < 31) {
      var winner = document.getElementById(matchups[14]);
      data = {
        "name": winner.value,
        "round1": true,
        "round2": true,
        "round3": true,
        "round4": true,
        "winner": true,
        "tourny_id": this.tourney_id
      }
      winners.push(data);
    }
  } else {
    if(teamsLength < 9) {
      for(var i = 0; i < 4; i++) {
        var winner = document.getElementById(matchups8[i]);
        data = {
          "name": winner.value,
          "round1": true,
          "round2": true,
          "round3": false,
          "round4": false,
          "winner": false,
          "tourny_id": this.tourney_id
        }
        winners.push(data);
      }
    } else if(teamsLength < 13) {
      for(var i = 4; i < 6; i++) {
        var winner = document.getElementById(matchups8[i]);
        data = {
          "name": winner.value,
          "round1": true,
          "round2": true,
          "round3": true,
          "round4": false,
          "winner": false,
          "tourny_id": this.tourney_id
        }
        winners.push(data);
      }
    } else if(teamsLength < 15) {
      var winner = document.getElementById(matchups8[6]);
      data = {
        "name": winner.value,
        "round1": true,
        "round2": true,
        "round3": true,
        "round4": false,
        "winner": true,
        "tourny_id": this.tourney_id
      }
      winners.push(data);
    }
  }

  var updateTourney = JSON.stringify(winners);
  var httpRequest = new XMLHttpRequest();
         httpRequest.open( "PUT", updateURL, true);
         httpRequest.setRequestHeader("Content-Type","application/json");
         httpRequest.send(updateTourney);
         httpRequest.onreadystatechange = function() {
            if( httpRequest.readyState == 4 && httpRequest.status == 200) {
              window.open('./manage-tourney.html', '_self');
			      }
         }

}

function back() {
  window.open('../main-page/main-page.html', '_self');
}