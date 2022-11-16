<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Palkó Patrik</h2>
                    <tr bgcolor = "#9acd32">
                        <th>ID</th>
                        <th>Vezeteknev</th>
                        <th>Keresztnev</th>
                        <th>Becenev</th>
                        <th>Kor</th>
                        <th>Ösztöndíj</th>
                    </tr>

                    <xsl:for-each select="class/student">

                        <tr>
                            <td>
                                <xsl:value-of select= "@id"/>
                            </td>
                            <td><xsl:value-of select = "vezeteknev"/></td>
                            <td><xsl:value-of select = "keresztnev"/></td>
                            <td><xsl:value-of select = "becenev"/></td>
                            <td><xsl:value-of select = "kor"/></td>
                            <td><xsl:value-of select = "osztondij"/></td>
                        </tr>
                    </xsl:for-each>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>