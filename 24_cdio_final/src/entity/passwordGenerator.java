package entity;

public class passwordGenerator {
	
	public String createPassword() {
		
		StringBuffer sb = new StringBuffer();
		
		int x = 0;
		int r;
		
		do {
			r = (int) (Math.random() * 25) + 65; //Asci table
			char c = (char) r;
			sb.append(c);
			x++;
		} while(x != 3);
		
		x = 0;
		
		do {
			r = (int) (Math.random() * 25) + 97;
			char c = (char) r;
			sb.append(c);
			x++;
		} while (x != 3);
		
		x = 0;
		
		do {
			r = (int) (Math.random() * 9);
			sb.append(Integer.toString(r));
			x++;
		} while (x != 3);

		
		return sb.toString();
		
	}
	
}
