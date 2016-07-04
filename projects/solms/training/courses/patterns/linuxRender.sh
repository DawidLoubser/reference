#!/bin/bash
xsltproc --stringparam paper.type A4 /opt/share/xml/Oxygen/docbook/xsl/fo/docbook.xsl Patterns.xml > ~/work/publish/courseMaterial/Patterns/patterns.fo  
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/Patterns/patterns.fo ~/work/publish/courseMaterial/Patterns/patterns.pdf
