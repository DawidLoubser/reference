<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
    
    <xsl:template match="weather">
        <temperatures>
            <xsl:apply-templates/>
        </temperatures>
    </xsl:template>
    
    <xsl:template match="forecast">
        <xsl:variable name="no">
            <xsl:number/>
        </xsl:variable>
        <temperature 
            place="{$no}: {placeName}"
            min="{max}"
            max="{min}"
            average="{(min+max) div 2}"/>
    </xsl:template>
    
</xsl:stylesheet>
