package com.zrana.springit;

import com.zrana.springit.Repository.CommentRepository;
import com.zrana.springit.Repository.LinkRepository;
import com.zrana.springit.domain.Comment;
import com.zrana.springit.domain.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {

	private  static final Logger log = LoggerFactory.getLogger(SpringitApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SpringitApplication.class, args);
	}

	//@Bean
	CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
		return args -> {
			Link link = new Link("Getting started with spring boot 2",
					"https://therealdenvega.com/spring-boot-2");
			linkRepository.save(link);

			Comment comment = new Comment("This spring boot 2 link is awesome!", link);
			commentRepository.save(comment);

			link.addComment(comment);
			System.out.println("We just inserted link and a comment");

		};
	}
}
