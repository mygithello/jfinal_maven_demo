package common;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;
import com.jfinal.template.Engine;
import common.aop.GlobalActionInterceptor;
import common.aop.GlobalServiceInterceptor;
import common.controller.BlogController;
import common.model.Blog;

public class CommonConfig extends JFinalConfig {
	/**
	 * 在configConstant方法中配置常量
	 */
	@Override
	public void configConstant(Constants me) {
		PropKit.use("config.properties");//使用PropKit工具读取配置文件
		me.setDevMode(PropKit.getBoolean("devMode", false)); //设置开发模式，读取配置文件中的devMode参数，如果没有的话取值为false
		me.setViewType(ViewType.JSP);//设置试图文件类型

	}

	@Override
	public void configHandler(Handlers me) {

	}

	@Override
	public void configInterceptor(Interceptors me) {
		// 添加控制层全局拦截器
	      me.addGlobalActionInterceptor(new GlobalActionInterceptor());
	  
	      // 添加业务层全局拦截器
	      me.addGlobalServiceInterceptor(new GlobalServiceInterceptor());
	  
	      // 为兼容老版本保留的方法，功能与addGlobalActionInterceptor完全一样
	      //me.add(new GlobalActionInterceptor());
	      
	      //事务？
	     /* me.add(new TxByMethodRegex("(.*save.*|.*update.*)"));
	      me.add(new TxByMethods("save", "update"));
	      me.add(new TxByActionKeyRegex("/trans.*"));
	      me.add(new TxByActionKeys("/tx/save", "/tx/update"));*/
	}
	/**
	 * 在configPlugin方法中配置数据源连接
	 * 
	 * 配置数据库映射，配置路由数据库映射：数据库中的表跟java类的对应关系路由：指定浏览器里输入的/blog由哪个控制器来处理
	 * 在configPlugin方法中配置数据库映射.
	 */
	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin C3p0Plugin = new C3p0Plugin(PropKit.get("jdbcUrl"), PropKit.get("user"), PropKit.get("password").trim());  //使用PropKit工具读取配置文件中的jdbcUrl,user,password
		me.add(C3p0Plugin);

		ActiveRecordPlugin arp = new ActiveRecordPlugin(C3p0Plugin);
		me.add(arp);
		arp.addMapping("blog", Blog.class); //数据库表映射
		//库中blog表对应blog类
		
		//添加ehcache的配置
		//me.add(new EhCachePlugin());


	}
	/**
	 * 在configRoute方法中配置路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add("/blog", BlogController.class); //路由，访问/blog由blogcontroller控制器处理，使用的试图路径为setBaseViewPath配置的路径下的blog目录
		
		/*如果路径名称不是blog，可以设置第三个参数
		me.add("/blog", BlogController.class, "blognew");
		这样就是指定到WEB-INF/view/blognew目录*/
		me.setBaseViewPath("WEB-INF/view");//设置默认试图路径，跟上面第四步项目分包时创建的视图层目录一致

	}

	@Override
	public void configEngine(Engine engine) {

	}

}
