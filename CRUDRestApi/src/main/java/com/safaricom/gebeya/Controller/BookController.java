package com.safaricom.gebeya.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safaricom.gebeya.Exception.ResourceNotFoundException;
import com.safaricom.gebeya.Model.Book;
import com.safaricom.gebeya.Repository.BookRepository;

@RestController
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@GetMapping("/show-books")
	public List<Book> getAllBooks(){
		return bookRepository.findAll();}
	
    @PostMapping("/add-books")
	public Book addBook(@RequestBody Book book){
		return bookRepository.save(book);
		
	
	}
    //by id
    @GetMapping("/show-books{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id){
    	Book book=bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("book not found with id "+id));
    	return ResponseEntity.ok(book);
    }
     
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id,@RequestBody Book updateBookDetail){
    	Book updateBook =bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("book not found with id "+id));
    	updateBook.setBookName(updateBookDetail.getBookName());
    	updateBook.setAuthorName(updateBookDetail.getAuthorName());
    	
    	bookRepository.save(updateBook);
    	return ResponseEntity.ok(updateBook);
    }
    @DeleteMapping("/{id}")
   public ResponseEntity<Book> deleteBook(@PathVariable int id){
	    Book book=bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("book not found with id "+id));
	    bookRepository.delete(book);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    
   }
}
