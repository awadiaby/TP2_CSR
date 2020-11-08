/**
 * Les objets instances de la classe Cliente representent des clients.
 * Le fonctionnement est le suivant :
 */

public class Client extends Thread {
	private Site site_départ, site_arriver;

	public Client(Site site_départ, Site site_arriver) {
		
		this.site_départ = site_départ;
		this.site_arriver = site_arriver;
	}
	
	 @Override
	  public void run() {
		
		 this.site_arriver.use();
		 this.site_départ.send();
		//  this.start();
	   // System.out.println("Mon traitement");
	  }
	 
	
	

}
