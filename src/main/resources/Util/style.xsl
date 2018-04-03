<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" omit-xml-declaration="yes" indent="no"/>
<xsl:template match="/">
Name,Phone,Email,Address,Block Number
<xsl:for-each select="//persons">
<xsl:value-of select="concat(name,',',phone,',',email,',',address,'&#xA;')"/>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>