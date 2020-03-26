package oblig4;

/**
 * Sub-klasse av Resept for bl� resepter.
 * @author ronni
 *
 */
public class BlaaResept extends Resept{
	
	/**
	 * Eneste konstrukt�r for Bl�Resept.
	 * @param legemiddel et Legemiddel-objekt.
	 * @param utskrivendeLege et Lege-objekt.
	 * @param pasientID en pasients ID som integer.
	 * @param reit gjenst�ende "bruk" av resepten, som integer.
	 */
	public BlaaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
		super(legemiddel, utskrivendeLege, pasient);
		reseptReit = reit;
		farge = "blaa";
	}
	
	@Override
	public String farge() {
		// midlertidig return!!!!
		return farge;
	}
	
	@Override
	public double prisAaBetale() {
		double pris = (int) hentLegemiddel().hentPris();
		pris = (double) (pris * 0.25);
		return pris;
	}
	
	@Override
	public String toString() {
		String alt = super.toString();
		alt += "," + "blaa" + "," + reseptReit;
		return alt;
	}
	
}
