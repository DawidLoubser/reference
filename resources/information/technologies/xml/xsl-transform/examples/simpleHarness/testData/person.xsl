<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:xs="http://www.w3.org/2001/XMLSchema" exclude-result-prefixes="xs"
    xmlns:xd="http://www.oxygenxml.com/ns/doc/xsl" version="2.0">
    <xsl:output method="text"/>
    <xsl:template match="//person">Name: <xsl:value-of select="names/firstName"/> <xsl:value-of select="names/surname"/></xsl:template>
</xsl:stylesheet>
