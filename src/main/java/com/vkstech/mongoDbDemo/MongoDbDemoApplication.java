package com.vkstech.mongoDbDemo;

import com.vkstech.mongoDbDemo.model.Book;
import com.vkstech.mongoDbDemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MongoDbDemoApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(MongoDbDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- findBookById() ---");
        Book myBook = bookRepository.findById(102L).orElse(null);
        System.out.println(myBook);
        newLine();

        System.out.println("--- findBooksGtThanNoOfPages() ---");
        int noOfPages = 200;
        List<Book> stream0= bookRepository.findBooksGtThanNoOfPages(noOfPages);
        stream0.forEach(System.out::println);
        newLine();

        System.out.println("--- findBooksByWriterAndLtThanNoOfPages() ---");
        List<Book> stream1 = bookRepository.findBooksByWriterAndLtThanNoOfPages("Mahesh", 250);
        stream1.forEach(System.out::println);
        newLine();

        System.out.println("--- findBooksByWriterOrCategory() : Ex-1---");
        List<Book> stream2 = bookRepository.findBooksByWriterOrCategory("Mahesh", "Graphics");
        stream2.forEach(System.out::println);
        newLine();

        System.out.println("--- findBooksByWriterOrCategory() : Ex-2---");
        List<Book> stream3 = bookRepository.findBooksByWriterOrCategory("Shiva", "Backend");
        stream3.forEach(System.out::println);
        newLine();

        System.out.println("--- findBestBooks() ---");
        List<Book> stream4 = bookRepository.findBestBooks();
        stream4.forEach(System.out::println);
        newLine();

        System.out.println("--- findBooksWithCertainFields() ---");
        List<Book> stream5 = bookRepository.findBooksWithCertainFields("Mahesh", "Backend");
        stream5.forEach(System.out::println);
        newLine();

        System.out.println("--- findBookCountByCategory() ---");
        Integer bookCount = bookRepository.findBookCountByCategory("Frontend");
        System.out.println("bookCount: " + bookCount);
        newLine();

        System.out.println("--- isBooksAvailableByWriter() ---");
        Boolean isBooksAvailable = bookRepository.isBooksAvailableByWriter("Krishna");
        System.out.println("isBooksAvailable: " + isBooksAvailable);
        newLine();

        System.out.println("--- findBooksByWriter() (ASC) ---");
        List<Book> stream6 = bookRepository.findBooksByWriter("Mahesh");
        stream6.forEach(System.out::println);
        newLine();

        System.out.println("--- findBooksByCategory() (DESC)---");
        List<Book> stream7 = bookRepository.findBooksByCategory("Backend");
        stream7.forEach(System.out::println);
        newLine();

        System.out.println("--- deleteBooksByCategory() ---");
        Long deletedCount = bookRepository.deleteBooksByCategory("Frontend");
        System.out.println("deletedCount: " + deletedCount);
        newLine();
    }

    private void newLine() {
        System.out.println();
    }

//	@Override
//	public void run(String... args) throws Exception {
//		Book b1 = new Book(101L, "Angular Tutorials", "Krishna", 200, "Frontend");
//		Book b2 = new Book(102L, "JavaScript Tutorials", "Krishna", 200, "Frontend");
//		Book b3 = new Book(103L, "Spring Tutorials", "Mahesh", 300, "Backend");
//		Book b4 = new Book(104L, "Java Tutorials", "Krishna", 250, "Backend");
//		Book b5 = new Book(105L, "Hibernate Tutorials", "Mahesh", 150, "Backend");
//		List<Book> list = new ArrayList<>();
//
//		list.add(b1);
//		list.add(b2);
//		list.add(b3);
//		list.add(b4);
//		list.add(b5);
//		bookRepository.saveAll(list);
//	}
}
