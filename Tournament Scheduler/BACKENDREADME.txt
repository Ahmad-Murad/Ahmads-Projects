Creating a tournament url (POST) http://localhost:8080/Tournaments/new
body example:
{
	"tournament":
	{
		"name":"First",
		"size":8,
		"username":"ahmad55"
	},
	"teams":
	[
		{
			"name":"Bears",
			"round1":true,
			"round2":false,
			"round3":false,
			"round4":false,
			"winner":false
			
		},
		{
			"name":"Packers",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"Chargers",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"49'ers",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"Dolphins",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"Panthers",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"Patriots",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		},
		{
			"name":"Lions",
			"round1":true,
			"round2":false,
			"round3":false,
			"winner":false
			
		}
		
	]
	
}

Viewing a tournament url (GET) http://localhost:8080/Tournaments/{id}
output example:
{
    "name": "First",
    "numTeams": 8,
    "teams": [
        "Bears",
        "Packers",
        "Chargers",
        "49'ers",
        "Dolphins",
        "Panthers",
        "Patriots",
        "Lions"
    ]
}
Viewing the tournaments a user is in (GET) http://localhost:8080/Tournaments/access/{username}
output example:
[
    {
        "name": "Sports Bonanza",
        "id": 8
    }
]
Updating the rankings of team within a tournament (PUT) http://localhost:8080/Tournaments/update
body example:

	[
		{
			"name":"Bears",
			"round1":true,
			"round2":true,
			"round3":false,
			"round4":false,
			"tourny_id":12
		},
		{
			"name":"Patriots",
			"round1":true,
			"round2":true,
			"round3":false,
			"tourny_id":12
			
		},
		{
			"name":"Lions",
			"round1":true,
			"round2":true,
			"round3":false,
			"tourny_id":12
			
		},
		{
			"name":"49'ers",
			"round1":true,
			"round2":true,
			"round3":false,
			"tourny_id":12
			
		}
		
	]
	

