package fr.afcepf.al33.projet1.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="articleCommande")
public class ArticleCommande implements Serializable{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

//		@Id
//		@GeneratedValue(strategy=GenerationType.IDENTITY)
//		@Column(name="id", nullable=false)
//		private Integer id;
		
		@Column(name="prixTotal")
		private double prixTotal;
		
		@Column(name="quantite")
		private int quantite;
		
		@Id
		@ManyToOne
		@JoinColumn(referencedColumnName="id")
		private Article article;
		
		@Id
		@ManyToOne
		@JoinColumn(referencedColumnName="id")
		private Commande commande;

		
		//Constructeur vide
		
		public ArticleCommande() {
			super();
			
		}
		
		//Constructeur chargé

		public ArticleCommande(/*Integer id,*/ double prixTotal, int quantite, Article article, Commande commande) {
			super();
//			this.id = id;
			this.prixTotal = prixTotal;
			this.quantite = quantite;
			this.article = article;
			this.commande = commande;
		}


		
		//GETTERS ET SETTERS

//		public Integer getId() {
//			return id;
//		}
//
//		public void setId(Integer id) {
//			this.id = id;
//		}

		public double getPrixTotal() {
			return prixTotal;
		}

		public void setPrixTotal(double prixTotal) {
			this.prixTotal = prixTotal;
		}

		public int getQuantite() {
			return quantite;
		}

		public void setQuantite(int quantite) {
			this.quantite = quantite;
		}

		public Article getArticle() {
			return article;
		}

		public void setArticle(Article article) {
			this.article = article;
		}

		public Commande getCommande() {
			return commande;
		}

		public void setCommande(Commande commande) {
			this.commande = commande;
		}
	

}
