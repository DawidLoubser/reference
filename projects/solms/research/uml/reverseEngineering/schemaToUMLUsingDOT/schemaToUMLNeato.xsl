<?xml version="1.0" encoding="UTF-8"?>
<!-- This is a simple style sheet that reverse-engineers a Schema to generate a UML diagram,
    by rendering to the 'neato' energy-minimised graph format.
    .
    It currently only functions with simple schemas, and glaring omissions are:
    
    - Support for Element and Attribute groups (this should either be done by introducing specialisation (and multiple inheritance)
      by introducing classes for element and attribute groups - or through simple copying (though information is then lost) 
    - Support to only render a particular class (or classes) and their relationships
    - Namespace support (IMPORTANT!)
    - Support for *imported* schemas
    - Support to only render specific information, e.g. a specific class, and it's relationships to "n" levels, for example...
    - Support to indicate points of no further information, e.g. either ebcause this was asked not to be rendered, or
      because e.g. a class being extended is in a separate schema, and this separate schema is not available. (maybe
      render with dotted lines, etc?)
    - Support combining two relationships into a single bi-directional relationsihp between two objects
    - Support for *association* links, based on keys / key-references
    - Support to only show the attributes compartment of classes, of there are any entries in such a compartment (i.e. never an empty compartment)
    
    This style sheet is part of ongoing research to reverse-engineer UML from both XML schema, and (more importantly) from
    XMI. A common framework is needed to support either of these, i.e. common templates available to both XMI- and Schema-
    specific style sheets.
    
    @author D. Loubser (Solms TCD)
    @version 0.0.2 pre-alpha (24 May 2006)
-->
    
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0"
    xmlns:xs="http://www.w3.org/2001/XMLSchema">
    
    <!-- Import common UML rendering templates -->
    <xsl:import href="umlreCommonTemplates.xsl"/>
    
    <!-- Plaintext output -->
    <xsl:output method="text"/>
    
    <!-- Overall schema (graph base) -->
    <xsl:template match="xs:schema" xml:space="preserve">
graph G
{
        overlap=false;
        splines=true;
        sep=0.2;
        label="<xsl:call-template name="diagramTitle"/>";
            <xsl:apply-templates select="xs:complexType"/>
}
    </xsl:template>
    
    
    <!-- Each complex type -->
    <xsl:template match="xs:complexType" xml:space="preserve">
        <!-- Main style -->
        <xsl:value-of select="@name"/>[shape=record, label="{<xsl:if test="@abstract = 'true'">\&lt;\&lt;Abstract\&gt;\&gt;\n</xsl:if><xsl:value-of select="@name"/>|<xsl:call-template name="umlAttributes"><xsl:with-param name="type" select="."></xsl:with-param></xsl:call-template>}"];
        
        <!-- Extension relationships -->
        <xsl:call-template name="complexTypeExtension">
            <xsl:with-param name="type" select="."/>
        </xsl:call-template>
        
        <!-- Aggregation relationships (only to other, known complex types) -->
        <xsl:call-template name="complexTypeAggregates">
            <xsl:with-param name="type" select="."/>
        </xsl:call-template>
        
    </xsl:template>
    
    <!-- Attributes -->
    <xsl:template name="umlAttributes">
        <xsl:param name="type"/>
        <!-- Both elements and attributes -->
        <xsl:for-each select="$type//xs:attribute | $type//xs:element">
            <xsl:call-template name="inlineAttribute">
                <xsl:with-param name="toTypeName"><xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="@type"/></xsl:call-template></xsl:with-param>
                <xsl:with-param name="roleName"><xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="@name"/></xsl:call-template></xsl:with-param>
                <xsl:with-param name="multLabel">
                    <!-- If this is an 'element' -->
                    <xsl:if test="@minOccurs or @maxOccurs">
                        <xsl:call-template name="generateUMLMultiplicityForAttribute">
                            <xsl:with-param name="minOccurs" select="@minOccurs"/>
                            <xsl:with-param name="maxOccurs" select="@maxOccurs"/>
                        </xsl:call-template>
                    </xsl:if>
                    <!-- If this is an 'attribute' -->
                    <xsl:if test="@use">
                        <xsl:call-template name="generateUMLMultiplicityForAttribute">
                            <xsl:with-param name="minOccurs">
                                <xsl:if test="@use = 'optional' or @use = 'prohibited'">0</xsl:if>
                                <xsl:if test="@use = 'required'">1</xsl:if>
                            </xsl:with-param>
                            <xsl:with-param name="maxOccurs">1</xsl:with-param>
                        </xsl:call-template>
                    </xsl:if>
                </xsl:with-param>
            </xsl:call-template>
        </xsl:for-each>
    </xsl:template>
    
    
    
    <!-- The extensions / restrictions for each complex type -->
    <xsl:template name="complexTypeExtension">
        <xsl:param name="type"/>
        <xsl:for-each select="$type//xs:extension | $type//xs:restriction" xml:space="preserve">
            <xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="$type/@name"/></xsl:call-template> -- <xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="@base"/></xsl:call-template> [arrowhead=empty,weight=8];
        </xsl:for-each>
    </xsl:template>
    
    
    <!-- Template to print the local name of a possibly qualified name -->
    <xsl:template name="stripTypeName">
        <xsl:param name="typeName"/>
        <xsl:choose>
            <xsl:when test="contains($typeName, ':')">
                <xsl:value-of select="substring-after($typeName, ':')"/>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$typeName"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template name="complexTypeAggregates">
        <xsl:param name="type"/>
        <xsl:for-each select="$type//xs:element">
            <xsl:call-template name="associateLink">
                <xsl:with-param name="fromTypeName"><xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="$type/@name"></xsl:with-param></xsl:call-template></xsl:with-param>
                <xsl:with-param name="toTypeName"><xsl:call-template name="stripTypeName"><xsl:with-param name="typeName" select="@type"></xsl:with-param></xsl:call-template></xsl:with-param>
                <xsl:with-param name="roleName" select="@name"/>
                <xsl:with-param name="multiplicityMin" select="@minOccurs"/>
                <xsl:with-param name="multiplicityMax" select="@maxOccurs"/>
            </xsl:call-template>                
        </xsl:for-each>
    </xsl:template>
    
    <!-- Generate a link - use only already-stripped names -->
    <xsl:template name="associateLink">
        <xsl:param name="fromTypeName"/>
        <xsl:param name="toTypeName"/>
        <xsl:param name="roleName"/>
        <xsl:param name="multiplicityMin"/>
        <xsl:param name="multiplicityMax"/>
        
        <!-- Only generate link to known complex type -->
        <xsl:variable name="typeExistsInThisSchema" select="count( /*/xs:complexType[@name = $toTypeName]) >= 1"/>
        <xsl:choose>
            <xsl:when test="$typeExistsInThisSchema">
                <!-- Multiplicity string -->
                <xsl:variable name="multiplicity">
                    <xsl:call-template name="generateUMLMultiplicity">
                        <xsl:with-param name="min" select="$multiplicityMin"/>
                        <xsl:with-param name="max" select="$multiplicityMax"/>
                    </xsl:call-template>
                </xsl:variable>
                <!-- Only add multiplicity to the actual label if it's not '1' (which is implied) -->
                <xsl:variable name="multLabel">
                    <xsl:if test="$multiplicity = '1'"/>
                    <xsl:if test="$multiplicity != '1'">\ \ <xsl:value-of select="$multiplicity"/></xsl:if>
                </xsl:variable>
                <xsl:value-of select="$fromTypeName"/> -- <xsl:value-of select="$toTypeName"/> [arrowhead=open,decorate=true,headlabel="<xsl:value-of select="$roleName"/><xsl:value-of select="$multLabel"/>",labeldistance=2.0,arrowtail=diamond];
            </xsl:when>
        </xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>