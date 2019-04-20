package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.afcepf.al33.projet1.IBusiness.FournisseurIBusiness;
import fr.afcepf.al33.projet1.entity.Fournisseur;


@ManagedBean(name="mbFindFournisseur")
@SessionScoped
public class RechercherFournisseurManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@EJB
	private FournisseurIBusiness proxyFournisseur;
	
	private Fournisseur foundFournisseur;
	private List<Fournisseur> fournisseurs;

	

	
	public void onSelect(Fournisseur fourn) {
	
		foundFournisseur=fourn;
	  
		System.out.println(fourn.getNom());
		System.out.println(fourn.getId());
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		 session.setAttribute("foundFournisseur", foundFournisseur);
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/ficheFournisseur.xhtml?faces-redirect=true");
	}
	
	
	@PostConstruct
	public void init() {
		fournisseurs = proxyFournisseur.getAll();
		
	}


	public FournisseurIBusiness getProxyFournisseur() {
		return proxyFournisseur;
	}


	public void setProxyFournisseur(FournisseurIBusiness proxyFournisseur) {
		this.proxyFournisseur = proxyFournisseur;
	}


	public Fournisseur getFoundFournisseur() {
		return foundFournisseur;
	}


	public void setFoundFournisseur(Fournisseur foundFournisseur) {
		this.foundFournisseur = foundFournisseur;
	}


	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}


	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}


	
}
