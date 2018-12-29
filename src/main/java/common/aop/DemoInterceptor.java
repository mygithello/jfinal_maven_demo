package common.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class DemoInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		System.out.println("before methodInterceptor ...");
		inv.invoke();
	}

}
