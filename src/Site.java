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
	private int compterVelo = STOCK_INIT; // Quantité de Velos
	private int num;

	public Site(int i) {
		this.compterVelo = STOCK_INIT;
		this.num = i;
	}

	/**
	 * Emprunter un vélo
	 */
	synchronized void destocker() {
		System.out.print("avant destockage :");
		afficher();
		while (compterVelo == 0) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo--;
		notifyAll();
		// System.out.println(" Destockage Site " + Thread.currentThread().getName()+ "
		// N° " +Site.this.num +" Velos: " + Site.this.compterVelo );
		System.out.print("après destockage :");
		afficher();
	}

	/**
	 * Retourner un velo
	 */
	synchronized void stocker() {
		System.out.print("avant stockage :");
		afficher();
		while (compterVelo == STOCK_MAX) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		compterVelo++;
		notifyAll();
		// System.out.println(" Stockage Site " +this.num + " Velos:" +this.compterVelo
		// );
		System.out.print("après stockage :");
		afficher();
	}

	/**
	 * Equilibrage
	 */
	synchronized void equilibrate(Camion camion) {
		//System.out.println("Stock courant avant equilibrage: " + this.compterVelo + " du site numero " + Site.this.num);
		System.out.print("avant equilibrage :");
		afficher();
		if (compterVelo > BORNE_SUP) {
			int veloExedentaire = compterVelo - STOCK_INIT;
			camion.chargerVelo(veloExedentaire);
			compterVelo = STOCK_INIT;

		} else if (compterVelo < BORNE_INF) {

			int veloDecharges = STOCK_INIT - compterVelo;
			if (camion.getVeloTransportes() < veloDecharges) {
				compterVelo = camion.getVeloTransportes();
				camion.dechargerVelo(camion.getVeloTransportes());

			}

		}
		notifyAll();
		// System.out.println("Equilibrate Site " + Thread.currentThread().getName()+ "
		// Site Numéro " +Site.this.num + " Velos: " + Site.this.compterVelo );
		System.out.print("après equilibrage :");
		afficher();

	}

	public void afficher() {
		System.out.println(Thread.currentThread().getName() + " Site Numéro " + Site.this.num
				+ " Stock courant: " + Site.this.compterVelo);
	}

	static public void main(String[] args) {

	}
}
