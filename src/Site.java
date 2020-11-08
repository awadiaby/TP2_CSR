class Site {
	

	/* Constantes associees au site */

	static final int STOCK_INIT = 5;
	static final int STOCK_MAX = 10;
	static final int BORNE_SUP = 8;
	static final int BORNE_INF = 2;
	private int availableBike = 0;
		
private int i;
private int compterVelo = STOCK_INIT;
	
	public Site(int i) {
		this.i = i ;
	}
	 // synch rendre la méthode atomique ( un verrou)
	
	// emprunter un vélo
	synchronized void use() {
		if(compterVelo == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo--;
		notify();
	}
	// retourner un velo 
	synchronized void send () {
		if(compterVelo >= STOCK_INIT ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo++;
		notify();
	}
	// equilibrer camion
	synchronized void equilibrate(Camion camion) {
		if(compterVelo > BORNE_SUP) {
			int veloExedentaire = compterVelo - BORNE_SUP;
			camion.chargerVelo(veloExedentaire);
			compterVelo = STOCK_INIT;
		} else if(compterVelo < BORNE_INF) {
			int veloDecharges = BORNE_INF - compterVelo;
			// getVeloTransporters : le nombre de vélo contenu dans le canion
			if(camion.getVeloTransportes() >= veloDecharges ) {
				camion.dechargerVelo(veloDecharges);
				compterVelo = STOCK_INIT;
			}
		}
	}

}
