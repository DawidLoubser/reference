#!/bin/bash
xsltproc --stringparam paper.type A4 /opt/share/xml/Oxygen/docbook/xsl/fo/docbook.xsl exam1.xml > ~/work/publish/courseMaterial/Patterns/exam1.fo  
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/Patterns/exam1.fo ~/work/publish/courseMaterial/Patterns/exam1.pdf
