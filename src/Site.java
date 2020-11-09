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
	private int compterVelo = STOCK_INIT; //Quantité de Velos
	private int num, stock_Max;


	public Site(int i) {
		this.compterVelo = STOCK_INIT;
		this.stock_Max = STOCK_MAX;
		this.num = i;
		System.out.println("Site " + Thread.currentThread().getName()+ " N° "+Site.this.num +" Velos: "+ Site.this.compterVelo+"/" +Site.this.stock_Max);
	}


//	public Site(int i) {
//		this.i = i ;
//	}
	 // synch rendre la méthode atomique ( un verrou)

	/**
	 * Emprunter un vélo
	 */
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
		System.out.println("Site " + Thread.currentThread().getName()+ " N°"+Site.this.num +"Velos:"+ Site.this.compterVelo+"/" +Site.this.stock_Max );
	}

	/**
	 * Retourner un velo
	 */
	synchronized void send () {
		if(compterVelo == STOCK_MAX ) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo++;
		notify();
		System.out.println("Site   Numéro   "  +Site.this.num +    "Velos:" + Site.this.compterVelo+  "/"  +Site.this.stock_Max );
	}

	/**
	 * Equilibrage  DOUTE ******
	 */
	synchronized void equilibrate(Camion camion) {
		System.out.println("Compter Velo"+ compterVelo);
		if(compterVelo > BORNE_SUP) {

			int veloExedentaire = compterVelo - STOCK_INIT ;
			camion.chargerVelo(veloExedentaire);
			compterVelo = STOCK_INIT;

		} else if(compterVelo < BORNE_INF) {

			int veloDecharges = STOCK_INIT - compterVelo;
			if(camion.getVeloTransportes()<veloDecharges){
				compterVelo = camion.getVeloTransportes();
				camion.dechargerVelo(camion.getVeloTransportes());

			}
			//if(camion.getVeloTransportes() >= veloDecharges ) {
			//	camion.dechargerVelo(veloDecharges);
			//	compterVelo = STOCK_INIT;
			//}
		}
		System.out.println("Equilibrate Site " + Thread.currentThread().getName()+ " Site Numéro " +Site.this.num +  " Velos: "+ Site.this.compterVelo+"/" +Site.this.stock_Max );
	}


	static public void main(String[] args) {

		Site Site1 = new Site(1);
		Site Site2 = new Site(2);
		Site1.use();
		Site2.send();

	}
}
