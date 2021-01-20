package webexamtestcaller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import webexamtestcaller.model.Book;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {
	private RestTemplate rest = new RestTemplate();
	
	@GetMapping("/viewAllApprove")
	public String showAllAprove(Model model) {
		List<Book> books = Arrays.asList(rest.getForObject("http://localhost:8080/books/recent", Book[].class));
		model.addAttribute("dsbook", books);
		return "viewall";
	}
	@GetMapping("/viewAll")
	public String showAllbook(Model model) {
		List<Book> books = Arrays.asList(rest.getForObject("http://localhost:8080/books/recentall", Book[].class));
		model.addAttribute("dsbook", books);
		System.out.println(books);
		return "viewallbook";
	}
	@GetMapping("/approve/{bookcode}")
	public String capnhat(@PathVariable("bookcode") String bookcode) {
		Book xBook = rest.getForObject("http://localhost:8080/books/" + bookcode, Book.class);
		xBook.setApproved(1);
		rest.postForObject("http://localhost:8080/books/approved/" + bookcode, xBook, Book.class);
		return "redirect:/";
	}
	@GetMapping("/viewToDel/{bookcode}")
	public String viewToDel(@PathVariable("bookcode") String bookcode, Model model) {
		Book book = rest.getForObject("http://localhost:8080/books/"+ bookcode, Book.class);
		model.addAttribute("bk", book);
		return "viewtodel";
	}
	@GetMapping("/del/{bookcode}")
	public String del(@PathVariable("bookcode") String bookcode) {
		rest.delete("http://localhost:8080/books/delete/"+ bookcode);
		return "redirect:/";
	}
}