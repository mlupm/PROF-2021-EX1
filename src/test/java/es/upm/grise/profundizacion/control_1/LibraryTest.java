package es.upm.grise.profundizacion.control_1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {
	
	private Library library;
	
	@BeforeEach
	void setup() {
		library = new Library();
	}
	
	@Test
	public void addBook_oneBook() {
		Book book = new Book("Titulo");
		try {
			library.addBook(book);
			assertEquals(library.getBook("Titulo"), book);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void addBook_TwoBook() {
		Book book1 = new Book("Titulo 1");
		Book book2 = new Book("Titulo 2");
		try {
			library.addBook(book1);
			library.addBook(book2);
			assertEquals(library.getBook("Titulo 1"), book1);
			assertEquals(library.getBook("Titulo 2"), book2);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Test
	public void addBook_DuplicateBook() {
		assertThrows(DuplicatedBookException.class, () -> {
			Book book = new Book("Titulo");
			library.addBook(book);
			library.addBook(book);
		});
	}
	
	@Test
	public void removeBook_EmptyList() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			library.removeBook(new Book("no estaba antes"));
		});
	}
	
	@Test
	public void removeBook_NonExistingBook() {
		assertThrows(IndexOutOfBoundsException.class, () -> {
			library.addBook(new Book("Libro inicial"));
			library.removeBook(new Book("no estaba antes"));
		});
	}
	
	@Test
	public void removeBook_ExistingBook() {
		assertThrows(NonExistingBookException.class, () -> {
			Book book1 = new Book("Libro inicial");
			Book book2 = new Book("Libro que queda en la lista");
			library.addBook(book1);
			library.addBook(book2);
			library.removeBook(book1);
			library.getBook("Libro inicial");
		});
	}
	
	@Test
	public void getBook_EmptyLibrary() {
		assertThrows(EmptyLibraryException.class, () -> { 
			library.getBook("Libreria vacia");
		});
	}
	
	@Test
	public void getBook_NonExistingBook() {
		assertThrows(NonExistingBookException.class, () -> {
			library.addBook(new Book("Libro inicial"));
			library.getBook("Libro que no existe");
		});
	}

	@Test
	public void getBook_ExistingBook() {
		Book book = new Book("Libro que existe");
		try {
			library.addBook(book);
			assertEquals(library.getBook("Libro que existe"), book);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
