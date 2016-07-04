#!/bin/bash

xsltproc --stringparam paper.type A4 /usr/share/sgml/docbook/xsl-stylesheets-1.62.0/fo/docbook.xsl $1.xml > $1.fo  
fop.sh $1.fo $1.pdf
