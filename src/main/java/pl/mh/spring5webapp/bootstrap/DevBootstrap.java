package pl.mh.spring5webapp.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.mh.spring5webapp.model.Author;
import pl.mh.spring5webapp.model.Book;
import pl.mh.spring5webapp.model.Publisher;
import pl.mh.spring5webapp.repositories.AuthorRepository;
import pl.mh.spring5webapp.repositories.BookRepository;
import pl.mh.spring5webapp.repositories.PublisherRepository;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {

        Author eric = new Author("Eric", "Evans");
        Publisher harperCollins = new Publisher("Harper Collins");
        Book ddd = new Book("Domain Driven Design", "1234", harperCollins);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        publisherRepository.save(harperCollins);
        bookRepository.save(ddd);


        Author rod = new Author("Rod", "Johnson");
        Publisher wrox = new Publisher("Wrox");
        Book noEJB = new Book("J2EE Development without EJB", "4321", wrox);
        rod.getBooks().add(noEJB);

        authorRepository.save(rod);
        publisherRepository.save(wrox);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
