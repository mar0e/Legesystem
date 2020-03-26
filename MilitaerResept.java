package oblig4;

/**
 * Sub-klasse av HvitResept for milit�re resepter.
 * @author ronni
 *
 */
public class MilitaerResept extends HvitResept{
	
	/**
	 * Eneste konstrukt�r for MilitaerResept.
	 * @param legemiddel et Legemiddel-objekt.
	 * @param utskrivendeLege et Lege-objekt.
	 * @param pasientID en pasients ID som integer.
	 * @param reit gjenst�ende "bruk" av resepten, som integer.
	 */
	public MilitaerResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
		super(legemiddel, utskrivendeLege, pasient, reit);
	}
	
	@Override
	public double prisAaBetale() {
		int pris = 0;
		return pris;
	}
	
	@Override
	public String toString() {
		String alt = reseptMiddel.hentID() + "," +reseptLege.hentNavn() + "," + IDPasient.hentID();
		alt += "," + "militaer" + "," + reseptReit;
		return alt;
	}
}
