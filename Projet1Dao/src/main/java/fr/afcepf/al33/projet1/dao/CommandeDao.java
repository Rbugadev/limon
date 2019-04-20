package fr.afcepf.al33.projet1.dao;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.afcepf.al33.projet1.entity.Client;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.idao.CommandeIdao;


@Remote(CommandeIdao.class)
@Stateless
public class CommandeDao extends GenericDao<Commande> implements CommandeIdao {

	//private final Logger slf4jLogger = LoggerFactory.getLogger(this.getClass());
	
	@PersistenceContext(unitName="Projet1DS")
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Commande> getAll() {
		List<Commande> commandes=null;
		String REQ = "SELECT commande from Commande commande ORDER BY commande.id";
		Query queryJPQL = em.createQuery(REQ);
		commandes = queryJPQL.getResultList();
		return commandes;
	}

	@SuppressWarnings("unchecked")
	public List<Commande> getAllToProcess() {
		//slf4jLogger.debug("getAllToProcess");
		List<Commande> commandes = null;
		// Commande à traiter si dateExpedition = null
		String REQ = "SELECT commande from Commande commande WHERE commande.dateExpedition is null ORDER BY commande.dateCreation desc";
		Query queryJPQL = em.createQuery(REQ);
		commandes = queryJPQL.getResultList();
		return commandes;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Commande> getAllByClient(Client client) {
		List<Commande> commandes=null;
		String REQ = "SELECT commande from Commande commande WHERE commande.client = :client ORDER BY commande.dateCreation desc";
		Query queryJPQL = em.createQuery(REQ);
		queryJPQL.setParameter("client", client);
		commandes = queryJPQL.getResultList();
		return commandes;
	}
}
