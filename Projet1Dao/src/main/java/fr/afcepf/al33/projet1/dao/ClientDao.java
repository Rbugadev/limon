package fr.afcepf.al33.projet1.dao;


import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.idao.ClientIdao;



@Remote(ClientIdao.class)
@Stateless
public class ClientDao extends GenericDao<Client> implements ClientIdao {
	
	
	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;

	@Override
	public Client getByLogin(String login) {
		String REQ_AUTH = "SELECT client from Client client WHERE client.login = :login ";
		Client client = null;
		
		try {
		Query queryJPQL = em.createQuery(REQ_AUTH);
		queryJPQL.setParameter("login", login);
		client = (Client) queryJPQL.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		return client;
	}

	@Override
	public Client findByLoginAndPassword(String l, String p) {
		
		String REQ_AUTH = "SELECT client from Client client WHERE client.login = :l AND client.password = :p";
		Client client = null;
		
		try {
		Query queryJPQL = em.createQuery(REQ_AUTH);
		queryJPQL.setParameter("l", l);
		queryJPQL.setParameter("p", p);
		client = (Client) queryJPQL.getSingleResult();
		}catch (NoResultException e) {
			return null;
		}
		return client;
		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getAll() {
		List<Client> clients = null;
		String REQ_GETALL = "SELECT client from Client client ORDER BY client.nom";
		Query queryJPQL = em.createQuery(REQ_GETALL);
		clients = queryJPQL.getResultList();
		return clients;
	}



	@Override
	public Client findById(int id) {
		String REQ_AUTH = "SELECT client from Client client WHERE client.id = :i";
		Client client= null;
	
		Query queryJPQL=em.createQuery(REQ_AUTH);
		queryJPQL.setParameter("i", id);
		client=(Client) queryJPQL.getSingleResult();
		
		
		return client;
	}

}
