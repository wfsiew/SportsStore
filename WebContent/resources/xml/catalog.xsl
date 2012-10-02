<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="catalog">
		<h2>My CD Collection</h2>
		<table border="1">
			<thead>
				<tr bgcolor="#9acd32">
					<th>Title</th>
					<th>Artist</th>
					<th>Country</th>
					<th>Company</th>
					<th>Price</th>
				</tr>
			</thead>
			<tbody>
			<xsl:for-each select="cd">
				<tr>
					<td>
						<xsl:value-of select="title" />
					</td>
					<td>
						<xsl:value-of select="artist" />
					</td>
					<td>
						<xsl:value-of select="country" />
					</td>
					<td>
						<xsl:value-of select="company" />
					</td>
					<td>
						<xsl:value-of select="price" />
					</td>
				</tr>
			</xsl:for-each>
			</tbody>
		</table>
	</xsl:template>
</xsl:stylesheet>