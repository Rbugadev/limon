package fr.afcepf.al33.projet1.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fr.afcepf.al33.projet1.IBusiness.ClientIBusiness;
import fr.afcepf.al33.projet1.IBusiness.VilleIBusiness;
import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Ville;

@ManagedBean(name="mbGererCompteClient")
@SessionScoped
public class GererCompteClientManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ClientIBusiness proxyClient;
	
	@EJB
	private VilleIBusiness proxyVille;
		
	private Client client= new Client();
	
	private String passwordConfirmation;
	
	private List<Ville> villes = new ArrayList<Ville>();


	// pour récupération objet client depuis managed bean de l'écran précédent
//	@ManagedProperty(value="#{mbFindFournisseur.foundFournisseur}")
//	private Fournisseur fournisseur;

	
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
	
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
		
	public List<Ville> getVilles() {
		return villes;
	}

	public void setVilles(List<Ville> villes) {
		this.villes = villes;
	}

	public void ajouter() throws Exception {
		// controles pattern mot de passe saisi
		if (verifierMotDePasse()) {
			// génération du sel et du mot de passe haché
			 if (genererMotDePasseHache()) {				
				try {
					// enregistrement du client
					proxyClient.add(client);
					
					// création d'un message
					FacesMessage message = new FacesMessage("Création de votre compte réussie");
					// ajout à la liste des messages à afficher
					FacesContext.getCurrentInstance().addMessage(null, message);
					
					// redirection depuis un managedBean
					FacesContext.getCurrentInstance().getExternalContext().redirect("../interfaceClient/accueilClient.xhtml");
				}
				catch (Exception e) {
					// création d'un message
					FacesMessage message = new FacesMessage("Erreur lors de la création de votre compte : " + e.getMessage());
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					// ajout à la liste des messages à afficher
					FacesContext.getCurrentInstance().addMessage(null, message);
				}
			}
					
		 }
	}
	
	private boolean verifierMotDePasse() {
		boolean verification = true;
		
		// vérification de disponibilité de l'identifiant
		if (proxyClient.rechercheParLogin(client.getLogin()) != null) {
			// création d'un message
			FacesMessage message = new FacesMessage("L'identifiant choisi est déjà utilisé");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
			verification = false;
		}
		
		// mot de passe identique entre les 2 input
		if (!this.passwordConfirmation.equals(client.getPassword())) {
			// création d'un message
			FacesMessage message = new FacesMessage("Le mot de passe a été saisi différemment entre les deux champs");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
			verification = false;
		}
		
		// verification du pattern mot de passe
		// n caractère mini
		int n = 8;
		if (client.getPassword().length() < n) {
			// création d'un message
			FacesMessage message = new FacesMessage("Le mot de passe doit comporter au moins " + n + " caractères");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
			verification = false;
		}
		
		// 1 car minuscule au moins
		int nbCarMin = 1;
		if (nbMin(client.getPassword()) < nbCarMin) {
			// création d'un message
			FacesMessage message = new FacesMessage("Le mot de passe doit comporter au moins " + nbCarMin + " minuscule");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
			verification = false;
		}
				
		// 1 car majuscule au moins
		int nbCarMaj = 1;
		if (nbMaj(client.getPassword()) < nbCarMaj) {
			// création d'un message
			FacesMessage message = new FacesMessage("Le mot de passe doit comporter au moins " + nbCarMaj + " majuscule");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
			verification = false;
		}
		
		return verification;
	}
	
	// comptage du nb de minuscules dans une chaine
	private int nbMin(String chaine) {
		int compteur = 0;
		for (int i = 0; i < chaine.length(); i++) {
			char ch = chaine.charAt(i);
			if (Character.isLowerCase(ch))
				compteur++;
		}
		return compteur;
	}

	// comptage du nb de majuscules dans une chaine
	private int nbMaj(String chaine) {
		int compteur = 0;
		for (int i = 0; i < chaine.length(); i++) {
			char ch = chaine.charAt(i);
			if (Character.isUpperCase(ch))
				compteur++;
		}
		return compteur;
	}
	
	private boolean genererMotDePasseHache() {
		boolean result = false;
		try {
			Map<String, String> hm = proxyClient.genererHashedPassword(client.getPassword());
			client.setPassword(hm.get("hashedPassword"));
			client.setSalt(hm.get("salt"));
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			// création d'un message
			FacesMessage message = new FacesMessage("Erreur lors de la génération de votre mot de passe  : " + e.getMessage());
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			// ajout à la liste des messages à afficher
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return result;
	}
}
