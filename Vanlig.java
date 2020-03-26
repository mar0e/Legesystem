package oblig4;

/**
 * Sub-klasse til Legemiddel for vanlige legemidler.
 * @author ronni
 *
 */
public class Vanlig extends Legemiddel{
	
	/**
	 * Eneste konstrukt�r for Vanlig.
	 * @param navn String-navn p� legemiddelet.
	 * @param pris prisen p� legemiddelet som double.
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
