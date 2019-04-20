package fr.afcepf.al33.projet1.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categorie")
public class Categorie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	
	@Column(name="nomCategorie")
	private String nomCategorie;
	
	
	@OneToMany(mappedBy="categorie", cascade=CascadeType.ALL)
	private List<Article> articles;


	//Constructeur vide
	
	public Categorie() {
		super();
	}

	//Constructeur chargé

	public Categorie(Integer id, String nomCategorie, List<Article> articles) {
		super();
		this.id = id;
		this.nomCategorie = nomCategorie;
		this.articles = articles;
	}


	//GETTERS ET SETTERS
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNomCategorie() {
		return nomCategorie;
	}


	public void setNomCategorie(String nomCategorie) {
		this.nomCategorie = nomCategorie;
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}


	
}
