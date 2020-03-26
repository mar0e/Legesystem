package oblig4;

/**
 * Sub-klasse av Lege for spesialister med godkjenningsfritak.
 * @author ronni
 *
 */
public class Spesialist extends Lege implements Godkjenningsfritak {
	protected int IDKontroll;
	
	/**
	 * Eneste konstruktør for Spesialist.
	 * @param navn String-navn på spesialisten.
	 * @param kontrollID kontroll ID-en til spesialisten, som integer.
	 */
	public Spesialist(String navn, int kontrollID) {
		super(navn);
		IDKontroll = kontrollID;
	}
	
	/**
	 * Henter kontroll ID-en til spesialisten.
	 * @return IDKontroll kontroll ID-en som en integer.
	 */
	public int hentKontrollID() {
		return IDKontroll;
	}
	
	@Override
	public String toString() {
		String alt = hentNavn() + "," + IDKontroll;
		return alt;
	}
}
