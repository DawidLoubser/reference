<?xml version="1.0" encoding="UTF-8"?>

<!-- A transformation style sheet to produce an XHTML periodic table
    list, grouped by element type -->
<xsl:stylesheet 
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    version="1.0">
    
    <!-- Room temperature, in Kelvin -->
    <xsl:variable name="roomTemp">300</xsl:variable>
    
    <!-- (Root) document structure container -->
    <xsl:template match="PERIODIC_TABLE">
        <html>
            <head>
                <title>Periodic Table of the Elements</title>
                <!-- CSS to visually format and layout the page -->
                <link rel="stylesheet" href="periodicTable.css"/>
            </head>
            <body>
                <h1>Periodic Table of the Elements</h1>
                
                <!-- Three types of elements -->
                <xsl:call-template name="outputGases"/>
                <xsl:call-template name="outputLiquids"/>
                <xsl:call-template name="outputSolids"/>
                
                <!-- Footer -->
                <xsl:call-template name="footer"/>
            </body>
        </html>
    </xsl:template>
    
    <!-- Outputs gaseous elements (i.e. boiling at room temperature or less) -->
    <xsl:template name="outputGases">
        <div class="gases">
            <h2>Gaseous elements</h2>
            <!-- Apply templates to atoms that satisfy 'gaseous' criteria. Because this is a named
            template, we do not have a 'context node' so our selection XPath is 'absolute'. -->
            <xsl:apply-templates select="/PERIODIC_TABLE/ATOM[ BOILING_POINT &lt; $roomTemp]">
                <!-- Sorth them by atomic number -->
                <xsl:sort select="ATOMIC_NUMBER" data-type="number"/>
            </xsl:apply-templates>
        </div>
    </xsl:template>
    
    <!-- Outputs liquid elements (i.e. melting below room 
        temperature, but boiling above room temperature) -->
    <xsl:template name="outputLiquids">
        <div class="liquids">
            <h2>Liquid elements</h2>
            <xsl:apply-templates select="/PERIODIC_TABLE/ATOM[ MELTING_POINT &lt; $roomTemp and BOILING_POINT &gt; $roomTemp]">
                <!-- Sorth them by atomic number -->
                <xsl:sort select="ATOMIC_NUMBER" data-type="number"/>
            </xsl:apply-templates>
        </div>
    </xsl:template>
    
    <!-- Outputs solid elements (i.e. both melting and boiling 
        points are above room temperature) -->
    <xsl:template name="outputSolids">
        <div class="solids">
            <h2>Solid elements</h2>
            <xsl:apply-templates select="/PERIODIC_TABLE/ATOM[ MELTING_POINT &gt; $roomTemp and BOILING_POINT &gt; $roomTemp]">
                <!-- Sorth them by atomic number -->
                <xsl:sort select="ATOMIC_NUMBER" data-type="number"/>
            </xsl:apply-templates>
        </div>
    </xsl:template>

    <!-- Template to render an atom (atomic element) -->
    <xsl:template match="ATOM">
        <div class="atom">
            <h3>
                <em><xsl:value-of select="ATOMIC_NUMBER"/>. </em> 
                <xsl:value-of select="NAME"/>
            </h3>
            <table>
                <!-- Apply templates to everything else except name and number (which are already taken care of).
                    All templates should then assume they generate rows for this table we are in.-->
                <xsl:apply-templates select="*[name() != 'NAME' and name() != 'ATOMIC_NUMBER']"/>
            </table>
        </div>
    </xsl:template>
    
    <!-- Atomic weight -->
    <xsl:template match="ATOMIC_WEIGHT">
        <tr>
            <td class="label">Atomic weight</td>
            <td><xsl:value-of select="."/></td>
        </tr>
    </xsl:template>
    
    <!-- Boiling and Melting points -->
    <xsl:template match="BOILING_POINT">
        <tr>
            <td class="label">Boiling point</td>
            <td><xsl:value-of select="."/> K</td>
        </tr>        
    </xsl:template>
    <xsl:template match="MELTING_POINT">
        <tr>
            <td class="label">Melting point</td>
            <td><xsl:value-of select="."/> K</td>
        </tr>        
    </xsl:template>
    
    <!-- Electron configuration -->
    <xsl:template match="ELECTRON_CONFIGURATION">
        <tr>
            <td class="label">Electron configuration</td>
            <td class="electronConfiguration"><xsl:value-of select="."/></td>
        </tr>
    </xsl:template>
    
    <!-- Writes a default footer. -->
    <xsl:template name="footer">
        <div class="footer">
            Automatically generated using XSLT. (C) 2006 Solms TCD. Materials classified
            according to an assumed room temperature of <xsl:value-of select="$roomTemp"/> K.
        </div>
    </xsl:template>
    
    <!-- Disable default child processing -->
    <xsl:template match="*|@*"/>
    
</xsl:stylesheet>