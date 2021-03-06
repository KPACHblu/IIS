package iis.service.impl;


import iis.dao.ArticleDAO;
import iis.domain.Article;
import iis.service.ArticleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("articleServiceImpl")
public class ArticleServiceImpl implements ArticleService{
	
	private static final long serialVersionUID = -5475715507919667488L;

	@Autowired
	private ArticleDAO articleDAO;

	@Override
	public Article create(Article entity) {
		return articleDAO.create(entity);
	}

	@Override
	public Article update(Article entity) {
		return articleDAO.update(entity);
	}

	@Override
	public void deleteById(Long id) {
		articleDAO.deleteById(id);
	}

	@Override
	public Article findById(Long id) {
		return articleDAO.findById(id);
	}

	@Override
	public List<Article> findAll() {
		return articleDAO.findAll();
	}

	@Override
	public List<Article> findByType(String type) {
		return articleDAO.findByType(type);
	}
	
	


}
