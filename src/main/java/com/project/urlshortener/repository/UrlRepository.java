package com.project.urlshortener.repository;

import com.project.urlshortener.model.UrlModel;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UrlRepository extends MongoRepository<UrlModel, String> {

}
