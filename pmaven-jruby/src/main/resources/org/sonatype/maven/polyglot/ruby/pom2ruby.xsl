<?xml version="1.0"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

  <xsl:output encoding="utf-8" method="text"/>

  <xsl:template match="*" priority="-1">
    <xsl:param name="indent"></xsl:param>
    <xsl:call-template name="node">
      <xsl:with-param name="indent" select="$indent"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template match="*[name() = 'module' or name() = 'system']">
    <xsl:param name="indent"></xsl:param>
    <xsl:call-template name="node">
      <xsl:with-param name="name">
	<xsl:text>_</xsl:text>
	<xsl:value-of select="name()"/>
      </xsl:with-param>
      <xsl:with-param name="indent" select="$indent"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template name="node">
    <xsl:param name="name"><xsl:value-of select="name()"/></xsl:param>
    <xsl:param name="indent"></xsl:param>

    <xsl:value-of select="$indent"/>
    <xsl:choose>
      <xsl:when test="contains($name, '.')">
	<xsl:text>'</xsl:text>
	<xsl:value-of select="$name"/>
	<xsl:text>'</xsl:text>
      </xsl:when>
      <xsl:otherwise>
	<xsl:value-of select="$name"/>
      </xsl:otherwise>
    </xsl:choose>

    <xsl:choose>
      <xsl:when test="*">
    <xsl:text> {
</xsl:text>

    <xsl:apply-templates select="*">
      <xsl:with-param name="indent">
	<xsl:value-of select="$indent"/>
	<xsl:text>  </xsl:text>
      </xsl:with-param>
    </xsl:apply-templates>

    <xsl:value-of select="$indent"/>
    <xsl:text>}
</xsl:text>
      </xsl:when>
      <xsl:otherwise>
	<!-- TODO quote quote-character inside text -->
	<xsl:text> '</xsl:text>
	<xsl:value-of select="text()"/>
	<xsl:text>'
</xsl:text>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>
    

</xsl:transform>

