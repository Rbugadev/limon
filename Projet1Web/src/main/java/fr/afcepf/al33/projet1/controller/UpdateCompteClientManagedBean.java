package fr.afcepf.al33.projet1.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Ville;

@ManagedBean(name="mbUpdateCompteClient")
@SessionScoped
public class UpdateCompteClientManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ClientIBusiness proxyClient;
	
	@EJB
	private VilleIBusiness proxyVille;

	// pour récupération objet client depuis managed bean de l'écran précédent
	@ManagedProperty(value="#{mbConnectionUtilisateur.clientConnecte}")
	private Client client;
			
	private List<Ville> villes = new ArrayList<Ville>();

	
	@PostConstruct
	public void init() {
		// chargement de toutes les villes
		villes = proxyVille.getAll();
	}

	public ClientIBusiness getProxyClient() {
		return proxyClient;
	}

	public void setProxyClient(ClientIBusiness proxyClient) {
		this.proxyClient = proxyClient;
	}

	public VilleIBusiness getProxyVille() {
		return proxyVille;
	}

	public void setProxyVille(VilleIBusiness proxyVille) {
		this.proxyVille = proxyVille;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public void modifier() throws Exception {
				try {
					// enregistrement des modifications du client
					proxyClient.update(client);
					
					// création d'un message
					FacesMessage message = new FacesMessage("Modification de votre compte réussie");
					// ajout à la liste des messages à afficher
					FacesContext.getCurrentInstance().addMessage(null, message);
					
					// redirection depuis un managedBean
					FacesContext.getCurrentInstance().getExternalContext().redirect("../interfaceClient/accueilClient.xhtml");
				}
				catch (Exception e) {
					// création d'un message
					FacesMessage message = new FacesMessage("Erreur lors de la modification de votre compte : " + e.getMessage());
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					// ajout à la liste des messages à afficher
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
	}
	
}
