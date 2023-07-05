package com.example.textTrivia.repo;

import com.example.textTrivia.model.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question, Long> {

    public Iterable<Question> findQuestionsByTextId(long id);


}
