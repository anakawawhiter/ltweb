package webexamtest.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="book")
public class Book {
	@Id
	private String bookcode;
	private String title;
	private String author;
	private String category;
	private int approved;
}
