/**
 * Les objets instances de la classe Camion represente
 */

public class Camion extends Thread{

	private Site[] sites;
	private int veloTransports = 0;


	/**
	 * CAMION
	 */
	public Camion(Site[] sites) {
		this.sites = sites;
		this.veloTransports = veloTransports;


	}

	/**
	 * Quantité des vélos au camion
	 */
	int getVeloTransportes() {
		return veloTransports;
	}

	/**
	 * Monter des vélos au camion
	 */
	void chargerVelo (int t) {
		veloTransports += t;
	}

	/**
	 * Descendre des vélos du camion
	 */
	void dechargerVelo(int t) {
		veloTransports -= t;
	}


	@Override
	public void run() {

	//Le camion est toujours en train d'equilibrer les sites
		while(true) {
			for (int i = 0; i <= this.sites.length - 1; i++) {
				this.sites[i].equilibrate(this);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}




}