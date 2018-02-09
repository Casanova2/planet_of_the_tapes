package com.planet_of_the_tapes.entity;


import java.util.ArrayList;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.planet_of_the_tapes.repository.*;

import com.planet_of_the_tapes.entity.User;
import com.planet_of_the_tapes.entity.Product;

@Component
public class DataExamples {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostConstruct
	private void initDatabase() {

		// Data declaration

		User user1, user2, user3;
		Product prod1, prod2, prod3, prod4, prod5, prod6;

	// Users creation
			user1 = new User("carlos", "1234", "4567891", "elmio@gmail.com",
					"656565066", "ROLE_USER");
			userRepository.save(user1);
			
			
			user2 = new User("raul", "4321", "7894561", "eltuyo@gmail.com",
					"606000000", "ROLE_ADMIN");
			userRepository.save(user2);
			
			user3 = new User("Rubén", "4321", "7894561", "eltuyo@gmail.com",
					"606000000", "ROLE_ADMIN");
			userRepository.save(user3);
			
	// Products Creation
			prod1 = new Product("Alien", "The film's title refers to a highly aggressive extraterrestrial creature that stalks and attacks the crew of a spaceship.",
					"movie", "Terror", 5, 20.0, 3.0, 90, "https://www.youtube.com/watch?v=jQ5lPt9edzQ",
					"Ridley Scott", "Sigourney Weaver, Tom Skerritt, Veronica Cartwright, Harry Dean Stanton, John Hurt, Ian Holm and Yaphet Kotto",
					 1979, "Alien.jpg");
			productRepository.save(prod1);
			
			prod2 = new Product("The Lord Of The Rings: The Fellowship of the Ring", "Set in Middle-earth, the story tells of the Dark Lord Sauron, who is seeking the One Ring. The fate of Middle-earth hangs in the balance as Frodo and eight companios.",
				    "Movie", "Fantasy", 5, 20.0, 3.0, 99, "https://www.youtube.com/watch?v=V75dMMIW2B4", 
				    "Peter Jackson", "Elijah Wood, Ian McKellen, Sean Astin, Viggo Mortensen, John Rhys-Davies, Orlando Bloom, Sean Bean, Liv Tyler, Cate Blanchett, Christopher Lee, Hugo Weaving, Sala Baker,Andy Serkis",
				    2001, "TLOTR_FL.jpg");
			productRepository.save(prod2);
			
			prod3 = new Product("Game of Thrones", "Set on the fictional continents of Westeros and Essos, Game of Thrones has several plot lines and a large ensemble cast but centers on three primary story arcs.",
					"Serie", "Fantasy", 4, 15.0, 2.0, 85, "https://www.youtube.com/watch?v=giYeaKsXnsI", "D. B. Weiss and David Benioff",
					"Sean Bean, Mark Addy, Nikolaj Coster-Waldau, Michelle Fairley, Lena Headey, Emilia Clarke, Iain Glen, Aidan Gillen, Harry Lloyd, Kit Harington, Sophie Turner , Maisie Williams, Richard Madden, Isaac Hempstead",
					2011, "img/Series/GoT.jpg");
			productRepository.save(prod3);
			
			prod4 = new Product("Breaking Bad", "Walter\\'s family consists of his wife Skyler and children, Walter, Jr. and Holly. The show also features Skyler\\'s sister Marie Schrader, and her husband Hank, a Drug Enforcement Administration (DEA) agent.",
					"Serie", "Sci-Fi", 4, 15.0, 2.0, 90, "https://www.youtube.com/watch?v=HhesaQXLuRY", "Vince Gilligan", "Bryan Crasnton, Aaron Pual, Anna Gunn, RJ Mitte, Elanor Anne Wenrich, Betsy Brandt, Dean Norris, Bob Odenkirk, Jonathan Banks, Giancarlo Esposito, Jesse Plemon, Laura Fraser.",
					2013, "img/Series/breaking-bad-.jpg");
			productRepository.save(prod4);
			
			prod5 = new Product("The witcher 3: Wild Hunt", "Based on The Witcher series of fantasy novels by Polish author Andrzej Sapkowski Players control protagonist Geralt of Rivia, a monster hunter known as a Witcher (Vedmak), is looking for his missing adopted daughter, who is on the run from the Wild Hunt.",
					"Game", "Fantasy", 5, 50.0, 5.0, 96, "https://www.youtube.com/watch?v=XHrskkHf958", "CD Projekt", "Geralt, Ciri, Jenneffer, Triss, Eredin.",
					2015, "img/Games/pc-witcher3.jpg");
			productRepository.save(prod5);
			
			prod6 = new Product("Monster Hunter World", "In Monster Hunter: World, the player takes the role of a Hunter, tasked to hunt down and either kill or trap monsters that roam in one of several environmental spaces.",
					"Game", "Fantasy", 5, 50.0, 5.0, 80, "https://www.youtube.com/watch?v=OotQrKEqe94", "Capcom", "Hunters and monsters",
					2018, "img/Games/pc-monsterhunter.jpg");
			productRepository.save(prod6);
			
			
			
			
			
	}
	
}
