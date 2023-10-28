package com.safaricom.gebeya.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.safaricom.gebeya.Model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
