package za.co.solms.publishing.books.ui.web;

import java.io.Serializable;
import java.util.StringTokenizer;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import za.co.solms.publishing.books.model.Names;

@FacesConverter(value="za.co.solms.fullNamesConverter")
public class FullNamesConverter implements Converter, Serializable
{

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cmp, String value)
	{
		String firstName="", middleNames="", lastName="";

		StringTokenizer tokenizer = new StringTokenizer(value);
		
		int numNames = tokenizer.countTokens();
		if (numNames > 0)
			firstName = tokenizer.nextToken(" ");
		
		StringBuffer midNames = new StringBuffer();
		for (int numName=1; numName<numNames-1; ++numName)
		{
			if (numName>1)
				midNames.append(" ");
			midNames.append(tokenizer.nextToken());
		}
		middleNames = midNames.toString();
		
		if (numNames > 1)
			lastName = tokenizer.nextToken();
		
		return new Names(firstName, middleNames, lastName);
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cmp, Object value)
	{
		Names names = (Names)value;
		return names.getFirstName() + " " + names.getMiddleNames() + " " + names.getLastName();
	}

}
