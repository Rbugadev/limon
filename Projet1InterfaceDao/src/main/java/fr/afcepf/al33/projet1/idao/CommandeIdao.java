package fr.afcepf.al33.projet1.idao;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;

public interface CommandeIdao extends GenericIdao<Commande> {
	
	List<Commande> getAll ();
	List<Commande> getAllToProcess ();
	List<Commande> getAllByClient (Client client);

}
