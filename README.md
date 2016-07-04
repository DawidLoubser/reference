# Reference material

Collected research, writings, and reference material. Largely ported from
my days as director at Solms TCD, with a best-effort attempt to remove all
private and/or sensitive information.

Because of this, there will be many **broken links** to accompany the many **un-renderable**
documents due to being in a legacy Docbook format, both of which require incremental fixing
over time.

Solms TCD believed enough in the open-source method, to also open-source
all internal writings, research, and course material.

This repository is an extension of that belief.

> Note: Specialised tools, not yet commited, may be required to render the material
> in this repository. One such option is using `pandoc` together with a little
> XSLT to 'fix up' the docbook XML structure at various levels of granularity.
> For example:

````
xmllint --xinclude someDoc.docbook | saxon -s:- docbook-pandoc-fixup.xsl | pandoc -s -f docbook -V geometry:margin=3.5cm --toc --number-sections --chapters --toc-depth=2 -o someDoc.pdf
````