<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
    
    <xsl:template match="weather">
        <xsl:comment>
            Sorted output produced automatically using XSLT
        </xsl:comment>
        <temperatures>
            <xsl:apply-templates>
                <xsl:sort data-type="number" select="min"/>
            </xsl:apply-templates>
        </temperatures>
    </xsl:template>
    
    <xsl:template match="forecast">
        <temperature 
            place="{placeName}"
            min="{max}"
            max="{min}"
            average="{(min+max) div 2}"/>
    </xsl:template>
    
</xsl:stylesheet>
