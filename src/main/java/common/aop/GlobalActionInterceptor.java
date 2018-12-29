package common.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalActionInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		System.err.println("global interceptor controller action...");
		inv.invoke();
	}

}
