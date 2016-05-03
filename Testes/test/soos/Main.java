package soos;

import easyaccept.EasyAccept;

public class Main {
	public static void main(String[] args) {
	    args = new String[] {"control.Facade", "Testes/usecase_1.txt"}; 
	    //separe cada script de teste por virgula.
	    EasyAccept.main(args);
	}

}
