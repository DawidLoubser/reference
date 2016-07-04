<?xml version="1.0" encoding="UTF-8" ?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="xml" indent="yes"/>

<xsl:template match="/">

<xsl:param name="margin1"/>
<xsl:param name="margin2"/>



<root xmlns="http://www.w3.org/1999/XSL/Format">
  <layout-master-set>
    <simple-page-master master-name="TitlePage"
                  page-height="29.7cm"
                  page-width="21cm"
                  margin-top="3cm"
                  margin-bottom="3cm"
                  margin-left="2.5cm"
                  margin-right="2.5cm">
      <region-body margin-top="6cm"/>
    </simple-page-master>
    <simple-page-master master-name="FirstPageOfChapter"
                  page-height="29.7cm"
                  page-width="21cm"
                  margin-top="1cm"
                  margin-bottom="5cm"
                  margin-left="3cm"
                  margin-right="2cm">
      <region-body margin-top="4cm"/>
      <region-before extent="3cm"/>
      <region-after extent="3cm"/>
    </simple-page-master>
    <simple-page-master master-name="EvenPage"
                  page-height="29.7cm"
                  page-width="21cm"
                  margin-top="2cm"
                  margin-bottom="1cm"
                  margin-left="2cm"
                  margin-right="3cm">
       <region-before extent="2cm" region-name="EvenHeader" padding="6mm"/>           
       <region-body padding="1cm" margin-top="15mm" margin-bottom="30mm"/>
       <region-after extent="2cm" region-name="Footer" padding="6mm"/>
    </simple-page-master>
    <simple-page-master master-name="OddPage"
                  page-height="29.7cm"
                  page-width="21cm"
                  margin-top="2cm"
                  margin-bottom="1cm"
                  margin-left="3cm"
                  margin-right="2cm">
       <region-before extent="2cm" region-name="OddHeader" padding="6mm"/>           
       <region-body padding="1cm" margin-top="15mm" margin-bottom="30mm"/>
       <region-after extent="2cm" region-name="Footer" padding="6mm"/>
    </simple-page-master>
    <simple-page-master master-name="BlankPage">
      <region-body padding="1cm"/>
    </simple-page-master>

    <page-sequence-master master-name="ChapterSequence">
      <repeatable-page-master-alternatives>
        <conditional-page-master-reference page-position="first" master-reference="FirstPageOfChapter"/>
        <conditional-page-master-reference odd-or-even="even" master-reference="EvenPage"/>
        <conditional-page-master-reference odd-or-even="odd" master-reference="OddPage"/>
        <conditional-page-master-reference blank-page="blank" master-reference="BlankPage"/>
      </repeatable-page-master-alternatives>
    </page-sequence-master>

  </layout-master-set>
  
  <xsl:apply-templates/>
  
  </root>
</xsl:template>  

<xsl:template match="book"><xsl:apply-templates/></xsl:template>

<xsl:template match="/book/title">
  <page-sequence master-reference="TitlePage">
    <flow flow-name="xsl-region-body">
      <block font-size="36pt" font-family="Times" font-weight="bold" text-align="center"
                  padding-top="40pt">
        <xsl:value-of select="."/>
      </block>  
     <block font-size="32pt" font-family="Times" text-align="center" 
                 space-after.optimum="36pt">
       <xsl:value-of select="../subtitle"/>
     </block>  
     <block font-size="16pt" font-family="Times" text-align="center"
                 space-after.optimum="8pt">
       <inline> <xsl:value-of select="../bookinfo/author/firstname"/> </inline>
       <inline> <xsl:value-of select="../bookinfo/author/surname"/> </inline>
     </block>  
     <block font-size="16pt" font-family="Times" font-style="italic" text-align="center">
        <xsl:value-of select="../bookinfo/affiliation"/>
      </block>  
    </flow>  
  </page-sequence>
</xsl:template>  

<xsl:template match="chapter">
  <page-sequence master-reference="ChapterSequence"> 
      <static-content flow-name="EvenHeader">
        <block text-align="left"> 
          <inline><page-number/></inline> <inline><xsl:value-of select="/book/title"/></inline>
        </block>
      </static-content>  
      <static-content flow-name="OddHeader">
        <block text-align="right"> 
          <inline><xsl:value-of select="title"/>
          <inline><page-number/></inline> 
        </inline></block>
      </static-content>  
        <!-- float not yet implemented by FOP
      <static-content flow-name="OddHeader">
        <float float="left">
          <block text-align="center"><xsl:value-of select="title"/></block>
        </float>
        <float>
          <block text-align="right"> <page-number/></block>
        </float>  
      </static-content>  
        -->
      <static-content flow-name="Footer">
        <block text-align="center">Solms Training and Consulting</block>
      </static-content>  
    <flow flow-name="xsl-region-body">
      <block>
        <xsl:apply-templates/>
      </block>  
    </flow>  
  </page-sequence>
</xsl:template>  

<xsl:template match="chapter/title">
      <block font-size="36pt" font-family="Times" font-weight="bold" 
                  padding-top="48pt" padding-bottom="12pt">
        Chapter <xsl:number count="chapter"/>
      </block>  
      <block font-size="32pt" font-family="Times" 
                  padding-top="12pt" padding-bottom="36pt">
        <xsl:value-of select="."/>
      </block>  
</xsl:template>

<xsl:template match="sect1">
      <block font-size="24pt" font-family="Times" font-weight="bold" 
                  padding-top="24pt" padding-bottom="24pt">
          <inline>        
         <xsl:number count="chapter" format="1."/><xsl:number count="sect1"/>
         </inline>
         <inline>
         <xsl:value-of select="./title"/>
         </inline>
      </block>  
      <xsl:apply-templates/>
</xsl:template>

<xsl:template match="sect2">
      <block font-size="12pt" font-family="Times" font-weight="bold" 
                  padding-top="24pt" padding-bottom="12pt">
          <inline>        
           <xsl:number count="chapter" format="1."/>
           <xsl:number count="sect1" format="1."/>
           <xsl:number count="sect2" format="1"/>
         </inline>
         <inline>
           <xsl:value-of select="./title"/>
         </inline>
      </block>  
      <xsl:apply-templates/>
</xsl:template>

<xsl:template match="itemizedlist">
      <block font-size="12pt" font-family="Times" margin-top="5mm" margin-left="10mm">
         <list-block>         
         <xsl:for-each select="./listitem">
          <list-item>
            <list-item-label end-indent="label-end()"><block>
              <xsl:text disable-output-escaping="yes">&#45;</xsl:text>
            </block></list-item-label>
            <list-item-body start-indent="body-start()"><block><xsl:value-of select="./para"/></block></list-item-body>
          </list-item>
        </xsl:for-each>  
        </list-block>
      </block>  
      <xsl:apply-templates/>
</xsl:template>

<xsl:template match="para">
      <block font-size="12pt" font-family="Times" text-align="justify"
                  text-indent="15mm">
        <xsl:value-of select="."/>
      </block>  
      <xsl:apply-templates/>
</xsl:template>


<xsl:template match="*"><!-- not shown --></xsl:template>

</xsl:stylesheet>
