package webexamtestcaller.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Book {
	private String bookcode;
	private String title;
	private String author;
	private String category;
	private int approved;
}
