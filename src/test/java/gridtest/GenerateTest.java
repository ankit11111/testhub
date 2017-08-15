/**
 * 
 */
package gridtest;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Utilities;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

/**
 * @author gaurav.khandelwal
 * 
 */
public class GenerateTest {

	/**
	 * test main method
	 */
	public static void main(String[] args) {
		String hubUrl = "http://172.26.55.76:4444/wd/hub";
		String browser = "firefox";
		String baseUrl = "http://google.com";
		generateAndRunTest(hubUrl, browser, baseUrl);
	}

	/**
	 * 
	 * This is responsible for generating the testNG xml on the fly and execute
	 * it with the provided parameters
	 * 
	 * **/
	public static void generateAndRunTest(String hubUrl, String browser,
			String baseUrl) {
		// Logic to generate the TestNg XML. This is just a demo, have to change
		// exact according to our test-cases
		XmlSuite suite = new XmlSuite();
		suite.setName("NewSuite");
		
		suite.addListener("util.TestNGCustomReportListener");
		
		XmlTest test = new XmlTest(suite);
		test.setName("NewTest");
		test.addParameter("hubUrl", hubUrl);
		test.addParameter("browser", browser);
		test.addParameter("baseUrl", baseUrl);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		classes.add(new XmlClass("test.GridTest"));
		test.setXmlClasses(classes);

		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		TestNG tng = new TestNG();
		TestListenerAdapter tla = new TestListenerAdapter();
		List<Class> listenerClasses = new ArrayList<Class>();
		listenerClasses.add(util.TestNGCustomReportListener.class);
		tng.setUseDefaultListeners(true);
		tng.addListener(tla);
		tng.setXmlSuites(suites);
		//Execute TestNG XML
		tng.run();
	}

}
