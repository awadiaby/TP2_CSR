/**
 * Les objets instances de la classe Camion represente
 */

public class Camion extends Thread{
	
	private Site[] sites;
	private int veloTransports = 0;

	public Camion(Site[] sites) {
		this.sites = sites;
		this.veloTransports = veloTransports;

		// TODO Auto-generated constructor stub
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
		 for(int i = 0; i<= this.sites.length; i++) {
			 this.sites[i].equilibrate(this);
		 }
	    this.start();
	  }
	 
	

	static public void main(String[] args) {
		Site Site1 = new Site(1);
		Site Site2 = new Site(2);
		Site2.use();
		Site2.use();
		Site2.use();
		Site2.use();
		Site2.use();
		Site[] sites1 ={Site1,Site2};
		Camion camion1 = new Camion(sites1);
		camion1.chargerVelo(3);
		System.out.println("Velos dans le camion: " + camion1.getVeloTransportes());


        sites1[1].equilibrate(camion1);
		System.out.println("Velos dans le camion:" + camion1.getVeloTransportes());




	}

}
