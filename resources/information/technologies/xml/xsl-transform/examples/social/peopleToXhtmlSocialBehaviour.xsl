<?xml version="1.0" encoding="UTF-8"?>
<!-- Produces an XHTML page inferring social behaviour from a set of people/friends -->
<xsl:stylesheet 
    xmlns="http://www.w3.org/1999/xhtml"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:friends="urn:social:friends"
    version="1.0">
    
    <!-- Matches on the root of the input document, to produce the
    framework for the output document -->
    <xsl:template match="friends:people">
        <html>
            <head>
                <title>A List of Friends</title>
                <!--link rel="stylesheet" href="style.css"/-->
            </head>
            <body>
                <h1>List of people (with social information)</h1>
                
                <xsl:apply-templates>
                    <!-- Sort alphabetically using name -->
                    <xsl:sort select="friends:name" 
                        data-type="text"/>
                </xsl:apply-templates>
                
            </body>
        </html>
    </xsl:template>
    
    
    <!-- A template to match a person, producing an XHTML
    <div> for each -->
    <xsl:template match="friends:person">
        <div>
            <xsl:apply-templates/>
            
            <!-- A summary to describe person's social behaviour -->
            <xsl:call-template name="socialSummary">
                <xsl:with-param name="person" select="."/>
            </xsl:call-template>
        </div>
    </xsl:template>
    
    
    <!-- Writes the person name -->
    <xsl:template match="friends:name">
        <h2><xsl:number count="//friends:person"/>. <xsl:value-of select="."/></h2>
    </xsl:template>
    
    
    <!-- Writes the ID number -->
    <xsl:template match="friends:person/friends:id">
        <p>SA ID number: <em><xsl:value-of select="."/></em></p>
    </xsl:template>
    
    
    <!-- Writes the bit about friends -->
    <xsl:template match="friends:person/friends:friends">
        <p>
            Says 
            <xsl:call-template name="genderNoun">
                <xsl:with-param name="person" select="parent::friends:person"/>
            </xsl:call-template>'s friends with: 
            <xsl:for-each select="friends:id">
                <em>
                    <!-- Get person name by ID -->
                    <xsl:call-template name="personNameByID">
                        <xsl:with-param name="id" select="."/>
                    </xsl:call-template>
                </em>
                <!-- Write comma after each except last -->
                <xsl:choose>
                    <xsl:when test="position() &lt; (last() - 1)">
                        <xsl:text xml:space="preserve">, </xsl:text>
                    </xsl:when>
                    <xsl:when test="position() = (last() - 1)">
                        <xsl:text xml:space="preserve"> and </xsl:text>
                    </xsl:when>
                    <xsl:otherwise>.</xsl:otherwise>
                </xsl:choose>
            </xsl:for-each>
        </p>
    </xsl:template>
    
    
    <!-- A template to infer a person's social behaviour -->
    <xsl:template name="socialSummary">
        <xsl:param name="person"/>
        
        <!-- How many times is this person listed as a friend? -->
        <xsl:variable name="referenceCount">
            <xsl:value-of select="count( //friends:person/friends:friends/friends:id[text() = $person/friends:id] )"/>
        </xsl:variable>
        
        <!-- Determine popularity -->
        <xsl:variable name="popularity">
            <xsl:choose>
                <xsl:when test="$referenceCount = 0">
                    hated
                </xsl:when>
                <xsl:when test="$referenceCount &lt; 2">
                    unpopular
                </xsl:when>
                <xsl:when test="$referenceCount >= 2">
                    popular
                </xsl:when>
            </xsl:choose>
        </xsl:variable>
        
        <!-- How many friends does this person have? -->
        <xsl:variable name="friendCount">
            <xsl:value-of select="count( friends:friends/friends:id )"/>
        </xsl:variable>
        
        <!-- Determine sociability -->
        <xsl:variable name="sociability">
            <xsl:choose>
                <xsl:when test="$friendCount = 0">
                    reclusive
                </xsl:when>
                <xsl:when test="$friendCount &lt; 2">
                    picky
                </xsl:when>
                <xsl:when test="$friendCount >= 2">
                    sociable
                </xsl:when>
            </xsl:choose>
        </xsl:variable>
        
        <p>
            Social behaviour: 
            <em><xsl:value-of select="$popularity"/></em>
            <xsl:text xml:space="preserve">, </xsl:text>
            <em><xsl:value-of select="$sociability"/></em>
        </p>
        
    </xsl:template>
    
    
    <!-- A template to get the gender noun (he/she) for a person -->
    <xsl:template name="genderNoun">
        <xsl:param name="person"/>
        <xsl:choose>
            <xsl:when test="$person/@gender = 'Male'">he</xsl:when>
            <xsl:when test="$person/@gender = 'Female'">she</xsl:when>
            <xsl:otherwise>it</xsl:otherwise>
        </xsl:choose>
    </xsl:template>
    
    
    <!-- A template to write the name of a person, by ID number -->
    <xsl:template name="personNameByID">
        <xsl:param name="id"/>
        <xsl:value-of select="//friends:person[ friends:id = $id ]/friends:name"/>
    </xsl:template>
    
</xsl:stylesheet>