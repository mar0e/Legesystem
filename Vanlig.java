package oblig4;

/**
 * Sub-klasse til Legemiddel for vanlige legemidler.
 * @author ronni
 *
 */
public class Vanlig extends Legemiddel{
	
	/**
	 * Eneste konstruktør for Vanlig.
	 * @param navn String-navn på legemiddelet.
	 * @param pris prisen på legemiddelet som double.
	 * @param mg antall mg legemiddelet innehar som double.
	 */
	public Vanlig(String navn, double pris, double mg) {
		super(navn, pris, mg);
	}
	
	public String toString() {
		String tekst = navn + "," + "vanlig" + "," + pris + "," + total;
		return tekst;
	}
	
}
