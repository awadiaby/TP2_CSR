/**
 * Auteurs Diaby Awa, KArla
 * Date : 12/11/2020
 */
/**
 * Les objets instances de la classe Cliente representent des clients.
 * Le fonctionnement est le suivant :
 */

public class Client extends Thread {
	private Site site_depart, site_arriver;

	public Client(Site site_depart, Site site_arriver) {
		
		this.site_depart = site_depart;
		this.site_arriver = site_arriver;
	}
	
	public void emprunter(Site site_depart) {
		site_depart.destocker();
	}
	
	public void restituer(Site site_arriver) {
		site_depart.stocker();
	}
	
	 @Override
	  public void run() {
		
		 emprunter(site_depart);
		 try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			System.out.println("Le client termine de roulé");
			
		}
		
		 restituer(site_arriver);
			
	  }
	 

}
