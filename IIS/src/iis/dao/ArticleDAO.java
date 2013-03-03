package iis.dao;

import iis.domain.Article;
import iis.domain.Enum.ArticleTypeEnum;

import java.io.Serializable;
import java.util.List;

public interface ArticleDAO extends Serializable {

	Article create(Article entity);

	Article update(Article entity);

	void deleteById(Long id);

	Article findById(Long id);
	
	List<Article> findAll();

	List<Article> findByType(ArticleTypeEnum type);

}
