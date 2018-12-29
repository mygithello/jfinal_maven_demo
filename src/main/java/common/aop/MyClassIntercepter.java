package common.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class MyClassIntercepter implements Interceptor {

	public void intercept(Invocation inv) {
		System.out.println("class inceptor before controller...");
		inv.invoke();
	}

}
