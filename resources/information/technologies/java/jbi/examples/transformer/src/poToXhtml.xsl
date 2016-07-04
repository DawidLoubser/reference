<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:po="http://example.co.za/purchaseOrder/"
    xmlns="http://www.w3.org/1999/xhtml"
    version="2.0">
    
    <xsl:output method="xhtml"/>
    
    <xsl:template match="po:purchaseOrder">
        <html>
            <head>
                <title>Your Purchase Order</title>
            </head>
            <body>
                <h1>Purchase Order</h1>
                <h2>
                    (for 
                    <em><xsl:value-of select="po:customer/po:name"/></em>)
                </h2>
                <p>
                    Thank you for purchasing items with us. The total of 
                    your purchases amounts to 
                    <em class="total">
                        <xsl:value-of select="@total"/>
                    </em>, we do hope you pay on time. Thank you for 
                    shopping with us.
                </p>
                <table>
                    <thead>
                        <tr>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <xsl:apply-templates select="po:item">
                        <xsl:sort select="@name"/>
                    </xsl:apply-templates>
                </table>
            </body>
        </html>
    </xsl:template>
    
    <xsl:template match="po:item">
        <tr>
            <td><xsl:value-of select="@name"/></td>
            <td><xsl:value-of select="@price"/></td>
        </tr>
    </xsl:template>
    
    <xsl:template name="footer">
        <div class="footer">
            Copyleft (C) 1798-1998 Dome Depot. No rights reserved.
        </div>
    </xsl:template>

</xsl:stylesheet>
