/**
 * 
 */
package za.co.solms.urdad.velocity;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * @author fritz@solms.co.za
 *
 */
public class UrdadVelocityTemplateRendererImpl implements
		UrdadVelocityTemplateRenderer
{

	/* (non-Javadoc)
	 * @see za.co.solms.urdad.velocity.UrdadVelocityTemplateRenderer#renderVelocityTemplate(za.co.solms.urdad.velocity.UrdadVelocityTemplateRenderer.VelocityTemplateRenderRequest)
	 */
	@Override
	public void renderVelocityTemplate(VelocityTemplateRenderRequest renderRequest) throws VelocityException
	{
		try
		{
			
			VelocityEngine velocityEngine = new VelocityEngine();
	      velocityEngine.init();
	      
	      File file = new File(renderRequest.getPathToVelocityTemplate());
	      String templatePath = new File(file.getParent()).getAbsolutePath();

	      Properties p = new Properties();
		    p.setProperty("file.resource.loader.path", templatePath);
		    Velocity.init( p );

	      
	      Template template = velocityEngine.getTemplate( renderRequest.getPathToVelocityTemplate() );
	      logger.info("templatePath: " + templatePath);
	      
	      // Create and populate template context with qualified use case name and a handle to a URDAD model query interface
	      VelocityContext context = new VelocityContext();
	      //Provide template access to qualified use case name and the urdadModelQueryInterface
	      context.put("qualifiedUseCaseName", renderRequest.getQualifiedUseCaseName());
	      context.put("urdadModelQueryInterface", new VelocityUrdadModelQueryAdapterImpl(renderRequest.getModelQueryInterface()));

	      // Set up the resource loading
	      context.put("resource.loader", "file, class");
	      context.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.FileResourceLoader");
//	      context.put("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClassPathResourceLoader");
	      context.put("file.resource.loader.path", templatePath);
	      context.put("TEMPLATE_ROOT", templatePath);
	      context.put("file.resource.loader.cache", "true");
	      
	      // Now render the template into a StringWriter 
	      StringWriter writer = new StringWriter();
	      template.merge( context, writer );
	      
	      System.out.println( writer.toString() );
	      FileWriter fileWriter = new FileWriter(renderRequest.getPathToOutputFile());
	      fileWriter.write(writer.toString());
	      fileWriter.close();
		}
		catch (Exception e) 
		{
			logger.severe(e.toString());
			throw new VelocityException(e);
		}
	}
	
	private static Logger logger = Logger.getLogger("logger");
}
