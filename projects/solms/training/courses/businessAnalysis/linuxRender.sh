#!/bin/bash
xsltproc --stringparam paper.type A4 /opt/share/xml/oxygen/docbook/xsl/fo/docbook.xsl businessAnalysis.xml > ~/work/publish/courseMaterial/businessAnalysis/businessAnalysis.fo  
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/businessAnalysis/businessAnalysis.fo ~/work/publish/courseMaterial/businessAnalysis/businessAnalysis.pdf
