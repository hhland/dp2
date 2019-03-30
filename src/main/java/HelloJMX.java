import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;


public class HelloJMX {

	
	
	public static void main(String[] args) throws Exception {
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		          ObjectName helloName = new ObjectName("jmxBean:name=hello");
		          //create mbean and register mbean
		          server.registerMBean(new Hello(), helloName);
		          Thread.sleep(60*60*1000);

		          
		          
    }
	
	
	
	public interface HelloMBean {
	    public String getName();
	    public void setName(String name);
	    public void printHello();
	    public void printHello(String whoName);
	} 
	
	
	public static class Hello implements HelloMBean {
	    private String name;
	    public String getName() {
	        return name;
	    }
	    public void setName(String name) {
	        this.name = name;
	    }
	    public void printHello() {
	        System.out.println("Hello World, " + name);
	    }
	    public void printHello(String whoName) {
	        System.out.println("Hello , " + whoName);
	    }
	}          
}
