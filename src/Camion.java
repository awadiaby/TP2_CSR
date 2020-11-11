/**
 * Les objets instances de la classe Camion represente
 */

public class Camion extends Thread{
	
	private Site[] sites;
	private int veloTransports = 0;
	private int num_courant = 0;

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
public void se_deplacer() {
	try {
		Thread.sleep(40);
	} catch (InterruptedException e) {
		num_courant ++;
		num_courant = num_courant % SystemeEmprunt.NB_SITES;
		
	}
}



	 @Override
	  public void run() {
		 for(int i = 0; i<= this.sites.length; i++) {
			 se_deplacer();
			 this.sites[num_courant].equilibrate(this);
		 }
	  }
	 
	

	static public void main(String[] args) {
		
	}

}
