import static org.junit.Assert.*;

import org.junit.Test;

public class BaseBallTestGame {

	@Test
	public void test() {
		assertTrue(check(new int[] {3,6,9}, new int[] {3,6,9}).equals("3s6b"));
	}
	String check(int []s, int []t) {
		String result="";
		int strike=0;
		int ball=0;

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(s[i]==t[i] &&i==j){
					strike++;
				}
				else if(s[i]==t[i] && i!=j){
					ball++;
				}
			}		
		}
		result=strike+"s"+ball+"b";
		System.out.println(result);
		return strike+"s"+ball+"b";
	}
}

