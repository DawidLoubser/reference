#!/bin/bash
xsltproc --stringparam paper.type A4 /opt/share/xml/Oxygen/docbook/xsl/fo/docbook.xsl ooad.xml > ~/work/publish/courseMaterial/OOAD/ooad.fo  
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/OOAD/ooad.fo ~/work/publish/courseMaterial/OOAD/ooad.pdf
