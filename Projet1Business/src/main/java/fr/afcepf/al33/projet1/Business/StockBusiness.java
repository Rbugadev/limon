package fr.afcepf.al33.projet1.Business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import fr.afcepf.al33.projet1.IBusiness.StockIBusiness;
import fr.afcepf.al33.projet1.entity.Approvisionnement;
import fr.afcepf.al33.projet1.entity.ArticleCommande;
import fr.afcepf.al33.projet1.entity.Categorie;
import fr.afcepf.al33.projet1.entity.Commande;
import fr.afcepf.al33.projet1.entity.Stock;
import fr.afcepf.al33.projet1.idao.ApprovisionnementIdao;
import fr.afcepf.al33.projet1.idao.ArticleCommandeIDao;
import fr.afcepf.al33.projet1.idao.CommandeIdao;
import fr.afcepf.al33.projet1.idao.StockIdao;

@Remote(StockIBusiness.class)
@Stateless
public class StockBusiness implements StockIBusiness{

	@EJB
	private StockIdao proxyStockIDao;
	
	@EJB 
	private ApprovisionnementIdao proxyApprovisionnementIdao;
	
	@EJB
	private CommandeIdao proxyCommandeIdao;
	
	@EJB
	private ArticleCommandeIDao proxyArticleComande;

	
	
	@Override
	public Stock add(Stock stock) {
		proxyStockIDao.ajouter(stock);
		return stock;
	}

	@Override
	public Boolean delete(Stock stock) {
		proxyStockIDao.supprimer(stock);
		return null;
	}

	@Override
	public Stock update(Stock stock) {
		proxyStockIDao.modifier(stock);
		return stock;
	}

	@Override
	public Stock searchById(Integer id) {
		Stock stock = proxyStockIDao.rechercherParId(id);
		return stock;
	}

	@Override
	public List<Stock> getAll() {
		List<Stock> stocks=new ArrayList<Stock>();
		stocks=proxyStockIDao.getAll();
		
		
//		List<Commande> commandesEnCours = proxyCommandeIdao.getAllToProcess();
//		Date aujourdhui = new Date();
//		int nbreStockPhysique=0;
//		int dispoWebARetirer=0;
//		for (Stock stock : stocks) {
//			
//			List<Approvisionnement> approvisionnements = proxyApprovisionnementIdao.getAllApproByStock(stock);
//			for (Approvisionnement approvisionnement : approvisionnements) {
//				if (approvisionnement.getDatePeremption().after(aujourdhui))
//					{
//						
//					nbreStockPhysique= nbreStockPhysique + approvisionnement.getQuantiteRestante();
//						
//					}
//			}
//			System.out.println(nbreStockPhysique);
//			stock.setQuantiteDispoPhysique(nbreStockPhysique);
//			stock.setQuantiteDispoSiteInternet(nbreStockPhysique);
//			nbreStockPhysique=0;
//			
//			
//			for (Commande commande : commandesEnCours) {
//				List<ArticleCommande> articlesCommandes = new ArrayList<ArticleCommande>();
//				articlesCommandes= proxyArticleComande.getAllByCommande(commande);
//				for (ArticleCommande ac : articlesCommandes) {
//					if(ac.getArticle().getStock().getId()==stock.getId()) {
//						dispoWebARetirer = dispoWebARetirer + ac.getQuantite();
//					}
//				}
//				
//			}
//			stock.setQuantiteDispoSiteInternet(stock.getQuantiteDispoSiteInternet()-dispoWebARetirer);
//			dispoWebARetirer=0;
//			proxyStockIDao.modifier(stock);
//		}
//		
//		
//		
		return stocks;
	}

	@Override
	public List<Stock> getByIdCategorie(Categorie c) {
		
		List<Stock> stocks=new ArrayList<Stock>();
		stocks=proxyStockIDao.getByIdCategorie(c);
		
		return stocks;
	}
	
	@Override
	public List<Stock> getByQuantity(){
		List<Stock> stockQuantite = new ArrayList<>();
		stockQuantite=proxyStockIDao.getByQuantity();
		return stockQuantite;
	}

	@Override
	public String getTotalQuantity() {
		return proxyStockIDao.getTotalQuantity();
	}
	
	@Override
	public List<Stock> getRupture(){
		return proxyStockIDao.getRupture();
	}

	@Override
	public String getVirtualQuantity() {
		return proxyStockIDao.getVirtualQuantity();
	}
}