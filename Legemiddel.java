package oblig4;
/**
 * Abstrakt klasse for legemidler
 * @author ronni
 *
 */
public abstract class Legemiddel {
	
	// navn, ID og pris (double)
	// hvor mye mg virkestoffet inneholder totalt (double)
	protected String navn = "";
	protected double pris = 0;
	protected double total = 0;
	protected int id;
	
	protected static int totalID = -1;
	
	/**
	 * Eneste konstrukt�r for Legemiddel
	 * @param n navn p� legemiddelet
	 * @param p pris p� legemiddelet
	 * @param mg antall mg legemiddelet inneholder
	 */
	public Legemiddel(String n, double p, double mg) {
		navn = n;
		pris = p;
		total = mg;
		
		totalID++;
		id = totalID;
	}
	
	/**
	 * Henter Legemiddel-subklasse sin unike ID
	 * @return ID-en som en integer
	 */
	public int hentID() {
		return id;
	}
	
	/**
	 * Henter navnet til legemiddelet.
	 * @return navnet som en String
	 */
	public String hentNavn() {
		return navn;
	}
	
	/**
	 * Henter prisen p� legemiddelet.
	 * @return prisen som double
	 */
	public double hentPris() {
		return pris;
	}
	
	/**
	 * Henter antall mg legemiddelet inneholder
	 * @return antall mg som double
	 */
	public double hentVirkestoff() {
		return total;
	}
	
	/**
	 * Muliggj�r � sette ny pris p� legemiddelet.
	 * Modifiserer "pris"-klassevariabelen.
	 * @param nyPris den nye �nskede prisen
	 */
	public void settNyPris(double nyPris) {
		pris = nyPris;
	}
	
	@Override
	abstract public String toString();
}
