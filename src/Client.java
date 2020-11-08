/**
 * Les objets instances de la classe Cliente representent des clients.
 * Le fonctionnement est le suivant :
 */

public class Client extends Thread {
	private Site site_d�part, site_arriver;

	public Client(Site site_d�part, Site site_arriver) {
		
		this.site_d�part = site_d�part;
		this.site_arriver = site_arriver;
	}
	
	 @Override
	  public void run() {
		
		 this.site_arriver.use();
		 this.site_d�part.send();
		//  this.start();
	   // System.out.println("Mon traitement");
	  }
	 
	
	

}
