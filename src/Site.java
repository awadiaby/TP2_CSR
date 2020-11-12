/**
 * Les objets instances de la classe Site representent un ensemble des velos,
 * Chaque site a une numero en Stock, un Stock maximo et un nom
 *
 */
class Site {

	/**
	 * Constantes associées au site
	 */

	static final int STOCK_INIT = 5;
	static final int STOCK_MAX = 10;
	static final int BORNE_SUP = 8;
	static final int BORNE_INF = 2;
	private int availableBike = 0;
	private int compterVelo = STOCK_INIT; //Quantité de Velos dans site
	private int num,num2; //Numéroté du SITE
	private int	stock_Max;


	/**
	 * Creer objet type Site(nom)
	 */
	public Site(int i) {
		this.compterVelo = STOCK_INIT;
		this.stock_Max = STOCK_MAX;
		this.num = i;
		this.num2 = i+1;
		System.out.println("Site N° "+Site.this.num2 +" Velos: "+ Site.this.compterVelo+"/" +Site.this.stock_Max);
	}



	/**
	 * Emprunter un vélo
	 */
	synchronized void use() {
		while(compterVelo == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo--;
		notifyAll();
		System.out.println("Cliente: "+Thread.currentThread().getName() +" Emprunte - Site N°"+Site.this.num2 +" Velos:"+ Site.this.compterVelo+"/" +Site.this.stock_Max );
	}

	/**
	 * Retourner un velo
	 */
	synchronized void send () {
		while(compterVelo == STOCK_MAX ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo++;
		notifyAll();
		System.out.println("Cliente:"+Thread.currentThread().getName() +" returne une Vélo Site N°: "  +Site.this.num2 +" Vélos: " + Site.this.compterVelo+  "/"  +Site.this.stock_Max );
	}

	/**
	 * Equilibrage
	 */
	synchronized void equilibrate(Camion camion) {

		//Si la quantité de vélos dans le site > au borne sup
		if(compterVelo > BORNE_SUP) {

			int veloExedentaire = compterVelo - STOCK_INIT ;
			camion.chargerVelo(veloExedentaire);
			compterVelo = STOCK_INIT;

		//Si la quantité de vélos dans le site < au borne inf
		} else if(compterVelo < BORNE_INF) {

			int veloDecharges = STOCK_INIT - compterVelo;

			//Si la quantité de vélos dans le camion < pour equilibrer les vélos dans le site
			if(camion.getVeloTransportes()<veloDecharges){
				compterVelo =compterVelo+camion.getVeloTransportes();
				camion.dechargerVelo(camion.getVeloTransportes());

			}else{
				camion.dechargerVelo(veloDecharges);
				compterVelo = STOCK_INIT;
			}
		}
		System.out.println("Equilibrate Site " + Thread.currentThread().getName()+" Le camion a: "+camion.getVeloTransportes()+" vélos "+"--> Site Numéro: " +Site.this.num2 +  " Velos: "+ Site.this.compterVelo+"/" +Site.this.stock_Max );
	}

	/**
	 * Get NOM du site
	 */
	public int getNom (){
		return num ;
	}

}
