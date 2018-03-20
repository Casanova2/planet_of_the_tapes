## IMPORTANT INFORMATION
All the API requests go with /api at the start.


### MAIN REQUESTS

Here are the request for the single product page and the product list page.  
  
* #### Product List (Movies, Videogames, Series or Packs)  
  
    
* ##### URL

	< /mplist >

* ##### Method:

	`GET`

* ##### URL Params
	* `enlace=[int]` -> Value: 1 = Series, 2 = Movies, 3 = Videogames, 4 = Packs  
  
* ##### Search example :

	* URL
	
			/mplist?enlace=1  
 
* ##### Success Response:  
        [
            {
                "id": 13,
                "porders": [],
                "name": "Game of Thrones",
                "description": "Set on the fictional continents of Westeros and Essos, Game of Thrones has several plot lines and a large ensemble cast but centers on three primary story arcs.",
                "type": "Series",
                "genre": "Fantasy",
                "stock": 4,
                "pbuy": 15,
                "prent": 2,
                "score": 85,
                "trailer": "https://www.youtube.com/watch?v=giYeaKsXnsI",
                "director": "D. B. Weiss and David Benioff",
                "cast": "Sean Bean, Mark Addy, Nikolaj Coster-Waldau, Michelle Fairley, Lena Headey, Emilia Clarke, Iain Glen, Aidan Gillen, Harry Lloyd, Kit Harington, Sophie Turner , Maisie Williams, Richard Madden, Isaac Hempstead",
                "year": 2011,
                "urlimg": "GoT.jpg",
                "selected": ""
            },
            {
                "id": 14,
                "porders": [],
                "name": "Breaking Bad",
                "description": "Walter\\'s family consists of his wife Skyler and children, Walter, Jr. and Holly. The show also features Skyler\\'s sister Marie Schrader, and her husband Hank, a Drug Enforcement Administration (DEA) agent.",
                "type": "Series",
                "genre": "Sci-fi",
                "stock": 4,
                "pbuy": 15,
                "prent": 2,
                "score": 90,
                "trailer": "https://www.youtube.com/watch?v=HhesaQXLuRY",
                "director": "Vince Gilligan",
                "cast": "Bryan Crasnton, Aaron Pual, Anna Gunn, RJ Mitte, Elanor Anne Wenrich, Betsy Brandt, Dean Norris, Bob Odenkirk, Jonathan Banks, Giancarlo Esposito, Jesse Plemon, Laura Fraser.",
                "year": 2013,
                "urlimg": "breaking-bad-.jpg",
                "selected": ""
            },
            {
                "id": 15,
                "porders": [],
                "name": "Hannibal",
                "description": "Focus on the relationship between FBI special investigator Will Graham and Dr. Hannibal Lecter, a forensic psychiatrist destined to become Graham's most cunning enemy and at the same time.",
                "type": "Series",
                "genre": "Sci-fi",
                "stock": 4,
                "pbuy": 15,
                "prent": 2,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=pDTzn8y-5kM",
                "director": "Bryan Fuller",
                "cast": "Hugh Dancy, Mads Mikkelsen",
                "year": 2015,
                "urlimg": "Hannibal.jpg",
                "selected": ""
            }
        ]  
* ##### Error Response:

	**Code:** 404 NOT FOUND  
  
    
* #### Single Product (Movies, Videogames or Series)  
  
* ##### URL

	< /product/{id} >

* ##### Method:

	`GET`

* ##### URL Params
	* `id=[int]`  
  
* ##### Search example :

	* URL
	
			/product/2    
 
* ##### Success Response:    
        {
                "id": 2,
                "porders": [],
                "name": "The Lord Of The Rings: The Fellowship of the Ring",
                "description": "Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
                "type": "Movies",
                "genre": "Fantasy",
                "stock": 5,
                "pbuy": 20,
                "prent": 3,
                "score": 99,
                "trailer": "https://www.youtube.com/watch?v=V75dMMIW2B4",
                "director": "Peter Jackson",
                "cast": "Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
                "year": 2001,
                "urlimg": "TLOTR_FL.jpg",
                "selected": ""
            }  
* ##### Error Response:

	**Code:** 404 NOT FOUND  
  
* #### Single Pack   
  
* ##### URL

	< /pack/{id} >

* ##### Method:

	`GET`

* ##### URL Params
	* `id=[int]`  
  
* ##### Search example :

	* URL
	
			/pack/2    
 
* ##### Success Response:  
  
        {
            "id": 2,
            "name": "Games Deal",
            "price": 40,
            "products": [
                {
                    "id": 19,
                    "name": "The witcher 3: Wild Hunt",
                    "description": "Based on The Witcher series of fantasy novels by Polish author Andrzej Sapkowski Players control protagonist Geralt of Rivia, a monster hunter known as a Witcher (Vedmak), is looking for his missing adopted daughter, who is on the run from the Wild Hunt.",
                    "type": "Videogames",
                    "genre": "Fantasy",
                    "stock": 5,
                    "pbuy": 50,
                    "prent": 5,
                    "score": 96,
                    "trailer": "https://www.youtube.com/watch?v=XHrskkHf958",
                    "director": "CD Projekt",
                    "cast": "Geralt, Ciri, Yenneffer, Triss, Eredin.",
                    "year": 2015,
                    "urlimg": "pc-witcher3.jpg",
                    "selected": ""
                },
                {
                    "id": 20,
                    "name": "Monster Hunter World",
                    "description": "In Monster Hunter: World, the player takes the role of a Hunter, tasked to hunt down and either kill or trap monsters that roam in one of several environmental spaces.",
                    "type": "Videogames",
                    "genre": "Fantasy",
                    "stock": 5,
                    "pbuy": 50,
                    "prent": 5,
                    "score": 80,
                    "trailer": "https://www.youtube.com/watch?v=OotQrKEqe94",
                    "director": "Capcom",
                    "cast": "Hunters and monsters",
                    "year": 2018,
                    "urlimg": "pc-monsterhunter.jpg",
                    "selected": ""
                },
                {
                    "id": 24,
                    "name": "Halo 5",
                    "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                    "type": "Videogames",
                    "genre": "Sci-Fi",
                    "stock": 5,
                    "pbuy": 50,
                    "prent": 5,
                    "score": 75,
                    "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                    "director": "343 Industries",
                    "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                    "year": 2015,
                    "urlimg": "xone-halo5.png",
                    "selected": ""
                }
            ],
            "img": "packo.jpg"
}  
  
* ##### Error Response:

	**Code:** 404 NOT FOUND  
  

## USER
Now, we will show the user requests implemented. As user, we can see and edit our profile, see our own orders, and login.  
As admin, in addition to performing user actions, we can add new users and delete them. And. as an unregistered user, we can register in the app.

* ##### Success Response:

	* HttpStatus.OK  
    * HttpStatus.NO_CONTENT  

* ##### Error Response:

	* Code: 404 NOT FOUND  
    * HttpStatus.UNAUTHORIZED

### See our profile

Show logged user data.

* ##### URL

	< /admin-user >

* ##### Method:

	`GET`
  
* ##### Success Response:
      {
            "id": 3,
            "name": "Asier",
            "passwordHash": "$2a$10$ruPeYJf1VKFjn4l8KN/xYOXqiSQN3sLDlh4/TNUvenmIEw0G2tws6",
            "dni": "7845678R",
            "email": "asier@gmail.com",
            "telephone": "606789541",
            "viewTelephone": false,
            "address": "calle falsa 123",
            "avatar": "img/admin/avatar/asier.png",
            "roles": [
                "ROLE_ADMIN",
                "ROLE_USER"
            ],
            "orders": []
}  


### Edit our profile  

Return the updated user introduced.

* ##### URL

	< /admin-modify-user/{id} >

* ##### Método:

	`PUT`  
  
* ##### URL Params
	* `id=[int]`   
      
* ##### Data Params:  
```
	{
		    "passwordHash": "$2a$10$ruPeYJf1VKFjn4l8KN/xYOXqiSQN3sLDlh4/TNUvenmIEw0G2tws6",
		    "dni": "7845678R",
		    "email": "asier@gmail.com",
		    "telephone": "606789541",
		    "address": "calle falsa 123455",
		    "avatar": "img/admin/avatar/asier.png"
	}
```
  
* ##### Success Response:  
```
	{
	    "id": 2,
	    "name": "Ruben",
	    "passwordHash": "$2a$10$ruPeYJf1VKFjn4l8KN/xYOXqiSQN3sLDlh4/TNUvenmIEw0G2tws6",
	    "dni": "7845678R",
	    "email": "asier@gmail.com",
	    "telephone": "606789541",
	    "viewTelephone": false,
	    "address": "calle falsa 123455",
	    "avatar": "img/admin/avatar/asier.png",
	    "roles": [
		"ROLE_ADMIN",
		"ROLE_USER"
	    ],
	    "orders": []
}
```  
### See our orders  

Show the logged user orders.  

* ##### URL

	< /user-orderlist >

* ##### Method:

	`GET`
  	
* ##### Success Response:
      [
            {
                "id": 3,
                "products": [
                    {
                        "id": 14,
                        "name": "Breaking Bad",
                        "description": "Walter\\'s family consists of his wife Skyler and children, Walter, Jr. and Holly. The show also features Skyler\\'s sister Marie Schrader, and her husband Hank, a Drug Enforcement Administration (DEA) agent.",
                        "type": "Series",
                        "genre": "Sci-fi",
                        "stock": 3,
                        "pbuy": 15,
                        "prent": 2,
                        "score": 90,
                        "trailer": "https://www.youtube.com/watch?v=HhesaQXLuRY",
                        "director": "Vince Gilligan",
                        "cast": "Bryan Crasnton, Aaron Pual, Anna Gunn, RJ Mitte, Elanor Anne Wenrich, Betsy Brandt, Dean Norris, Bob Odenkirk, Jonathan Banks, Giancarlo Esposito, Jesse Plemon, Laura Fraser.",
                        "year": 2013,
                        "urlimg": "breaking-bad-.jpg",
                        "selected": ""
                    },
                    {
                        "id": 3,
                        "name": "Blade Runner: 2049",
                        "description": "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
                        "type": "Movies",
                        "genre": "Sci-fi",
                        "stock": 4,
                        "pbuy": 20,
                        "prent": 3,
                        "score": 83,
                        "trailer": "https://www.youtube.com/watch?v=6T2b0mp2hco",
                        "director": "Denis Villeneuve",
                        "cast": "Harrison Ford, Ryan Gosling, Ana de Armas",
                        "year": 2017,
                        "urlimg": "BladeRunner2049.jpg",
                        "selected": ""
                    }
                ],
                "packs": [],
                "state": "completed",
                "pay": "Credit Card",
                "type": "",
                "total": 35
            }
        ]  
  
### Add User as admin
As admin, you can add new users.

* ##### URL

	< /admin-add-user >

* ##### Method:

	`POST`  
  
* ##### Data Params:  
```
	{
        "name": "Pepe",
        "passwordHash": "555",
        "dni": "7845678R",
        "email": "a@gmail.com",
        "telephone": "606789541",
        "address": "calle falsa 123455"
}
```  

* ##### Success Response:
```
    {
        "id": null,
        "name": "Pepe",
        "passwordHash": "555",
        "dni": "7845678R",
        "email": "a@gmail.com",
        "telephone": "606789541",
        "viewTelephone": false,
        "address": "calle falsa 123455",
        "avatar": "usern.png",
        "roles": "ROLE_USER",
        "orders": []
}
```  
### Delete User as admin  
As admin, you can delete existing users.  

* ##### URL

	< /admin/user/remove/{id} >

* ##### Método:

	`DELETE`

* ##### URL Params
	* Required:

		* `id=[int]`

* ##### Data Params
```
{null}  
```  
* ##### Success response:
```
{null}  
```  
### Register
As an unregistered user, you can register in app. It's similar to add user as admin.

* ##### URL

	< /register/add >

* ##### Method:

	`POST`
  
* ##### Data Params:  
```
{
    "name": "Rul",
    "passwordHash": "4567897$",
    "dni": "7894561I",
    "email": "ra@gmail.com",
    "telephone": "606056001",
    "address": "calle falsa 123"
}
```  
* ##### Success Response:  
```
{
    "id": null,
    "name": "Rul",
    "passwordHash": "4567897$",
    "dni": "7894561I",
    "email": "ra@gmail.com",
    "telephone": "606056001",
    "viewTelephone": false,
    "address": "calle falsa 123",
    "avatar": "usern.png"
    "roles": "ROLE_USER",
    "orders": []
}
```  
## PRODUCTS  
Working with products, as admin, you can add a new product, modify, delete and see all the products in database.  
  
* ##### Success Response:

	* HttpStatus.OK  
        * HttpStatus.NO_CONTENT  

* ##### Error Response:

	* Code: 404 NOT FOUND  
        * HttpStatus.UNAUTHORIZED

  
### Add product  
As admin, you can add new products.

* ##### URL

	< /admin-add-product >

* ##### Method:

	`POST`  
  
* ##### Data Params:  
```
 { 
        "name": "Alienuu",
        "description": "The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship!!.",
        "type": "Movies",
        "genre": "Terror",
        "stock": 5,
        "pbuy": 20,
        "prent": 3,
        "score": 90,
        "trailer": "https://www.youtube.com/watch?v=jQ5lPt9edzQ",
        "director": "Ridley Scott",
        "cast": "Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
        "year": 1979,
        "urlimg": "25.jpg"
    }
```  
* ##### Success Response:  
```
{
    "id": 25,
    "porders": [],
    "packs": [],
    "name": "Alienuu",
    "description": "The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship!!.",
    "type": "Movies",
    "genre": "Terror",
    "stock": 5,
    "pbuy": 20,
    "prent": 3,
    "score": 90,
    "trailer": "https://www.youtube.com/watch?v=jQ5lPt9edzQ",
    "director": "Ridley Scott",
    "cast": "Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
    "year": 1979,
    "urlimg": "25.jpg",
    "selected": null
}
```  
### Modify product  
  
As admin, you can modify an existing product.  
* ##### URL

	< /admin/product/modify/{id} >

* ##### Method:

	`PUT`  
  
  * ##### URL Params
	* Required:

		* `id=[int]`  
  
* ##### Data Params:  
```
 {
        "name": "The shininguu",
        "description": "The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
        "type": "Movies",
        "genre": "Fantasy",
        "stock": 5,
        "pbuy": 20,
        "prent": 3,
        "score": 89,
        "trailer": "https://www.youtube.com/watch?v=5Cb3ik6zP2I",
        "director": "Stanley Kubrick",
        "cast": "Jack Nicholson, Shelley Duvall, Danny Lloyd",
        "year": 1980,
        "urlimg": "Shining.jpg",
        "selected": ""
    }
```  
* ##### Success Response:  
```
{
    "id": 5,
    "porders": [],
    "packs": [],
    "name": "The shininguu",
    "description": "The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
    "type": "Movies",
    "genre": "Fantasy",
    "stock": 5,
    "pbuy": 20,
    "prent": 3,
    "score": 89,
    "trailer": "https://www.youtube.com/watch?v=5Cb3ik6zP2I",
    "director": "Stanley Kubrick",
    "cast": "Jack Nicholson, Shelley Duvall, Danny Lloyd",
    "year": 1980,
    "urlimg": "Shining.jpg",
    "selected": ""
}
```  
### Delete product  
  
As admin, you can remove an existing product.  
  
* ##### URL

	< /admin/product/remove/{id}>

* ##### Method:

	`DELETE`  
  
  * ##### URL Params
	* Required:

		* `id=[int]`  
  
* ##### Data Params
```
{null}  
```  
* ##### Success Response  
```
{null}  
```  
### See all products  
  
As admin, you can see al the products in database.  
  
* ##### URL

	< /admin-products >

* ##### Method:

	`GET`  
  
* ##### Success Response:  
```  
[
    {
        "id": 1,
        "porders": [],
        "packs": [
            {}
        ],
        "name": "Alien",
        "description": "The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship.",
        "type": "Movies",
        "genre": "Terror",
        "stock": 5,
        "pbuy": 20,
        "prent": 3,
        "score": 90,
        "trailer": "https://www.youtube.com/watch?v=jQ5lPt9edzQ",
        "director": "Ridley Scott",
        "cast": "Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
        "year": 1979,
        "urlimg": "Alien.jpg",
        "selected": ""
    },
    {
        "id": 2,
        "porders": [],
        "packs": [
            {}
        ],
        "name": "The Lord Of The Rings: The Fellowship of the Ring",
        "description": "Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
        "type": "Movies",
        "genre": "Fantasy",
        "stock": 5,
        "pbuy": 20,
        "prent": 3,
        "score": 99,
        "trailer": "https://www.youtube.com/watch?v=V75dMMIW2B4",
        "director": "Peter Jackson",
        "cast": "Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
        "year": 2001,
        "urlimg": "TLOTR_FL.jpg",
        "selected": ""
    },
    {
        "id": 3,
        "porders": [],
        "packs": [
            {}
        ],
        "name": "Blade Runner: 2049",
        "description": "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
        "type": "Movies",
        "genre": "Sci-fi",
        "stock": 5,
        "pbuy": 20,
        "prent": 3,
        "score": 83,
        "trailer": "https://www.youtube.com/watch?v=6T2b0mp2hco",
        "director": "Denis Villeneuve",
        "cast": "Harrison Ford, Ryan Gosling, Ana de Armas",
        "year": 2017,
        "urlimg": "BladeRunner2049.jpg",
        "selected": ""
    },
    ...
]
```  
## PACKS  
  
Working with packs, as admin, you can add a new pack, modify, delete and see all the packs in database.  
  
  
* ##### Success Response:

	* HttpStatus.OK  
        * HttpStatus.NO_CONTENT  

* ##### Error Response:

	* Code: 404 NOT FOUND  
        * HttpStatus.UNAUTHORIZED
  
## Add pack  
As admin, you can add new packs.

* ##### URL

	< /admin-add-pack-action/{id1}/{id2}/{id3} >

* ##### Method:

	`POST`  
    
  * ##### URL Params
	* Required:

		* `id1=[int]`  
        	* `id2=[int]`  
        	* `id3=[int]`  
  
* ##### Data Params  
```
 {
        
        "name": "New Pack",
		"price": 50
    }
```  
* ##### Success Response  
```
{
    "id": 3,
    "name": "New Pack",
    "price": 50,
    "products": [
        {
            "id": 3,
            "name": "Blade Runner: 2049",
            "description": "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
            "type": "Movies",
            "genre": "Sci-fi",
            "stock": 5,
            "pbuy": 20,
            "prent": 3,
            "score": 83,
            "trailer": "https://www.youtube.com/watch?v=6T2b0mp2hco",
            "director": "Denis Villeneuve",
            "cast": "Harrison Ford, Ryan Gosling, Ana de Armas",
            "year": 2017,
            "urlimg": "BladeRunner2049.jpg",
            "selected": ""
        },
        {
            "id": 11,
            "name": "Then shining",
            "description": "The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
            "type": "Movies",
            "genre": "Terror",
            "stock": 5,
            "pbuy": 20,
            "prent": 3,
            "score": 89,
            "trailer": "https://www.youtube.com/watch?v=5Cb3ik6zP2I",
            "director": "Stanley Kubrick",
            "cast": "Jack Nicholson, Shelley Duvall, Danny Lloyd",
            "year": 1980,
            "urlimg": "Shining.jpg",
            "selected": ""
        },
        {
            "id": 20,
            "name": "Monster Hunter World",
            "description": "In Monster Hunter: World, the player takes the role of a Hunter, tasked to hunt down and either kill or trap monsters that roam in one of several environmental spaces.",
            "type": "Videogames",
            "genre": "Fantasy",
            "stock": 5,
            "pbuy": 50,
            "prent": 5,
            "score": 80,
            "trailer": "https://www.youtube.com/watch?v=OotQrKEqe94",
            "director": "Capcom",
            "cast": "Hunters and monsters",
            "year": 2018,
            "urlimg": "pc-monsterhunter.jpg",
            "selected": ""
        }
    ],
    "img": "packi.jpg"
}
```  
## Modify pack  
  
As admin, you can modify an existing pack.  
  
* ##### URL

	< /admin/pack/modify/pack-{id}/{id1}-{id2}-{id3} >

* ##### Method:

	`PUT`  
  
  * ##### URL Params
	* Required:

		* `id=[int]`  
		* `id1=[int]`  
		* `id2=[int]`  
		* `id3=[int]`  
  
* ##### Data Params  
```
 {
        
        "name": "Games Dealing",
		"price": 50
    }
```  
* ##### Success Response  
```
{
    "id": 2,
    "name": "Games Dealing",
    "price": 50,
    "products": [
        {
            "id": 11,
            "name": "Then shining",
            "description": "The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
            "type": "Movies",
            "genre": "Terror",
            "stock": 5,
            "pbuy": 20,
            "prent": 3,
            "score": 89,
            "trailer": "https://www.youtube.com/watch?v=5Cb3ik6zP2I",
            "director": "Stanley Kubrick",
            "cast": "Jack Nicholson, Shelley Duvall, Danny Lloyd",
            "year": 1980,
            "urlimg": "Shining.jpg",
            "selected": ""
        },
        {
            "id": 12,
            "name": "Then proposal",
            "description": "The plot centers on a Canadian executive who learns that she may face deportation from the U.S. because of her expired visa.",
            "type": "Movies",
            "genre": "Comedy",
            "stock": 5,
            "pbuy": 20,
            "prent": 3,
            "score": 70,
            "trailer": "https://www.youtube.com/watch?v=RFL8b1p1ELY",
            "director": "Anne Fletcher",
            "cast": "Ryan Reynolds, Sandra Bullock",
            "year": 2009,
            "urlimg": "TheProposal.jpg",
            "selected": ""
        },
        {
            "id": 13,
            "name": "Game of Thrones",
            "description": "Set on the fictional continents of Westeros and Essos, Game of Thrones has several plot lines and a large ensemble cast but centers on three primary story arcs.",
            "type": "Series",
            "genre": "Fantasy",
            "stock": 4,
            "pbuy": 15,
            "prent": 2,
            "score": 85,
            "trailer": "https://www.youtube.com/watch?v=giYeaKsXnsI",
            "director": "D. B. Weiss and David Benioff",
            "cast": "Sean Bean, Mark Addy, Nikolaj Coster-Waldau, Michelle Fairley, Lena Headey, Emilia Clarke, Iain Glen, Aidan Gillen, Harry Lloyd, Kit Harington, Sophie Turner , Maisie Williams, Richard Madden, Isaac Hempstead",
            "year": 2011,
            "urlimg": "GoT.jpg",
            "selected": ""
        }
    ],
    "img": "packi.jpg"
}
```  
## Delete pack  
  
As admin, you can remove an existing pack.  
  
* ##### URL

	< /admin/pack/remove/{id}>

* ##### Method:

	`DELETE`  
  
  * ##### URL Params
	* Required:

		* `id=[int]`  
  
* ##### Data Params  
```
{null}  
```  
* ##### Success Response  
```
{null}  
```  
## See all packs  
  
As admin, you can see all the pack in database.  
  
* ##### URL

	< /admin-packlist >

* ##### Method:

	`GET`  
  
* ##### Success Response:   
```
[
    {
        "id": 1,
        "name": "Movies Deal",
        "price": 35,
        "products": [
            {
                "id": 1,
                "name": "Alien",
                "description": "The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship.",
                "type": "Movies",
                "genre": "Terror",
                "stock": 5,
                "pbuy": 20,
                "prent": 3,
                "score": 90,
                "trailer": "https://www.youtube.com/watch?v=jQ5lPt9edzQ",
                "director": "Ridley Scott",
                "cast": "Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
                "year": 1979,
                "urlimg": "Alien.jpg",
                "selected": ""
            },
            {
                "id": 2,
                "name": "The Lord Of The Rings: The Fellowship of the Ring",
                "description": "Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
                "type": "Movies",
                "genre": "Fantasy",
                "stock": 5,
                "pbuy": 20,
                "prent": 3,
                "score": 99,
                "trailer": "https://www.youtube.com/watch?v=V75dMMIW2B4",
                "director": "Peter Jackson",
                "cast": "Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
                "year": 2001,
                "urlimg": "TLOTR_FL.jpg",
                "selected": ""
            },
            {
                "id": 3,
                "name": "Blade Runner: 2049",
                "description": "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
                "type": "Movies",
                "genre": "Sci-fi",
                "stock": 5,
                "pbuy": 20,
                "prent": 3,
                "score": 83,
                "trailer": "https://www.youtube.com/watch?v=6T2b0mp2hco",
                "director": "Denis Villeneuve",
                "cast": "Harrison Ford, Ryan Gosling, Ana de Armas",
                "year": 2017,
                "urlimg": "BladeRunner2049.jpg",
                "selected": ""
            }
        ],
        "img": "packi.jpg"
    },
    ...
]
```  
## ORDERS  
## Create new order 
 you can add an order in progress.  
* ##### URL

	< /{id2}/buy >

* ##### Method:

	`POST`  
  
  * ##### URL Params
	* Required:

		* `id2=[int]`  
  
* ##### Data Params:  
```
 {
    "state": "progress",
    "pay": "Credit Card",
    "type": "Buy",
    "total": 450
}
```  
* ##### Success Response:  
```
{
    "id": 3,
    "products": [
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 4,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        }
    ],
    "packs": [],
    "state": "progress",
    "pay": "Credit Card",
    "type": "Buy",
    "total": 500
}
```  
## Modify order add product  
you can add a product in an existing order in progress.  
* ##### URL

	< /{id}/{id2}/buy>

* ##### Method:

	`PUT`  
  
  * ##### URL Params:
	* Required:

		* `id2=[int]` 
		* `id=[int]` 
  
* ##### Data Params:  
```
{
        "id": 2,
        "products": [
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            }
        ],
        "packs": [],
        "state": "progress",
        "pay": "Credit Card",
        "type": "Buy",
        "total": 300
    }
```  
* ##### Success Response:  
```
{
    "id": 2,
    "products": [
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 3,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        },
        {
            "id": 23,
            "name": "Gears of War 4",
            "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 5,
            "pbuy": 50,
            "prent": 5,
            "score": 80,
            "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
            "director": "The Coalition, Microsoft Studios",
            "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
            "year": 2016,
            "urlimg": "xone-gears4.jpg",
            "selected": ""
        },
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 3,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        },
        {
            "id": 23,
            "name": "Gears of War 4",
            "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 5,
            "pbuy": 50,
            "prent": 5,
            "score": 80,
            "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
            "director": "The Coalition, Microsoft Studios",
            "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
            "year": 2016,
            "urlimg": "xone-gears4.jpg",
            "selected": ""
        },
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 3,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        }
    ],
    "packs": [],
    "state": "progress",
    "pay": "Credit Card",
    "type": "Buy",
    "total": 350
}
```  
## Delete product in order  
* ##### URL

	< /{id}/{id1}/remove >

* ##### Method:

	`PUT`  
  
  * ##### URL Params
	* Required:

		* `id=[int]`
		* `id1=[int]`
  
* ##### Data Params:  
```
{
        "id": 2,
        "products": [
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            }
        ],
        "packs": [],
        "state": "progress",
        "pay": "Credit Card",
        "type": "Buy",
        "total": 300
    }
```  
* ##### Success Response:  
```
{
    "id": 2,
    "products": [
        {
            "id": 23,
            "name": "Gears of War 4",
            "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 5,
            "pbuy": 50,
            "prent": 5,
            "score": 80,
            "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
            "director": "The Coalition, Microsoft Studios",
            "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
            "year": 2016,
            "urlimg": "xone-gears4.jpg",
            "selected": ""
        },
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 4,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        },
        {
            "id": 23,
            "name": "Gears of War 4",
            "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 5,
            "pbuy": 50,
            "prent": 5,
            "score": 80,
            "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
            "director": "The Coalition, Microsoft Studios",
            "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
            "year": 2016,
            "urlimg": "xone-gears4.jpg",
            "selected": ""
        },
        {
            "id": 24,
            "name": "Halo 5",
            "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
            "type": "Videogames",
            "genre": "Sci-Fi",
            "stock": 4,
            "pbuy": 50,
            "prent": 5,
            "score": 75,
            "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
            "director": "343 Industries",
            "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
            "year": 2015,
            "urlimg": "xone-halo5.png",
            "selected": ""
        }
    ],
    "packs": [],
    "state": "progress",
    "pay": "Credit Card",
    "type": "Buy",
    "total": 300
}
```  
## See all orders  
* ##### URL

	< /admin-orderlist >

* ##### Method:

	`GET`  
	
* ##### Success Response:  
```
[
    {
        "id": 1,
        "products": [
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 4,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 4,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            }
        ],
        "packs": [],
        "state": "completed",
        "pay": "Credit Card",
        "type": "Buy",
        "total": 200
    },
    {
        "id": 2,
        "products": [
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 4,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            },
            {
                "id": 23,
                "name": "Gears of War 4",
                "description": "Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 5,
                "pbuy": 50,
                "prent": 5,
                "score": 80,
                "trailer": "https://www.youtube.com/watch?v=XrfVfRV0zSg",
                "director": "The Coalition, Microsoft Studios",
                "cast": "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio",
                "year": 2016,
                "urlimg": "xone-gears4.jpg",
                "selected": ""
            },
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 4,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            }
        ],
        "packs": [],
        "state": "progress",
        "pay": "Credit Card",
        "type": "Buy",
        "total": 300
    },
    {
        "id": 3,
        "products": [
            {
                "id": 24,
                "name": "Halo 5",
                "description": "Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
                "type": "Videogames",
                "genre": "Sci-Fi",
                "stock": 4,
                "pbuy": 50,
                "prent": 5,
                "score": 75,
                "trailer": "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
                "director": "343 Industries",
                "cast": "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes",
                "year": 2015,
                "urlimg": "xone-halo5.png",
                "selected": ""
            }
        ],
        "packs": [],
        "state": "progress",
        "pay": "Credit Card",
        "type": "Buy",
        "total": 500
    }
]
```  
