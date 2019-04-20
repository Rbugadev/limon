package fr.afcepf.al33.projet1.controller.utilisateur;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;


@ManagedBean(name="mbValiderCommande")
@SessionScoped
public class ValiderCommandeManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// pour récupération objet commande depuis managed bean Panier
	@ManagedProperty(value="{mbPanier.commande}")
	private Commande commande;

	
	// pour récupération objet client depuis managed bean Connection Utilisiateur
	@ManagedProperty(value="#{mbConnectionUtilisateur.clientConnecte}")
	private Client client;
	
	
	
	
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}
	

	
	
}
