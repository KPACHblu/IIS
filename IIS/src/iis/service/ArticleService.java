package iis.service;

import iis.domain.Article;

import java.io.Serializable;
import java.util.List;

public interface ArticleService extends Serializable{

	Article create(Article entity);

	Article update(Article entity);

	void deleteById(Long id);

	Article findById(Long id);

	List<Article> findAll();
	
	List<Article> findByType(String type);

}
