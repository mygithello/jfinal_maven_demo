package common;

import com.jfinal.core.JFinal;

public class runserver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//JFinal.start("WebRoot", 8081, "/", 5);
		JFinal.start("src/main/webapp", 8086, "/");
	}

}
