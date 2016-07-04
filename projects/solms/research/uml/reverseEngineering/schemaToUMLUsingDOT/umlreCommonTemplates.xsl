<?xml version="1.0" encoding="UTF-8"?>

<!-- Common templates for XML Schema - UML reverse-engineering -->

<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0"
    xmlns:xs="http://www.w3.org/2001/XMLSchema">
    
    <xsl:output method="text"/>
    
    <!-- Prints the title of the graph -->
    <xsl:template name="diagramTitle"><xsl:variable name="namespace">
        <xsl:choose>
            <xsl:when test="/xs:schema/@targetNamespace">
                <xsl:value-of select="/xs:schema/@targetNamespace"/>
            </xsl:when>
            <xsl:otherwise>no namespace</xsl:otherwise>
        </xsl:choose>
    </xsl:variable>Generated from XML Schema\n(<xsl:value-of select="$namespace"/>)</xsl:template>
    
    
    <!-- Writes an inline label for an attribute -->
    <xsl:template name="inlineAttribute"><xsl:param name="toTypeName"/><xsl:param name="roleName"/><xsl:param name="multLabel"/><xsl:variable name="typeExistsInThisSchema" select="count( /*/xs:complexType[@name = $toTypeName]) >= 1"/><xsl:if test="not($typeExistsInThisSchema)"><xsl:value-of select="$roleName"/>\ :\ <xsl:value-of select="$toTypeName"/>\ <xsl:value-of select="$multLabel"/>\n</xsl:if></xsl:template>
    
    
    <!-- A template to generate a multiplicity label for inline attributes, in the form '[0..*]', for example. 
        Does not return anything if '[1..1'] as this is implied -->
    <xsl:template name="generateUMLMultiplicityForAttribute">
        <xsl:param name="minOccurs"/>
        <xsl:param name="maxOccurs"/>
        <xsl:variable name="multString">
            <xsl:call-template name="generateUMLMultiplicity">
                <xsl:with-param name="min" select="$minOccurs"/>
                <xsl:with-param name="max" select="$maxOccurs"/>
            </xsl:call-template>
        </xsl:variable>
        <xsl:choose>
            <xsl:when test="$multString != '1'">[<xsl:value-of select="$multString"/>]</xsl:when>
        </xsl:choose>
    </xsl:template>
    
    
    <!-- Given two Schema multiplicites (minoccurs, maxoccurs), figures out the UML 
        multiplicity string. Both parameters taken literally from XML schema, e.g. 'unbounded', 
        or empty node sets if not existent-->
    <xsl:template name="generateUMLMultiplicity">
        <!-- As we got them from XML Schema -->
        <xsl:param name="min"/>
        <xsl:param name="max"/>
        
        <!-- Normalise them -->
        <xsl:variable name="umlMin">
            <xsl:choose>
                <xsl:when test="$min">
                    <xsl:value-of select="$min"/>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>1</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        <xsl:variable name="umlMax">
            <xsl:choose>
                <xsl:when test="$max">
                    <xsl:choose>
                        <xsl:when test="$max = 'unbounded'">
                            <xsl:text>*</xsl:text>
                        </xsl:when>
                        <xsl:otherwise>
                            <xsl:value-of select="$max"/>
                        </xsl:otherwise>
                    </xsl:choose>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:text>1</xsl:text>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        
        <!-- Compose UML multiplicity string, dropping duplicates, converting 0..* to shorthand ('*'), etc -->
        <xsl:variable name="multString">
            <xsl:choose>
                <!-- If the same, only present the one -->
                <xsl:when test="$umlMin = $umlMax">
                    <xsl:value-of select="$umlMin"/>
                </xsl:when>
                <!-- Convert 0..* to shorthand -->
                <xsl:when test="$umlMin = '0' and $umlMax = '*'">
                    <xsl:text>*</xsl:text>
                </xsl:when>
                <xsl:otherwise>
                    <xsl:value-of select="$umlMin"/>..<xsl:value-of select="$umlMax"/>
                </xsl:otherwise>
            </xsl:choose>
        </xsl:variable>
        
        <!-- Finally, output UML string -->
        <xsl:value-of select="$multString"/>
        
    </xsl:template>
    
</xsl:stylesheet>
