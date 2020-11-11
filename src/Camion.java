/**
 * Les objets instances de la classe Camion represente
 */

public class Camion extends Thread{

	private Site[] sites;

	private Site site_actuel;
	private int veloTransports = 0;


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

	/**
	 * Valider quand le camion a pas la quantité suffi pour le site
	 */




	@Override
	public void run() {


		while(true) {
			for (int i = 0; i <= this.sites.length - 1; i++) {
				this.sites[i].equilibrate(this);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
			//this.start();
		}
	}


	static public void main(String[] args) {
		Site Site1 = new Site(1);
		Site Site2 = new Site(2);
		Site1.use();
		Site1.use();
		Site1.use();
		Site1.use();

		Site2.send();
		Site2.send();
		Site2.send();
		Site2.send();
		Site2.send();

		Site[] sites1 ={Site1,Site2};
		Camion camion1 = new Camion(sites1);
		camion1.chargerVelo(0);
		System.out.println("Velos dans le camion: " + camion1.getVeloTransportes());

		sites1[0].equilibrate(camion1);
		System.out.println("Velos dans le camion:" + camion1.getVeloTransportes());

		sites1[1].equilibrate(camion1);
		System.out.println("Velos dans le camion:" + camion1.getVeloTransportes());


	}

}