package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


import fr.afcepf.al33.projet1.IBusiness.FournisseurIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.Fournisseur;




@ManagedBean(name="mbFicheFournisseur")
@SessionScoped
public class FicheFournisseurManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private FournisseurIBusiness proxyFournisseur;
	



	
	@ManagedProperty(value="#{mbFindFournisseur.foundFournisseur}")
	private Fournisseur fournisseur;
	
	List<Approvisionnement> approvisionnements = new ArrayList<Approvisionnement>();

	
	
	

	@PostConstruct
	public void init() {


		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		fournisseur = (Fournisseur) session.getAttribute("foundFournisseur");
		approvisionnements= fournisseur.getApprovisionnements();
		
	}

	public void delete() {
		proxyFournisseur.delete(fournisseur);
		
		 FacesContext facesContext = FacesContext.getCurrentInstance();
		 facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext,null,"/interfaceAdmin/moteurRechercheFournisseur.xhtml?faces-redirect=true");
	}
	


	public FournisseurIBusiness getProxyFournisseur() {
		return proxyFournisseur;
	}


	public void setProxyFournisseur(FournisseurIBusiness proxyFournisseur) {
		this.proxyFournisseur = proxyFournisseur;
	}


	public Fournisseur getFournisseur() {
		return fournisseur;
	}


	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public List<Approvisionnement> getApprovisionnements() {
		return approvisionnements;
	}

	public void setApprovisionnements(List<Approvisionnement> approvisionnements) {
		this.approvisionnements = approvisionnements;
	}
	
	
	
}
