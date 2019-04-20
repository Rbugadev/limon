package fr.afcepf.al33.projet1.IBusiness;

import java.util.List;
import java.util.Map;

import fr.afcepf.al33.projet1.entity.Client;

public interface ClientIBusiness {
	
	public Client add(Client client);
	public Boolean delete(Client client);
	public Client update(Client client);
	public Client searchById(Integer id);
	public Client adherentByIdJPQL(int id);
	public Client seConnecter (String login, String password);
	public Client rechercheParLoginEtPassword (String l, String p);
	public Client rechercheParLogin (String l);
	public List<Client> getAllClients();
	public Map<String, String> genererHashedPassword(String passwordToHash) throws Exception;
}


