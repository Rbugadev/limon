package fr.afcepf.al33.projet1.IBusiness;

import java.util.List;

import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;

public interface CommandeIBusiness {
		
	public Commande add(Commande commande);
	public Boolean delete(Commande commande);
	public Commande update(Commande commande);
	public Commande searchById(Integer id);
	public List<Commande> getAllToProcess();
	public List<Commande> getAllByClient (Client client);

}
