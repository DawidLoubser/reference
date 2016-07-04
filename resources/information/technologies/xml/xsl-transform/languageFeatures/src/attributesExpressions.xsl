<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
    
    <xsl:template match="weather">
        <listOfPlaces>
            <xsl:apply-templates/>
        </listOfPlaces>
    </xsl:template>
    
    <xsl:template match="forecast">
        <place name="{placeName}"/>
    </xsl:template>
    
</xsl:stylesheet>