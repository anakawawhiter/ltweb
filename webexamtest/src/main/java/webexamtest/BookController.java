package webexamtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import webexamtest.data.BookRepository;
import webexamtest.model.Book;

@RestController
@RequestMapping(path="/books", produces="application/json")
@CrossOrigin(origins="*")
public class BookController {
	private BookRepository bookRepo;
	@Autowired
	public BookController (BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	@GetMapping("/recent")
	public List<Book> getAllAprroved(){
		List<Book> books = (List<Book>) bookRepo.findAll();
		List<Book> books1 = new ArrayList<>();
		for( Book x : books) {
			if(x.getApproved() == 0) {
				books1.add(x);
			}
		}
		return books1;
	}
	@GetMapping("/recentall")
	public List<Book> getAll(){
		List<Book> bk = (List<Book>) bookRepo.findAll();
		return bk;
	}
	@PostMapping("/approved/{id}")
	public Book upDateAproved(@PathVariable("id") String bookCode) {
		Optional<Book> opt = bookRepo.findById(bookCode);
		Book b1 = new Book();
		if(opt.isPresent()) {
			b1=opt.get();
			b1.setApproved(1);
		}
		return bookRepo.save(b1);
	}
	@GetMapping("{id}")
	public Book getBook(@PathVariable("id") String bookCode) {
		Optional<Book> opt = bookRepo.findById(bookCode);
		if(opt.isPresent()) {
			return opt.get();
		}
		return null;
	}
	@DeleteMapping("/delete/{id}")
	public void delBook(@PathVariable("id") String bookCode) {
		bookRepo.deleteById(bookCode);
	}
//	@PostMapping( path="/add", consumes="applicaton/json")
//	@ResponseStatus(HttpStatus.CREATED)
//	public Book addBook(@RequestBody Book book) {
//		return bookRepo.save(book);
//	}
}
