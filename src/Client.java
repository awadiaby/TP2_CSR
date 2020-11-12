/**
 * Les objets instances de la classe Cliente representent des clients.
 * Le fonctionnement est le suivant :
 */

public class Client extends Thread {
	private Site site_départ, site_arriver;
	private int distance_entre_sites; //Canculate distance entre sites

	public Client(Site site_départ, Site site_arriver) {

		this.site_départ = site_départ;
		this.site_arriver = site_arriver;
		distance_entre_sites = Math.abs(this.site_arriver.getNom() - this.site_départ.getNom());

	}

	@Override
	public void run() {

		this.site_arriver.use();
		try { Thread.sleep(100*distance_entre_sites);   //Sleep proportionel au distance entre site de départ et site d'arrive du client
		} catch(InterruptedException e) {}
		this.site_départ.send();

	}




}
