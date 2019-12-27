var url = "http://localhost:8080/Tournaments/manage/";
var response;
var ids = [
  "btn-1",
  "btn-2",
  "btn-3",
  "btn-4",
  "btn-5"
];

function back() {
  window.open('../main-page/main-page.html', '_self');
} 

window.onload = function() {
  var username = sessionStorage.getItem("username");
  var viewUrl = url + username; 
  var httpRequest = new XMLHttpRequest();
  httpRequest.withCredentials = false;
  httpRequest.open( "GET", viewUrl, true);
  console.log('Error: ' + httpRequest.status);
  httpRequest.send();			
  httpRequest.onreadystatechange = function() {
    if( httpRequest.readyState == 4 && httpRequest.status == 200) {
      response = JSON.parse(httpRequest.responseText);
      for( var i = 0; i < this.response.length; i++) {
        var btn = document.getElementById(ids[i]);
        btn.value = response[i].name;
        btn.classList.remove('d-none');
      }
    }
  }
}

function tourneySelect(i){
  var tourney_id = this.response[i].id;
  sessionStorage.setItem("tourneyID", tourney_id);
  window.open('../manage-tourney/manage-tourney.html', '_self');
}