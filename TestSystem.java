package oblig4;

import java.io.File;

public class TestSystem {
	
	public static void main(String[] args) throws UlovligUtskrift {
		Legesystem vilberg = new Legesystem();
		vilberg.lesFraFil("inndata.txt");
		
		vilberg.kommando();
	}
}
