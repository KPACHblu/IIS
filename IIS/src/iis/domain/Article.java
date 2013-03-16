package iis.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;

@Entity
@Table(name = Article.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name=Article.FIND_BY_AUTHOR_ID, query = "SELECT OBJECT(e) FROM Article AS e WHERE e.author.id = :p1"),
	@NamedQuery(name=Article.FIND_BY_DATE, query = "SELECT OBJECT(e) FROM Article AS e WHERE e.date = :p1"),
	@NamedQuery(name=Article.FIND_BY_TYPE, query = "SELECT OBJECT(e) FROM Article AS e WHERE e.type = :p1")
})                                                                                
public class Article {
	public final static String TABLE_NAME = "article";
	public final static String FIND_BY_AUTHOR_ID = "findArticleByAuthorID";
	public final static String FIND_BY_TYPE = "findArticleByType";
	public final static String FIND_BY_DATE = "findArticleByDate"; 
	
	public final static String TYPE_NEWS = "news";
	public final static String TYPE_ENROLLEE_INFO = "enrolleInfo";
	
	public Article() {}
	
	public Article(Long id, User author, String type, String title,
			String content, Date date) {
		super();
		this.id = id;
		this.author = author;
		this.type = type;
		this.title = title;
		this.content = content;
		this.date = date;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue 
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "author_id")
	private User author;

	@Column(name = "type") 
	private String type;
	
	@Column(name = "title") 
	private String title;
	
	@Column(name = "content") 
	private String content;
	
	@Column(name = "date") 
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", author=" + author + ", type=" + type
				+ ", title=" + title + ", content=" + content + ", date="
				+ date + "]";
	}
	
}
