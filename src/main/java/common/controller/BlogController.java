package common.controller;
import java.util.ArrayList;
import java.util.Date;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import common.aop.DemoInterceptor;
import common.aop.MyClassIntercepter;
import common.model.Blog;

//配置一个Class级别的拦截器，她将拦截本类中的所有方法
@Before(MyClassIntercepter.class)
public class BlogController extends Controller {
	/*默认的index方法，
	浏览器输入/blog就可以访问到BlogController里的默认方法index，
	访问/blog 等于是访问/blog/index
	访问/blog/add 就是访问到BlogController里边的add方法*/
	
	/**
	 * 原先的index，开始时写的
	 */
	public void indexOld(){
		Blog user=Blog.me.findFirst("select count(id) num from blog");
		
		/*renderText("Hello JFinal World...........");*/   //这个地方是不会有效果的，直接跳转了。
		render("index.jsp");
		//通过render渲染视图目录下的index.jsp文件
	}
	/**
	 * 一般情况下使用BlogController中的默认方法(index)作为列表页显示纪录
	 */
	public void index(){
		
		ArrayList a=(ArrayList)Blog.me.find("select * from blog");//查询数据给ArrayList
		setAttr("blogs",a);//将结果放到request对象中，然后传递到下一页去
		
		render("index.jsp");//渲染视图文件
		//renderJsp("index.jsp");
	}

		
	/**
	 *	开始写的form,旧版本的
	 * 显示新增加的表单
	 */
	public void formOld() {		
		render("form.jsp");
	}
	
	/**
	 * 用来验证？id=..的使用方法，通过判断，来巧妙的区分增加还是修改
	 */
	public void form(){		
		Integer id=getParaToInt("id"); //接收ID参数转为integer类型
		if(id!=null && id>0) { //如果id不为空且大于0，执行编辑操作
			setAttr("blog",Blog.me.findById(id));//从数据库中找到该条记录放到request对象中
		}
		render("form.jsp");//渲染页面，编辑的界面，可以提交修改
	}

	/**
	 * 旧版本的保存，比较新的测试使用的版本
	 * 表单提交后，保存
	 */
	public void saveOld() {
		Blog blog=getModel(Blog.class,"blog");//将表单中的数据转成blog对象，这里第二个参数blog就是前面表单中blog.title,blog.content中的blog，可以更换名字，当然要跟前面表单里的保持一致
			blog.set("pubtime",new Date()); //添加发布时间,因为表单里没有时间这一项
			blog.save(); //保存数据
			renderText("新增完毕"); //显示文本提示信息
	}
	
	/**
	 * 根据参数的有无来判断是保存还是修改
	 */
	// 配置多个Method级别的拦截器，仅拦截本方法
	//@Before({BbbInter.class, CccInter.class})
	@Before({DemoInterceptor.class})
	public void save() {
		Blog blog=getModel(Blog.class,"blog");//将表单中的数据转成blog对象
		
		if(blog.get("id")==null) { //新增
			blog.set("pubtime",new Date()); //添加发布时间
			blog.save(); //保存数据
			
		}else if(blog.getInt("id")>0) { //修改
			blog.update();			
		}

		index();//访问index方法跳转到显示列表页
	}

	/**
	 * 根据id进行删除，如果id为空或者小于等于零，删除失败
	 */
	public void delete() {
		Integer id=getParaToInt("id");//获取id参数
		if(id!=null && id>0){
			boolean flag=Blog.me.deleteById(id); //执行删除操作
			if(!flag) {
				renderText("删除失败");
				return; //结束，不再往下执行。
			}
		}else{
			renderText("删除失败");
			return; //结束，不再往下执行。
		}
		index(); //如果删除成功的话，跳转到显示列表页
	}

	// 本例仅为示例, 并未严格考虑账户状态等业务逻辑
	@Before(Tx.class)
	public void blog_tx() {
		// 获取转出blogId
	    Integer blogId = getParaToInt("id");
	    
	    String title = getPara("title")+"ttt";
	    
	    Db.update("update blog set content = ? where id = ?",title, blogId);
	    
	    index(); //如果修改成功的话，跳转到显示列表页
	    
	    //事务浏览器测试
	    //http://localhost:8080/demo/blog/blog_tx?id=4
	}
	/*
	public void listWithCache() {
		ArrayList a=(ArrayList) Blog.me.find("select * from blog");//查询数据给ArrayList
		List<Blog> blogList = Blog.me.findByCache("oneCache", "key", "select * from blog");
	    setAttr("blogs", blogList).render("list.jsp");
	    //将结果放到request对象中，然后传递到下一页去
		//渲染视图文件
	}*/
	
	
}
