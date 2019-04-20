package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import fr.afcepf.al33.projet1.IBusiness.ApprovisionnementIBusiness;
import fr.afcepf.al33.projet1.IBusiness.ArticleCommandeIBusiness;
import fr.afcepf.al33.projet1.IBusiness.CommandeIBusiness;
import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.entity.Stock;


@ManagedBean(name="mbAfficherCommande")
@SessionScoped
public class AfficherCommandeManagedBean implements Serializable{
	
	final Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="#{mbFicheClient.selectedCommande}")
	private Commande commande;
	
	private List<ArticleCommande> articlesCommandes = new ArrayList<ArticleCommande>();
	
	private Date dateTraitement = new Date();
	
	@EJB
	private CommandeIBusiness proxyCommande;
	
	@EJB
	private ArticleCommandeIBusiness proxyArticleCommande;
	
	@EJB
	private ApprovisionnementIBusiness proxyApprovisionnement;

	@EJB
	private StockIBusiness proxyStock;

	@PostConstruct
	public void init() {
			
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		commande = (Commande) session.getAttribute("selectedCommande");
		
		articlesCommandes=proxyArticleCommande.getAllByCommande(commande);
	}
	
	public void traiterCommande() {
		if(commande.getDateExpedition()==null) {
			articlesCommandes= proxyArticleCommande.getAllByCommande(commande);
			
			for (ArticleCommande ac : articlesCommandes) {
				// pour chaque article 
				int quantitePreparee = 0;
				Stock stock = proxyStock.searchById(ac.getArticle().getId());
				if(logger.isDebugEnabled()) {
					logger.debug("stock avant pour " + ac.getArticle().getNom() 
							+ " physique = " + stock.getQuantiteDispoPhysique()
							+ " internet = " + stock.getQuantiteDispoSiteInternet());
				}
				List<Approvisionnement> approvisionnements = stock.getApprovisionnements();
				// tri par date de péremption de la plus ancienne à la plus récente
				Collections.sort(approvisionnements, new Comparator<Approvisionnement>() {
					public int compare(Approvisionnement a1, Approvisionnement a2) {
						// a1 et a2 dans l'ordre pour un tri ascendant (option choisie ici)
						// a2 et a1 en ordre inverse pour tri descendant
						return a1.getDatePeremption().compareTo(a2.getDatePeremption());
					}
				});
				if(logger.isDebugEnabled()) {
					logger.debug("après tri des approvisionnements sur date péremption : "); 
					for (Approvisionnement approvisionnement : approvisionnements) {
						logger.debug("approvisionnement date péremption : " + approvisionnement.getDateApprovisionnement()); 
					}
				}
				for (Approvisionnement approvisionnement : approvisionnements) {
					// dans les approvisionnements classés par ordre de date de péremption croissante
					// prendre la quantité nécessaire ou disponible 
					// si la date de péremption n'est pas dépassée
					if (approvisionnement.getDatePeremption().after(new Date())) {
						int quantiteAPrendre = Integer.min(ac.getQuantite() - quantitePreparee, approvisionnement.getQuantiteRestante());
						if(logger.isDebugEnabled()) {
							logger.debug("quantité approvisionnement avant pour " 
									+ " lot " + approvisionnement.getLot()
									+ " date péremption " + approvisionnement.getDatePeremption()
									+ " = " + approvisionnement.getQuantiteRestante());
						}
						// décrémenter le stock dans l'approvisionnement
						approvisionnement.setQuantiteRestante(approvisionnement.getQuantiteRestante() - quantiteAPrendre);
						// modifier le stock
						proxyApprovisionnement.update(approvisionnement);
						if(logger.isDebugEnabled()) {
							logger.debug("quantité approvisionnement après pour " 
									+ " lot " + approvisionnement.getLot()
									+ " date péremption " + approvisionnement.getDatePeremption()
									+ " = " + approvisionnement.getQuantiteRestante());
						}
						quantitePreparee += quantiteAPrendre;
						// jusqu'à satisfaire la quantité commandée
						if(logger.isDebugEnabled()) {
							logger.debug("quantité préparée pour " + ac.getArticle().getNom() 
									+ " = " + quantitePreparee + " / " + ac.getQuantite());
						}
						if (quantitePreparee == ac.getQuantite()) {
							if(logger.isDebugEnabled()) {
								logger.debug("quantité intégralement préparée pour " + ac.getArticle().getNom());
							}
							break;					
						}
					}
				}
				// si quantité insuffisante emettre un message d'alerte pour l'administrateur
				// création d'un message
				if (quantitePreparee < ac.getQuantite()) {
					// modification de la quantité commandée à la quantité disponible
					ac.setQuantite(quantitePreparee);
					// update fait par l'update de proxyCommande
					if(logger.isDebugEnabled()) {
						logger.debug("quantité commande ramenée à " + ac.getQuantite() 
									+ " pour " + ac.getArticle().getNom());
					}
					FacesMessage message = new FacesMessage("Pour la commande " + commande.getId() 
														+ " seulement " + quantitePreparee
														+ " sur " + ac.getQuantite()
														+ " " + ac.getArticle().getNom());
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					// ajout à la liste des messages à afficher
					FacesContext.getCurrentInstance().addMessage(null, message);
				}			
				// maj quantite physique dans Stock
				stock.setQuantiteDispoPhysique(stock.getQuantiteDispoPhysique() - quantitePreparee);
				proxyStock.update(stock);
				if(logger.isDebugEnabled()) {
					logger.debug("stock après pour " + ac.getArticle().getNom() 
							+ " physique = " + stock.getQuantiteDispoPhysique());
				}
			}
			// mise à jour date expédition commande
			commande.setDateExpedition(dateTraitement);
			proxyCommande.update(commande);
		}
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/ficheClient.xhtml?faces-redirect=true");
	}
	


	public Commande getCommande() {
		return commande;
	}



	public void setCommande(Commande commande) {
		this.commande = commande;
	}



	public List<ArticleCommande> getArticlesCommandes() {
		return articlesCommandes;
	}



	public void setArticlesCommandes(List<ArticleCommande> articlesCommandes) {
		this.articlesCommandes = articlesCommandes;
	}

	public Date getDateTraitement() {
		return dateTraitement;
	}

	public void setDateTraitement(Date dateTraitement) {
		this.dateTraitement = dateTraitement;
	}

	public CommandeIBusiness getProxyCommande() {
		return proxyCommande;
	}

	public void setProxyCommande(CommandeIBusiness proxyCommande) {
		this.proxyCommande = proxyCommande;
	}

	

}
