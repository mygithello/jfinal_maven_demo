package common.aop;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GlobalServiceInterceptor implements Interceptor {

	public void intercept(Invocation inv) {
		System.err.println("global insterceptor service method.....");
		inv.invoke();
	}

}
