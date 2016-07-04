<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:template match="employee">
        <emp>
            <xsl:attribute name="number">
                <xsl:value-of select="empno"/>
            </xsl:attribute>
            <phone>
                <xsl:value-of select="phone-no"/>
            </phone>
            <fullname>
                <xsl:value-of select="name"/>
                <xsl:text xml:space="preserve"> </xsl:text>
                <xsl:value-of select="surname"/>
            </fullname>
        </emp>
    </xsl:template>
    
</xsl:stylesheet>
