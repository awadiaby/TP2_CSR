/**
 * Les objets instances de la classe Cliente representent des clients.
 * Le fonctionnement est le suivant :
 */

public class Client extends Thread {
	private Site site_départ, site_arriver;
	private int distance_entre_sites;

	public Client(Site site_départ, Site site_arriver) {

		this.site_départ = site_départ;
		this.site_arriver = site_arriver;
		distance_entre_sites = Math.abs(this.site_arriver.getNom() - this.site_départ.getNom());
		System.out.println("----------------------- Arriver"+this.site_arriver.getNom()+"Depar"+this.site_départ.getNom()+"Diference"+distance_entre_sites );
	}

	@Override
	public void run() {

		this.site_arriver.use();
		try { Thread.sleep(100*distance_entre_sites); } catch(InterruptedException e) {}
		this.site_départ.send();
		//  this.start();
		// System.out.println("Mon traitement");
	}




}
