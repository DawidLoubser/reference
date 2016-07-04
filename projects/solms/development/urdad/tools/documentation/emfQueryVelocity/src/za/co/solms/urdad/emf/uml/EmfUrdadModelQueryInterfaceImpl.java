package za.co.solms.urdad.emf.uml;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Comment;
import org.eclipse.uml2.uml.Dependency;
import org.eclipse.uml2.uml.DirectedRelationship;
import org.eclipse.uml2.uml.Element;
import org.eclipse.uml2.uml.Extend;
import org.eclipse.uml2.uml.Include;
import org.eclipse.uml2.uml.Interface;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Relationship;
import org.eclipse.uml2.uml.UseCase;
import org.eclipse.uml2.uml.util.UMLUtil;

public class EmfUrdadModelQueryInterfaceImpl implements EmfUrdadModelQueryInterface
{
	public EmfUrdadModelQueryInterfaceImpl(Resource umlResource)
	{
		this.umlResource = umlResource;
	}

	@Override
	public Set<ConditionalFunctionalRequirement> getConditionalFunctionalRequirements(
			UseCase useCase)
	{
		Set<ConditionalFunctionalRequirement> conditionalFunctionalRequirements = new HashSet<ConditionalFunctionalRequirement>();
		for (DirectedRelationship directedRelationship : useCase.getTargetDirectedRelationships())
		{
			if (Extend.class.isAssignableFrom(directedRelationship.getClass()))
			{	
				Extend extend = (Extend)directedRelationship;
				for (Element element : extend.getSources())
					if (UseCase.class.isAssignableFrom(element.getClass()))
						conditionalFunctionalRequirements.add
							(new ConditionalFunctionalRequirement((UseCase)element, extend.getCondition()));
			}	
		}
		return conditionalFunctionalRequirements;
	}

	@Override
	public Set<UseCase> getMandatoryFunctionalRequirements(UseCase useCase)
	{
		Set<UseCase> functionalRequirements = new HashSet<UseCase>();
		for (Include include: useCase.getIncludes())
		{
			for (Element element : include.getTargets())
				if (UseCase.class.isAssignableFrom(element.getClass()))
					functionalRequirements.add((UseCase)element);
		}
		return functionalRequirements;
	}

	@Override
	public Set<UseCase> getFunctionalRequirements(UseCase useCase)
	{
		Set<UseCase> functionalRequirements = getMandatoryFunctionalRequirements(useCase);
		for (ConditionalFunctionalRequirement conditionalFunctionalRequirement : getConditionalFunctionalRequirements(useCase))
			functionalRequirements.add(conditionalFunctionalRequirement.getFunctionalRequirement());
		
		return functionalRequirements;
	}

	@Override
	public Set<Interface> getStakeholdersForUseCase(UseCase useCase)
	{
		Set<UseCase> functionalRequirements = getFunctionalRequirements(useCase);
		Set<Interface> stakeholders = new HashSet<Interface>();
		for (UseCase functionalRequirement : functionalRequirements)
			stakeholders.addAll(getStakeholdersForFunctionalRequirement(functionalRequirement));
		
		return stakeholders;
	}

	@Override
	public Set<Interface> getStakeholdersForFunctionalRequirement(UseCase functionalRequirement)
	{
		Set<Interface> stakeholders = new HashSet<Interface>();
		for (DirectedRelationship targetRelationship : functionalRequirement.getTargetDirectedRelationships())
			if (Dependency.class.isAssignableFrom(targetRelationship.getClass()))
				for (Element element : targetRelationship.getSources())
					if (Interface.class.isAssignableFrom(element.getClass()))
							stakeholders.add((Interface)element);

		return stakeholders;
	}
	
	@SuppressWarnings("unchecked")
	public NamedElement getNamedElement(String qualifiedElementName, Class type) throws ElementDoesNotExistException
	{
		// Add a Data for the root of the model
		 String rootQualifiedElementName = "Data::" + qualifiedElementName;

		Collection<NamedElement> elements = UMLUtil.findNamedElements(umlResource, rootQualifiedElementName);
		
		for (NamedElement e:elements)
			if (type.isAssignableFrom(e.getClass()))
				return e;

		throw new ElementDoesNotExistException();
	}
	
	@SuppressWarnings("unchecked")
	public EList<Relationship> getRelationships(Element element, Class relatioshipClass)
	{
		EList<Relationship> relationships = element.getRelationships();
  
		EList<Relationship> result = new UniqueEList<Relationship>();
		
		Iterator<Relationship> iter = relationships.iterator();
		while (iter.hasNext())
		{
			Relationship relationship = iter.next();
			if (Association.class.isAssignableFrom(relationship.getClass()))
				result.add(relationship);
		}
		return result;
	}
	
	public Interface getUserContract(UseCase useCase) throws UserContractNotSpecifiedException, MultipleUserContractException
	{
		EList<Relationship> associations = getRelationships(useCase, Association.class);
		if (associations.size() == 0)
			throw new UserContractNotSpecifiedException();
		if (associations.size() > 1)
			throw new MultipleUserContractException();
		
		Association association = (Association)associations.get(0);
		
		Iterator<Element> relatedElements = association.getRelatedElements().iterator();
		while (relatedElements.hasNext())
		{
			Element relatedElement = relatedElements.next();
			if (Interface.class.isAssignableFrom(relatedElement.getClass()))
					return (Interface)relatedElement;
		}
		throw new UserContractNotSpecifiedException();
	}

	
	public UseCase getUseCase(String qualifiedUseCaseName) throws ElementDoesNotExistException
	{
		return (UseCase)this.getNamedElement(qualifiedUseCaseName, UseCase.class);
	}
	
	public List<String> getAnnotations(Element element)
	{
		List<String> result = new LinkedList<String>();
		
		for (Comment comment : element.getOwnedComments())
			result.add(comment.getBody());
		
		return result;
	}

	public Resource getUmlResource()
	{
		return umlResource;
	}
	
	private Resource umlResource;
}
