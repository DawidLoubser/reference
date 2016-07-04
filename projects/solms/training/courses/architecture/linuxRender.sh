#!/bin/bash
xsltproc --stringparam paper.type letter /opt/share/xml/Oxygen/docbook/xsl/fo/docbook.xsl Architecture.xml > ~/work/publish/courseMaterial/Architecture/Architecture.fo
/opt/share/xml/fop-0.20.4/fop.sh ~/work/publish/courseMaterial/Architecture/Architecture.fo ~/work/publish/courseMaterial/Architecture/Architecture.pdf
