#!/bin/bash

xsltproc --stringparam paper.type A4 /usr/share/sgml/docbook/xsl-stylesheets-1.62.0/fo/docbook.xsl Patterns.xml > Patterns.fo  
#xsltproc /usr/share/sgml/docbook/xsl-stylesheets-1.62.0/html/docbook.xsl > Servlets.html  
fop.sh Patterns.fo Patterns.pdf
