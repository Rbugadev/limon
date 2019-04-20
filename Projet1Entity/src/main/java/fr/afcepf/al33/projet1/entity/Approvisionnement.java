package fr.afcepf.al33.projet1.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="approvisionnement")
public class Approvisionnement implements Serializable{
	
	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Integer id;
	

	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Article article;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Fournisseur fournisseur;
	
	@Column(name="quantiteCommandee")
	private int quantiteCommandee;
	
	@Column(name="quantiteRestante")
	private int quantiteRestante;
	
	@Column(name="prix")
	private double prix;
	
	@Column(name="dateApprovisionnement")
	private Date dateApprovisionnement;
	
	@Column(name="datePeremption")
	private Date datePeremption;
	
	@Column(name="dateMiseAuRebut")
	private Date dateMiseAuRebut;
	
	@Column(name="lot")
	private String lot;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="id")
	private Stock stock;
	

	//Constructeur vide
	
	public Approvisionnement() {
		super();
	}
	
	//Constructeur chargé

	public Approvisionnement(Integer id, Article article, Fournisseur fournisseur, int quantiteCommandee,
			int quantiteRestante, double prix, Date dateApprovisionnement, Date datePeremption, Date dateMiseAuRebut,
			String lot, Stock stock) {
		super();
		this.id = id;
		this.article = article;
		this.fournisseur = fournisseur;
		this.quantiteCommandee = quantiteCommandee;
		this.quantiteRestante = quantiteRestante;
		this.prix = prix;
		this.dateApprovisionnement = dateApprovisionnement;
		this.datePeremption = datePeremption;
		this.dateMiseAuRebut = dateMiseAuRebut;
		this.lot = lot;
		this.stock = stock;
	}


	//GETTERS ET SETTERS
	
	public Integer getId() {
		return id;
	}



	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public int getQuantiteCommandee() {
		return quantiteCommandee;
	}

	public void setQuantiteCommandee(int quantiteCommandee) {
		this.quantiteCommandee = quantiteCommandee;
	}

	public int getQuantiteRestante() {
		return quantiteRestante;
	}

	public void setQuantiteRestante(int quantiteRestante) {
		this.quantiteRestante = quantiteRestante;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Date getDateApprovisionnement() {
		return dateApprovisionnement;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDateApprovisionnement(Date dateApprovisionnement) {
		this.dateApprovisionnement = dateApprovisionnement;
	}


	public Date getDatePeremption() {
		return datePeremption;
	}


	public void setDatePeremption(Date datePeremption) {
		this.datePeremption = datePeremption;
	}


	public String getLot() {
		return lot;
	}


	public void setLot(String lot) {
		this.lot = lot;
	}


	public Stock getStock() {
		return stock;
	}


	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Date getDateMiseAuRebut() {
		return dateMiseAuRebut;
	}

	public void setDateMiseAuRebut(Date dateMiseAuRebut) {
		this.dateMiseAuRebut = dateMiseAuRebut;
	}


	
	
}
