package iis.web;

import iis.domain.Article;
import iis.domain.User;
import iis.service.ArticleService;
import iis.service.UserService;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ComponentSystemEvent;

@ManagedBean(name = "articleBean")
@SessionScoped
public class ArticleBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = 867681382800730764L;

	@ManagedProperty(value = "#{articleServiceImpl}")
	private ArticleService articleService;
	
	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	private Article addArticle = new Article();
	private Article selectedArticle;
	private List<Article> allArticles;
	private List<Article> filteredArticles;
	private Map<String, String> allArticleType;
	private String selectedArticleType;
	
	@PostConstruct
	public void init() {
		allArticleType = new HashMap<String,String>();
		allArticleType.put("Новость", Article.TYPE_NEWS);
		allArticleType.put("Страница для абитуриентов", Article.TYPE_ENROLLEE_INFO);
		
		setAllArticles(articleService.findAll());
	}
	
	//Call from xhtml when it start to render
	public void onPageLoad(ComponentSystemEvent event) {
		String articleType = ((String) getViewParameter("ARTICLE_TYPE"));
		filteredArticles = articleService.findByType(articleType);
	}


	public void add() {
		try {
			User user = userService.findByLogin(getLogin());
			addArticle.setAuthor(user);
			addArticle.setType(selectedArticleType);
			articleService.create(addArticle);
			addMessage(FacesMessage.SEVERITY_INFO, "Статья успешно добавлена", "");
			allArticles.add(addArticle);
			addArticle = new Article();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Возникла ошибка при добавлении статьи","");
		}
	}

	public void update() {
		try {
			articleService.update(selectedArticle);
			addMessage(FacesMessage.SEVERITY_INFO, "Статья успешно обновлена","");
			selectedArticle = null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Возникла ошибка при обновлении данных статьи","");
		}
	}

	public String delete() {
		try {
			articleService.deleteById(selectedArticle.getId());
			addMessage(FacesMessage.SEVERITY_INFO, "Запись успешно удалена","");
			allArticles.remove(selectedArticle);
			selectedArticle = null;
			return "list?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Возникла ошибка при удалении записи","");
			return "";
		}
	}
	
	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public Article getAddArticle() {
		return addArticle;
	}

	public void setAddArticle(Article addArticle) {
		this.addArticle = addArticle;
	}

	public Article getSelectedArticle() {
		return selectedArticle;
	}

	public void setSelectedArticle(Article selectedArticle) {
		this.selectedArticle = selectedArticle;
	}

	public List<Article> getAllArticles() {
		return allArticles;
	}

	public void setAllArticles(List<Article> allArticles) {
		this.allArticles = allArticles;
	}

	public Map<String, String> getAllArticleType() {
		return allArticleType;
	}

	public void setAllArticleType(Map<String, String> allArticleType) {
		this.allArticleType = allArticleType;
	}


	public String getSelectedArticleType() {
		return selectedArticleType;
	}


	public void setSelectedArticleType(String selectedArticleType) {
		this.selectedArticleType = selectedArticleType;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<Article> getFilteredArticles() {
		return filteredArticles;
	}

	public void setFilteredArticles(List<Article> filteredArticles) {
		this.filteredArticles = filteredArticles;
	}
	
}
