package com.example.textTrivia.repo;

import com.example.textTrivia.model.Text;
import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<Text, Long> {
}
