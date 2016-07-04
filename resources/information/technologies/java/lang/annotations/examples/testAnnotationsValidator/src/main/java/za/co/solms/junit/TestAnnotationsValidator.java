/**
 * 
 */
package za.co.solms.junit;

import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import org.junit.Test;

/**
 * This class validates that only methods which take no parameter and return
 * a void are annotated as a JUnit Test Case.
 * 
 * The {@link TestAnnotationsValidator} is an {@link AbstractProcessor} which
 * can be used when compiling code via
 * <code>javac -processor za.co.solms.junit.TestAnnotationsValidator</code> xx.java</code>.
 * 
 * @author fritz@solms.co.za
 *
 */
@SupportedAnnotationTypes (value = {"org.junit.Test"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class TestAnnotationsValidator extends AbstractProcessor
{

	/* 
	 * This method reports error messages to the processing environments Messager 
	 * if a compiled class has a method annotated as a junit Test case which takes 
	 * parameters or if it does not return void.
	 * 
	 * (non-Javadoc)
	 * @see javax.annotation.processing.AbstractProcessor#process
	 *  (java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	@SuppressWarnings(value="unchecked")
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv)
	{
		if (annotations.size() == 0)
			return false;
		Messager messager = processingEnv.getMessager();
		logger.info("** Processing " + annotations.size() + " annotations");
		Set<ExecutableElement> annotatedMethods 
			= (Set<ExecutableElement>)roundEnv.getElementsAnnotatedWith(Test.class);
		logger.info("** Num annotated methods = " + annotatedMethods.size());
		for (ExecutableElement method:annotatedMethods)
		{
			logger.info("** Validating " + method.getSimpleName());
			if (method.getParameters().size() != 0)
				messager.printMessage(Kind.ERROR, "Test case has parameters.");
			if (method.getReturnType().getKind() != javax.lang.model.type.TypeKind.VOID)
				messager.printMessage(Kind.ERROR, "Test case has return value.");
		}
		return false; 
		// This processor does not claim the @Test annotations and these annotations
		// are hence available for further annotations processors.
	}

	private static Logger logger = Logger.getLogger(TestAnnotationsValidator.class.getName());
}
