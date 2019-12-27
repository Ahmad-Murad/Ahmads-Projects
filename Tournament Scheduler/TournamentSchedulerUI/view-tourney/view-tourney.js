var url = 'http://localhost:8080/Tournaments/'

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
]

window.onload = function() {
  tourney_id = sessionStorage.getItem("tourneyID");
  console.log(tourney_id);
  tourneyUrl = url + tourney_id;
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
        //handle response
        var title = document.getElementById("tourney-name");
        title.innerHTML = response.name;
        var numTeams = response.numTeams;
        var teams = response.teams;
        if(numTeams > 8) {
          for( var x = 0; x < display16.length; x++) {
            e = document.getElementById(display16[x]);
            e.classList.remove('d-none');
          }
        }
        for(var i = 0; i < teams.length; i++) {
            if(numTeams > 8) {
              if(i == 16) {
                round = document.getElementById("rd-2");
                round.classList.add('current');
              } else if(i == 24) {
                round = document.getElementById("rd-3");
                round.classList.add('current');
              } else if(i == 28) {
                round = document.getElementById("rd-4");
                round.classList.add('current');
              } else if(i == 30) {
                round = document.getElementById("champion");
                round.classList.add('current');
                element = document.getElementById("winner");
                element.innerHTML = teams[i];
              }
              element = document.getElementById(id16[i]);
              element.innerHTML = teams[i];
            } else {
              if(i == 8){
                round = document.getElementById("rd-2");
                round.classList.add('current');
              } else if(i == 12){
                round = document.getElementById("rd-3");
                round.classList.add('current');
              } else if(i == 14) {
                round = document.getElementById("champion");
                round.classList.add('current');
                element = document.getElementById("winner");
                element.innerHTML = teams[i];
              }
              element = document.getElementById(id8[i]);
              element.innerHTML = teams[i];
            }
        }
    }
  };
}

function back() {
  window.open('../main-page/main-page.html', '_self');
}