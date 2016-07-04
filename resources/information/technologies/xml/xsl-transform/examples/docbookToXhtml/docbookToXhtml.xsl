<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs"
    xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl" version="2.0"
    xmlns:db="http://docbook.org/ns/docbook">
    <xsl:output method="xhtml" indent="yes"/>

    <xsl:variable name="sectionLevel">1</xsl:variable>
    
    <xsl:template match="/">
        <html>
            <head>
                
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>    
    </xsl:template>
    
    <xsl:template match="db:section">
        <xsl:variable name="sectionLevel"><xsl:value-of select="$sectionLevel+1"/></xsl:variable>
        <xsl:apply-templates/>
    </xsl:template>
    
    <xsl:template match="db:title">
        <xsl:choose>
            <xsl:when test="$sectionLevel=1">
                <h1><xsl:apply-templates/></h1>                
            </xsl:when>
            <xsl:when test="$sectionLevel=2">
                <h2><xsl:apply-templates/></h2>                
            </xsl:when>
            <xsl:when test="$sectionLevel=3">
                <h3><xsl:apply-templates/></h3>                
            </xsl:when>
            <xsl:when test="$sectionLevel=4">
                <h4><xsl:apply-templates/></h4>                
            </xsl:when>
            <xsl:when test="$sectionLevel=5">
                <h5><xsl:apply-templates/></h5>                
            </xsl:when>
            <xsl:when test="$sectionLevel=6">
                <h6><xsl:apply-templates/></h6>                
            </xsl:when>
        </xsl:choose>
    </xsl:template>
    
    <xsl:template match="db:para">
        <p>
            <xsl:apply-templates/>
        </p>    
    </xsl:template>
    
    <xsl:template match="db:itemizedlist">
        <ul>
            <xsl:apply-templates/>
        </ul>    
    </xsl:template>
    <xsl:template match="db:listitem">
        <li><xsl:value-of select="db:para"/></li>    
    </xsl:template>
</xsl:stylesheet>

<!-- 
    

    ****** Level: $sectionLevel
-->