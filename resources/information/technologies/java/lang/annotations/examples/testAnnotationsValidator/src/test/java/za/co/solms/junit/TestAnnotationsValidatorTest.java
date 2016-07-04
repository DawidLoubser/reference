/**
 * 
 */
package za.co.solms.junit;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.tools.JavaCompiler.CompilationTask;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is a unit test for the {@link TestAnnotationsValidator} which
 * is supposed to validate that only methods which have no parameters
 * and return void are annotated as test cases.
 * 
 * @author fritz@solms.co.za
 *
 */
public class TestAnnotationsValidatorTest
{
	@BeforeClass
	public static void initializeCompiler()
	{
		compiler = ToolProvider.getSystemJavaCompiler();
		
		optionList = new ArrayList<String>();
		optionList.addAll(Arrays.asList(
				"-classpath","/home/fritz/.m2/repository/junit/junit/4.8.2/junit-4.8.2.jar",
				"-processor","za.co.solms.junit.TestAnnotationsValidator")
			);
	}

	@Test
	public void testParsesVoidNoParamsTestMethod()
	{
		StringWriter compilerOutput = new StringWriter();
		String pathToSourceFile 
			= "src/test/java/za/co/solms/junit/ClassWithVoidNoParamsTestMethod.java";

		Boolean compilationSuccess = compile(pathToSourceFile, compilerOutput);
		
		if (compilationSuccess)
			logger.info("** Works - did not get compilation error.");
		else
			fail("Should not have received compilation error: " + compilerOutput.toString());
	}
	
	@Test
	public void testFailsNonVoidTestMethods()
	{
		StringWriter compilerOutput = new StringWriter();
		String pathToSourceFile 
			= "src/test/java/za/co/solms/junit/ClassWithNonVoidNoParamsTestMethod.java";

		Boolean compilationSuccess = compile(pathToSourceFile, compilerOutput);
		
		if (compilationSuccess)
			fail("Should have received a compilation error due to AnnotationsProcessor");
		else
			logger.info("** Works - got " + compilerOutput.toString());
	}
	
	@Test
	public void testFailsTestMethodsWithParams()
	{
		StringWriter compilerOutput = new StringWriter();
		String pathToSourceFile 
			= "src/test/java/za/co/solms/junit/ClassWithParamsTestMethod.java";

		Boolean compilationSuccess = compile(pathToSourceFile, compilerOutput);
		
		if (compilationSuccess)
			fail("Should have received a compilation error due to AnnotationsProcessor");
		else
			logger.info("** Works - got " + compilerOutput.toString());
	}
	
	
	private boolean compile(String pathToSourceFile, Writer compilerOutput)
	{	
		File[] files = new File[1];
		StringBuffer sb;
		files[0] = new File(pathToSourceFile);
		logger.info("** File " + files[0].getName() + " exists? " + files[0].exists());
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
		CompilationTask compilationTask 
		  = compiler.getTask(compilerOutput, fileManager, null, optionList, null,  
				fileManager.getJavaFileObjectsFromFiles(Arrays.asList(files)));
		logger.info("** Now compiling ...");
		
		return compilationTask.call();
	}

	private static JavaCompiler compiler;
	private static List<String> optionList;
	private static Logger logger = Logger.getLogger(TestAnnotationsValidatorTest.class.getName());
}
