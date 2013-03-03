package iis.dao.impl;

import iis.dao.ArticleDAO;
import iis.domain.Article;
import iis.domain.Enum.ArticleTypeEnum;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class ArticleDAOImpl extends GenericDAOImpl<Article> implements
		ArticleDAO {

	private static final long serialVersionUID = 962656663635886301L;

	@Override
	public List<Article> findByType(ArticleTypeEnum type) {
		return executeNamedQueryList(Article.FIND_BY_TYPE,
				new Object[] { type });
	}

}
