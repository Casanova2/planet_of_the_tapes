package com.planetofthetapes.entity;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planetofthetapes.entity.Product;
import com.planetofthetapes.entity.User;
import com.planetofthetapes.repository.*;

import java.util.List;

@Component
public class DataExamples {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private POrderRepository porderRepository;
	
	@Autowired
	private PackRepository packRepository;

	@PostConstruct
	private void initDatabase() {

		// Data declaration

		User user1, user2, user3, user4;
		Product prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11, prod12, prod100, prod101,
				prod102, prod103, prod104, prod105,prod106, prod107, prod108, prod109, prod110, prod111, prod200, prod201, prod202, prod203, prod204, prod205,
				prod206, prod207, prod208, prod209, prod210, prod211;

		// Users creation

		user2 = new User("Raul", "4321", "7894561I", "raul@gmail.com", "606056001", "calle falsa 123",
				"img/admin/avatar/raul.png", "ROLE_ADMIN", "ROLE_USER");
		userRepository.save(user2);

		user3 = new User("Ruben", "2143", "7874541C", "ruben@gmail.com", "606000000", "calle falsa 123",
				"img/admin/avatar/ruben.png", "ROLE_ADMIN", "ROLE_USER");
		userRepository.save(user3);

		user4 = new User("Asier", "1111", "7845678R", "asierruano@gmail.com", "606789541", "calle falsa 123",
				"img/admin/avatar/asier.png", "ROLE_ADMIN", "ROLE_USER");
		userRepository.save(user4);

		// Products Creation

		// MOVIES
		prod1 = new Product("Alien",
				"The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship.",
				"Movies", "Terror", 5, 20.0, 3.0, 90, "https://www.youtube.com/watch?v=jQ5lPt9edzQ", "Ridley Scott",
				"Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
				1979, "Alien.jpg");
		productRepository.save(prod1);

		prod2 = new Product("The Lord Of The Rings: The Fellowship of the Ring",
				"Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
				"Movies", "Fantasy", 5, 20.0, 3.0, 99, "https://www.youtube.com/watch?v=V75dMMIW2B4", "Peter Jackson",
				"Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
				2001, "TLOTR_FL.jpg");
		productRepository.save(prod2);

		prod3 = new Product("Blade Runner: 2049",
				"Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
				"Movies", "Sci-fi", 5, 20.0, 3.0, 83, "https://www.youtube.com/watch?v=6T2b0mp2hco", "Denis Villeneuve",
				"Harrison Ford, Ryan Gosling, Ana de Armas", 2017, "BladeRunner2049.jpg");
		productRepository.save(prod3);

		prod4 = new Product("Back to the Future",
				" It stars Marty McFly, who is sent back in time to 1955, where he meets his future parents in high school and accidentally becomes his mother's romantic interest.",
				"Movies", "Sci-fi", 5, 20.0, 3.0, 88, "https://www.youtube.com/watch?v=qvsgGtivCgs", "Robert Zemeckis",
				"Michael J. Fox, Christopher Lloyd", 1985, "BTTF.jpg");
		productRepository.save(prod4);

		prod5 = new Product("The shining",
				"The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
				"Movies", "Terror", 5, 20.0, 3.0, 89, "https://www.youtube.com/watch?v=5Cb3ik6zP2I", "Stanley Kubrick",
				"Jack Nicholson, Shelley Duvall, Danny Lloyd", 1980, "Shining.jpg");
		productRepository.save(prod5);

		prod6 = new Product("The proposal",
				"The plot centers on a Canadian executive who learns that she may face deportation from the U.S. because of her expired visa.",
				"Movies", "Comedy", 5, 20.0, 3.0, 70, "https://www.youtube.com/watch?v=RFL8b1p1ELY", "Anne Fletcher",
				"Ryan Reynolds, Sandra Bullock", 2009, "TheProposal.jpg");
		productRepository.save(prod6);

		prod7 = new Product("Alienn",
				"The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship.",
				"Movies", "Terror", 5, 20.0, 3.0, 90, "https://www.youtube.com/watch?v=jQ5lPt9edzQ", "Ridley Scott",
				"Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
				1979, "Alien.jpg");
		productRepository.save(prod7);

		prod8 = new Product("Then Lord Of The Rings: The Fellowship of the Ring",
				"Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
				"Movies", "Fantasy", 5, 20.0, 3.0, 99, "https://www.youtube.com/watch?v=V75dMMIW2B4", "Peter Jackson",
				"Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
				2001, "TLOTR_FL.jpg");
		productRepository.save(prod8);

		prod9 = new Product("Bladen Runner: 2049",
				"Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos.",
				"Movies", "Sci-fi", 5, 20.0, 3.0, 83, "https://www.youtube.com/watch?v=6T2b0mp2hco", "Denis Villeneuve",
				"Harrison Ford, Ryan Gosling, Ana de Armas", 2017, "BladeRunner2049.jpg");
		productRepository.save(prod9);

		prod10 = new Product("Backn to the Future",
				" It stars Marty McFly, who is sent back in time to 1955, where he meets his future parents in high school and accidentally becomes his mother's romantic interest.",
				"Movies", "Sci-fi", 5, 20.0, 3.0, 88, "https://www.youtube.com/watch?v=qvsgGtivCgs", "Robert Zemeckis",
				"Michael J. Fox, Christopher Lloyd", 1985, "BTTF.jpg");
		productRepository.save(prod10);

		prod11 = new Product("Then shining",
				"The Shining is about Jack Torrance, an aspiring writer and recovering alcoholic, who accepts a position as the off-season caretaker of the isolated historic Overlook Hotel in the Colorado Rockies.",
				"Movies", "Terror", 5, 20.0, 3.0, 89, "https://www.youtube.com/watch?v=5Cb3ik6zP2I", "Stanley Kubrick",
				"Jack Nicholson, Shelley Duvall, Danny Lloyd", 1980, "Shining.jpg");
		productRepository.save(prod11);

		prod12 = new Product("Then proposal",
				"The plot centers on a Canadian executive who learns that she may face deportation from the U.S. because of her expired visa.",
				"Movies", "Comedy", 5, 20.0, 3.0, 70, "https://www.youtube.com/watch?v=RFL8b1p1ELY", "Anne Fletcher",
				"Ryan Reynolds, Sandra Bullock", 2009, "TheProposal.jpg");
		productRepository.save(prod12);
		// END MOVIES

		// SERIES
		prod100 = new Product("Game of Thrones",
				"Set on the fictional continents of Westeros and Essos, Game of Thrones has several plot lines and a large ensemble cast but centers on three primary story arcs.",
				"Series", "Fantasy", 4, 15.0, 2.0, 85, "https://www.youtube.com/watch?v=giYeaKsXnsI",
				"D. B. Weiss and David Benioff",
				"Sean Bean, Mark Addy, Nikolaj Coster-Waldau, Michelle Fairley, Lena Headey, Emilia Clarke, Iain Glen, Aidan Gillen, Harry Lloyd, Kit Harington, Sophie Turner , Maisie Williams, Richard Madden, Isaac Hempstead",
				2011, "GoT.jpg");
		productRepository.save(prod100);

		prod101 = new Product("Breaking Bad",
				"Walter\\'s family consists of his wife Skyler and children, Walter, Jr. and Holly. The show also features Skyler\\'s sister Marie Schrader, and her husband Hank, a Drug Enforcement Administration (DEA) agent.",
				"Series", "Sci-fi", 4, 15.0, 2.0, 90, "https://www.youtube.com/watch?v=HhesaQXLuRY", "Vince Gilligan",
				"Bryan Crasnton, Aaron Pual, Anna Gunn, RJ Mitte, Elanor Anne Wenrich, Betsy Brandt, Dean Norris, Bob Odenkirk, Jonathan Banks, Giancarlo Esposito, Jesse Plemon, Laura Fraser.",
				2013, "breaking-bad-.jpg");
		productRepository.save(prod101);

		prod102 = new Product("Hannibal",
				"Focus on the relationship between FBI special investigator Will Graham and Dr. Hannibal Lecter, a forensic psychiatrist destined to become Graham's most cunning enemy and at the same time.",
				"Series", "Sci-fi", 4, 15.0, 2.0, 75, "https://www.youtube.com/watch?v=pDTzn8y-5kM", "Bryan Fuller",
				"Hugh Dancy, Mads Mikkelsen", 2015, "Hannibal.jpg");
		productRepository.save(prod102);

		prod103 = new Product("La que se Avecina",
				"The show is set in Mirador de Montepinar, a condominium located in the suburbs of Madrid.", "Series",
				"Comedy", 4, 15.0, 2.0, 84, "https://www.youtube.com/watch?v=Z5XnA7xCoZk",
				"Alberto Caballero, Laura Caballero",
				"Jose Luis Gil, Cristina Castaño, Jordi Sanchez, Pablo Chiapella, Ernesto Sevilla, Paz Padilla and more",
				2007, "LQSA.jpg");
		productRepository.save(prod103);

		prod104 = new Product("Sons of Anarchy",
				"The series explores vigilantism, government corruption and racism, and depicts an outlaw motorcycle club as an analogy for human transformation.",
				"Series", "Sci-Fi", 4, 15.0, 2.0, 95, "https://www.youtube.com/watch?v=_03DBXL3Srw", "Kurt Sutter",
				"Charlie Hunnam, Katey Sagal, Kim Coates, Drea de Matteo, Theo Rossi and more", 2008,
				"sons.jpg");
		productRepository.save(prod104);

		prod105 = new Product("Rick And Morty",
				"The series follows the misadventures of cynical mad scientist Rick Sanchez and his fretful, easily influenced grandson Morty Smith.",
				"Series", "Sci-Fi", 4, 15.0, 2.0, 99, "https://www.youtube.com/watch?v=WNhH00OIPP0",
				"Justin Roiland and Dan Harmon", "Justin Roiland, Chris Parnell, Spencer Grammer, and Sarah Chalke",
				2013, "rick-and-morty.png");
		productRepository.save(prod105);
		
		prod106 = new Product("Black Mirror",
				"It examines modern society, particularly with regard to the unanticipated consequences of new technologies.",
				"Series", "Sci-Fi", 4, 15.0, 2.0, 80, "https://www.youtube.com/watch?v=jROLrhQkK78",
				"Charlie Brooker", "Bryce Dallas Howard, Alice Eve, James Norton, Cherry Jones, Wyatt Russell, Alex Lawther, Jerome Flynn, Gugu Mbatha-Raw",
				2015, "blackMirror.png");
		productRepository.save(prod106);
		
		prod107 = new Product("How I met your mother",
				"The series concerns the adventures of Ted Mosby narrating the story of how he met the mother of his children.",
				"Series", "Comedy", 4, 15.0, 2.0, 92, "https://www.youtube.com/watch?v=aJtVL2_fA5w",
				"Craig Thomas and Carter Bays", "Josh Radnor, Jason Segel, Cobie Smulders, Neil Patrick Harris, Alyson Hannigan.",
				2005, "himym.jpg");
		productRepository.save(prod107);
		
		prod108 = new Product("Two and a half men",
				"The series is about a hedonistic jingle writer, Charlie Harper, his uptight brother Alan, and Alan's troublesome son Jake.",
				"Series", "Comedy", 4, 15.0, 2.0, 70, "https://www.youtube.com/watch?v=ypMtP5qfpfE",
				"JJames Widdoes", "Charlie Sheen, Jon Cryer, and Angus T. Jones",
				2003, "taahm.jpg");
		productRepository.save(prod108);
		
		prod109 = new Product("American Horror Story",
				"Each season is conceived as a self-contained miniseries, following a different set of characters and settings, and a storyline with its own.",
				"Series", "Terror", 4, 15.0, 2.0, 88, "https://www.youtube.com/watch?v=norWW9ExdPg",
				"Ryan Murphy and Brad Falchuk", "Dylan McDermott, Jessica Lange, Taisa Farmiga, Evan Peters",
				2011, "ahs.png");
		productRepository.save(prod109);
		
		prod110 = new Product("The Walking Dead",
				"Sheriff's deputy Rick Grimes awakens from a coma discovering a world overrun by zombies, commonly referred to as walkers.",
				"Series", "Terror", 4, 15.0, 2.0, 70, "https://www.youtube.com/watch?v=R1v0uFms68U",
				"Frank Darabont", "Andrew Lincoln, Jon Bernthal, Jeffrey DeMunn, Scott Wilson, Jeffrey Dean Morgan, Norman Reedus.",
				2013, "TheWalkingDead.jpg");
		productRepository.save(prod110);
		
		prod111 = new Product("The 100",
				"The series follows a group of post-apocalyptic survivors, chiefly a group of adolescents.",
				"Series", "Sci-Fi", 4, 15.0, 2.0, 86, "https://www.youtube.com/watch?v=ia1Fbg96vL0",
				"Jason Rothenberg", "Eliza Taylor, Paige Turco, Thomas McDonell, Eli Goree, Marie Avgeropoulos, Bob Morley, Kelly Hu",
				2014, "los100.jpg");
		productRepository.save(prod111);
		// END SERIES

		// VIDEOGAMES
		prod200 = new Product("The witcher 3: Wild Hunt",
				"Based on The Witcher series of fantasy novels by Polish author Andrzej Sapkowski Players control protagonist Geralt of Rivia, a monster hunter known as a Witcher (Vedmak), is looking for his missing adopted daughter, who is on the run from the Wild Hunt.",
				"Videogames", "Fantasy", 5, 50.0, 5.0, 96, "https://www.youtube.com/watch?v=XHrskkHf958", "CD Projekt",
				"Geralt, Ciri, Jenneffer, Triss, Eredin.", 2015, "pc-witcher3.jpg");
				
		productRepository.save(prod200);

		prod201 = new Product("Monster Hunter World",
				"In Monster Hunter: World, the player takes the role of a Hunter, tasked to hunt down and either kill or trap monsters that roam in one of several environmental spaces.",
				"Videogames", "Fantasy", 5, 50.0, 5.0, 80, "https://www.youtube.com/watch?v=OotQrKEqe94", "Capcom",
				"Hunters and monsters", 2018, "pc-monsterhunter.jpg");
		productRepository.save(prod201);

		prod202 = new Product("Mario Kart 8 Deluxe",
				"players control characters from the Mario franchise and participate in kart racing on various race tracks, using items to hinder opponents or gain advantages.",
				"Videogames", "Sci-fi", 5, 50.0, 5.0, 86, "https://www.youtube.com/watch?v=tKlRN2YpxRE", "Nintendo",
				"Mario and Company", 2017, "switch-kart8.jpg");
		productRepository.save(prod202);

		prod203 = new Product("Uncharted 4: A thief's End",
				"It is the final Uncharted game to feature protagonist Nathan Drake. Drake, reunites with his estranged older brother Sam and longtime partner Sully to search for Captain Henry Avery's lost treasure.",
				"Videogames", "Sci-fi", 5, 50.0, 5.0, 91, "https://www.youtube.com/watch?v=WNDGQMz1fJQ", "Naughty Dog",
				"Nathan Drake, Victor Sullivan, Elena", 2017, "ps4-uncharted4.jpg");
		productRepository.save(prod203);
		
		prod204 = new Product("Gears of War 4",
				"Gears of War 4 takes place 25 years after the events of Gears of War 3. Although the use of the Imulsion Countermeasure weapon destroyed all Imulsion on the planet Sera, killing the Locust and the Lambent in the process.",
				"Videogames", "Sci-Fi", 5, 50.0, 5.0, 80, "https://www.youtube.com/watch?v=XrfVfRV0zSg",
				"The Coalition, Microsoft Studios", "Angel Desai, Justina Machado, Jimmy Smits, John DiMaggio", 2016,
				"xone-gears4.jpg");
		productRepository.save(prod204);

		prod205 = new Product("Halo 5",
				"Halo 5: Guardians takes place in the year 2558, and is set eight months after the events of Halo 4. The game follows the human fireteams Blue Team and Fireteam Osiris.",
				"Videogames", "Sci-Fi", 5, 50.0, 5.0, 75, "https://www.youtube.com/watch?v=Rh_NXwqFvHc",
				"343 Industries", "Brittany Uomoleale, Michelle Lukes, Laura Bailey, Steve Downes", 2015,
				"xone-halo5.png");
		productRepository.save(prod205);
		
		prod206 = new Product("The Legend of Zelda: breath of the Wild",
				"The story follows Link, who awakens from a hundred-year slumber to a mysterious voice that guides him to defeat Calamity Ganon before he can destroy the kingdom of Hyrule.",
				"Videogames", "Fantasy", 5, 50.0, 5.0, 100, "https://www.youtube.com/watch?v=zw47_q9wbBE",
				"Nintendo", "Link, Zelda, Ganon, Impa, Deku tree", 2017,
				"switch-zelda.jpg");
		productRepository.save(prod206);
		
		prod207 = new Product("Splatoon 2",
				"Splatoon 2 is a multiplayer third-person shooter in which players control characters known as Inklings and use colored ink to attack opponents and clear goals.",
				"Videogames", "Sci-Fi", 5, 50.0, 5.0, 80, "https://www.youtube.com/watch?v=qN4w5D2tzME",
				"Nintendo", "Inklings everywhere", 2017,
				"switch-splatoon2.jpg");
		productRepository.save(prod207);
		
		prod208 = new Product("Forza Motorsport 7",
				"Forza Motorsport 7 is a racing video game featuring over 700 cars—including new Forza Edition cars, and more than 200 different configurations to race on across 32 locations at launch.",
				"Videogames", "Sci-Fi", 5, 50.0, 5.0, 88, "https://www.youtube.com/watch?v=Dokpy_KIuyA",
				"Turn 10 Studios", "Ultimate designed cars", 2017,
				"xone-forzam7.jpg");
		productRepository.save(prod208);
		
		prod209 = new Product("Lego Worlds",
				"The game allows players to build constructions in a 3D procedurally generated world.",
				"Videogames", "Fantasy", 5, 50.0, 5.0, 68, "https://www.youtube.com/watch?v=frRfo4sKxtk",
				 "Warner Bros. Interactive Entertainment", "Lego figurines", 2017,
				"xone-legoworlds.jpg");
		productRepository.save(prod209);
		
		prod210 = new Product("Sunset Overdrive",
				"The game is set in 2027, in a fictional metropolis called Sunset City. The player controls an employee of FizzCo, who has to fight off the OD.",
				"Videogames", "Sci-Fi", 5, 50.0, 5.0, 70, "https://www.youtube.com/watch?v=FqJdZQPBDF0",
				"Insomniac Games", "Multiple crazy characters", 2014,
				"xone-sunset.png");
		productRepository.save(prod210);
		
		prod211 = new Product("Resident Evil 7",
				"The player controls Ethan Winters as he searches for his wife in a derelict plantation occupied by the cannibalistic Baker family.",
				"Videogames", "Terror", 5, 50.0, 5.0, 86, "https://www.youtube.com/watch?v=W1OUs3HwIuo",
				"Capcom", "BEthan Winters, Baker Family", 2017,
				"pc-RE7.jpg");
		productRepository.save(prod211);
		// VIDEOGAMES
		
		//ORDERS
		user1 = new User("Carlos", "1234", "4567891L", "carlos@gmail.com", "656565066", "calle falsa 123",
				"img/admin/avatar/carlos.png", "ROLE_ADMIN", "ROLE_USER");
		userRepository.save(user1);
		
		
		POrder order1 = new POrder("completed", "Credit Card", "Buy", 200.00);
		POrder order2 = new POrder("progress", "Credit Card", "Buy", 300.00);
		porderRepository.save(order1);
		porderRepository.save(order2);
		
		order1.getProducts().add(prod205);
		order1.getProducts().add(prod204);
		
		order2.getProducts().add(prod205);
		order2.getProducts().add(prod204);
		
		porderRepository.save(order1);
		porderRepository.save(order2);
		
		user1.getOrders().add(order1);
		user1.getOrders().add(order2);
		userRepository.save(user1);
		
		
		
		
			
		/*List<Product> filmlist = new ArrayList<Product>();
		
		List<POrder> orderlist = new ArrayList<POrder>();
		
		
		filmlist.add(prod1);
		filmlist.add(prod2);
		filmlist.add(prod3);
		filmlist.add(prod4);
	
		
<<<<<<< HEAD
		POrder order2 = new POrder("Payed", "Credit Card", "Buy", 80.00, user3);
		order2.setProducts(filmlist);
=======
		POrder order2 = new POrder(filmlist, "Payed", "Credit Card", "Buy", 80.00, user3);
		
		POrder order3 = new POrder(filmlist, "progress", "Credit Card", "Buy", 40.00, user2);
		
>>>>>>> 09e8f5c6a918b36779043add2142b45795eb9149
		
		gamelist.add(prod200);
		gamelist.add(prod201);
		gamelist.add(prod202);
		gamelist.add(prod203);
		orderlist.add(order1);
		orderlist.add(order2);
<<<<<<< HEAD
		
		
=======
		//orderlist.add(order3);
		porderRepository.save(order1);
>>>>>>> 09e8f5c6a918b36779043add2142b45795eb9149
		porderRepository.save(order2);
		//porderRepository.save(order3);
		
		
		user2.getOrders().add(order2);
		
		// PACKS
		Pack packpelis = new Pack("Peliculas Oferta",35,filmlist);
		
		
		packRepository.save(packpelis);*/
		//Pack packjuegos = new Pack("Juegos Oferta",60,gamelist);
		//packRepository.save(packjuegos);
		
		
		
		
		

	}

}
