<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">

<root xmlns="http://www.w3.org/1999/XSL/Format">
  <layout-master-set>
    <simple-page-master master-name="LetterPage"
                  page-height="29.7cm"
                  page-width="21cm"
                  margin-top="3cm"
                  margin-bottom="3cm"
                  margin-left="2.5cm"
                  margin-right="2.5cm">
      <region-body margin-top="6cm"/>
    </simple-page-master>
  </layout-master-set>  
  
  <xsl:apply-templates/>
  
  </root>
</xsl:template>  

<xsl:template match="/shippingConfirmation">
  <page-sequence master-reference="LetterPage">
    <flow flow-name="xsl-region-body">
      <xsl:apply-templates/>
    </flow>  
  </page-sequence>
</xsl:template>


<xsl:template match="//salutation">
  <block>
    <xsl:apply-templates/>
  </block>  
</xsl:template>

<xsl:template match="recipient">
  <inline font-style="italic"> <xsl:value-of select="."/> </inline>
</xsl:template>

<xsl:template match="orderID">
  <inline font-weight="bold"> <xsl:value-of select="."/> </inline>
</xsl:template>

<xsl:template match="dateShipped">
  <inline font-weight="bold"> <xsl:value-of select="."/> </inline>
</xsl:template>

<xsl:template match="trackingNumber">
  <inline font-weight="bold"> <xsl:value-of select="."/> </inline>
</xsl:template>

<!--
<xsl:template match="closing">
  <p></p>
  <xsl:apply-templates/>
</xsl:template>

<xsl:template match="sender">
  <p><b><xsl:apply-templates></xsl:apply-templates></b></p>
</xsl:template>
-->

<xsl:template match="*"/>

</xsl:stylesheet>
