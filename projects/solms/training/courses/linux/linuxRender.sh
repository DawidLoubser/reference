#!/bin/bash
xsltproc --stringparam paper.type A4 /opt/share/xml/Oxygen/docbook/xsl/fo/docbook.xsl Linux.xml > ~/work/publish/courseMaterial/Linux/Linux.fo  
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/Linux/Linux.fo ~/work/publish/courseMaterial/Linux/Linux.pdf
