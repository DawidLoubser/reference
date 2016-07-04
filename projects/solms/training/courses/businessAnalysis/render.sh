#!/bin/bash

xsltproc --stringparam paper.type A4 /usr/share/sgml/docbook/xsl-stylesheets-1.62.0/fo/docbook.xsl businessAnalysis.xml > businessAnalysis.fo  
#xsltproc /usr/share/sgml/docbook/xsl-stylesheets-1.62.0/html/docbook.xsl > Servlets.html  
fop.sh businessAnalysis.fo BusinessAnalysis.pdf
