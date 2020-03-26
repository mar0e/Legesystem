package oblig4;

/**
 * Sub-klasse av HvitResept for prevensjonsresepter.
 * @author ronni
 *
 */
public class PrevensjonResept extends HvitResept{
	
	/**
	 * Eneste konstruktør for PrevensjonResept.
	 * @param legemiddel et Legemiddel-objekt.
	 * @param utskrivendeLege et Lege-objekt.
	 * @param pasientID en pasients ID som integer.
	 */
	public PrevensjonResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
		super(legemiddel, utskrivendeLege, pasient, 3);
	}
	
	@Override
	public double prisAaBetale() {
		int pris = (int) hentLegemiddel().hentPris();
		int RABATT_GRENSE = 108;
		if((int) pris >= RABATT_GRENSE) {
			pris = (int) (pris - RABATT_GRENSE);
		} else {
			pris = 0;
		}
		return pris;
	}
	
	@Override
	public String toString() {
		String alt = reseptMiddel.hentID() + "," + reseptLege.hentNavn() + "," + IDPasient.hentID();
		alt += "," + "p";
		return alt;
	}
}
