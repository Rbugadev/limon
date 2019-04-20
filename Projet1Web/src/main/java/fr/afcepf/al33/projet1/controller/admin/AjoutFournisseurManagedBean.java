package fr.afcepf.al33.projet1.controller.admin;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.al33.projet1.IBusiness.FournisseurIBusiness;
import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Fournisseur;
import fr.afcepf.al33.projet1.entity.Ville;




@ManagedBean(name="mbAjoutFournisseur")
@SessionScoped
public class AjoutFournisseurManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private FournisseurIBusiness proxyFournisseur;
	
	@EJB
	private VilleIBusiness proxyVille;
	

	private Fournisseur fournisseur = new Fournisseur();
	
	private List<Ville> villes;

	
	
	@PostConstruct
	public void init() {
		
		villes = proxyVille.getAll();
	}
	
	public void ajout() {
	
		proxyFournisseur.add(fournisseur);
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


	public VilleIBusiness getProxyVille() {
		return proxyVille;
	}


	public void setProxyVille(VilleIBusiness proxyVille) {
		this.proxyVille = proxyVille;
	}


	public List<Ville> getVilles() {
		return villes;
	}


	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}
	
}
