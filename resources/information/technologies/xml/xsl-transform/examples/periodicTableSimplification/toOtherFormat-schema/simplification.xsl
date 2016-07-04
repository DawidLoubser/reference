<?xml version="1.0" encoding="UTF-8"?>
<!-- An XSLT Transformation that transforms the complete periodic table,
    as received from a vendor in a messy format, into a minimal
    table which conforms to our XML schema. Performs the following 
    key tasks:
    
    - Infers the state of the elements (gas, liquid, solid).
    - Translates to a namespaced vocabulary, and inserts a link to the schema
    - Inserts a link to a CSS stylesheet for visual rendering in a browser
    - Orders by atomic number
-->
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:p="urn:physics:elements"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    version="1.0">
    
    <!-- Room temperature, in Kelvin (used to determine element state) -->
    <xsl:variable name="roomTemp">300</xsl:variable>
    
    <!-- The URI of the schema to which our output document conforms.
    In practical use, this should be a publically accessible, HTTP URL-->
    <xsl:variable name="schemaURI">periodicMinimal.xsd</xsl:variable>
    
    <!-- The URI of the CSS style sheet to attach to the resulting document -->
    <xsl:variable name="cssURI">periodicMinimal.css</xsl:variable>
    
    
    <!-- From the old document root, create a new (simplified) one which has links
    to the schema, etc -->
    <xsl:template match="PERIODIC_TABLE">
        
        <!-- Indicate that we want this processing instruction in the output document, which
        contains a link to a CSS style sheet. This enables the resulting document to be
        opened in any web browser for a pleasing view, whilst still containing all the
        information which applications / technical users might require. -->
        <xsl:processing-instruction name="xml-stylesheet">type="text/css" href="<xsl:value-of select="$cssURI"/>"</xsl:processing-instruction>
        
        <!-- Insert a comment in the output -->
        <xsl:comment>This simplification automatically produced with XSLT</xsl:comment>
        
        <!-- The root of our new (output) document. Note the inclusion of the 'schemaLocation'
            attribute, which means our output document is instantly validatable. -->
        <p:periodicTable xsi:schemaLocation="urn:physics:elements {$schemaURI}">
            <xsl:apply-templates>
                <!-- Sort by atomic number -->
                <xsl:sort select="ATOMIC_NUMBER" data-type="number" order="ascending"/>
            </xsl:apply-templates>
        </p:periodicTable>
    </xsl:template>
    
    
    <!-- Transform each atom to it's new (simplified) form -->
    <xsl:template match="ATOM">
        <p:element>
            <!-- If we have sufficient information, determine the element's
            natural state -->
            <xsl:if test="BOILING_POINT and MELTING_POINT">
                <xsl:attribute name="naturalState">
                    <xsl:call-template name="determineState">
                        <xsl:with-param name="atom" select="."/>
                    </xsl:call-template>
                </xsl:attribute>
            </xsl:if>
            <!-- Other fields (name, symbol, etc) simply copied through -->
            <p:name><xsl:value-of select="NAME"/></p:name>
            <p:symbol><xsl:value-of select="SYMBOL"/></p:symbol>
            <p:atomicNumber><xsl:value-of select="ATOMIC_NUMBER"/></p:atomicNumber>
            <!-- Optional (if we have it) atomic weight -->
            <xsl:if test="ATOMIC_WEIGHT">
                <p:atomicWeight><xsl:value-of select="ATOMIC_WEIGHT"/></p:atomicWeight>
            </xsl:if>
        </p:element>
    </xsl:template>
    
    
    <!-- A template to determine the state of an element (liquid/gas/solid)
    based on an assumed room temperature. -->
    <xsl:template name="determineState">
        <xsl:param name="atom"/>
        <xsl:choose>
            <xsl:when test="$atom/BOILING_POINT &lt;= $roomTemp">gas</xsl:when>
            <xsl:when test="$atom/MELTING_POINT &lt;= $roomTemp and $atom/BOILING_POINT &gt;= $roomTemp">liquid</xsl:when>
            <xsl:when test="$atom/MELTING_POINT &gt;= $roomTemp and $atom/BOILING_POINT &gt;= $roomTemp">solid</xsl:when>
        </xsl:choose>
    </xsl:template>
    
</xsl:stylesheet>