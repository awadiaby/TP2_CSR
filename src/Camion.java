/**
 * Les objets instances de la classe Camion represente
 */

public class Camion extends Thread{
	
	private Site[] sites;
	private int taille;
	private int veloTransports = 0;

	public Camion(Site[] sites) {
		this.sites = sites;
		// TODO Auto-generated constructor stub
	}
	
	int getTaille() {
		return taille;
	}
	void setTaille(int t) {
		taille = t;
	}
	
	int getVeloTransportes() {
		return veloTransports;
	}
	
	void chargerVelo (int t) {
		veloTransports += t;
	}
	
	void dechargerVelo(int t) {
		veloTransports -= t;
	}

	 @Override
	  public void run() {
		 for(int i = 0; i<= this.sites.length; i++) {
			 this.sites[i].equilibrate(this);
		 }
	    this.start();
	  }
	 
	/* public int compteBike() {
		 // return this.
	 } */

}
