<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/">
        <html>
            <head>
                <title>Greenhouse Plants</title>
            </head>
            <body>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>Name</th>
                        <th>Soil</th>
                        <th>Origin</th>
                        <th>Stem Color</th>
                        <th>Leafs Color</th>
                        <th>Size (meter)</th>
                        <th>Temperature (°C)</th>
                        <th>Lighting</th>
                        <th>Watering (ml/week)</th>
                    </tr>
                    <xsl:for-each select="greenhouse/plant">
                        <tr>
                            <td><xsl:value-of select="name"/></td>
                            <td><xsl:value-of select="soil"/></td>
                            <td><xsl:value-of select="origin"/></td>
                            <td><xsl:value-of select="visual_parameters/stem_color"/></td>
                            <td><xsl:value-of select="visual_parameters/leafs_color"/></td>
                            <td><xsl:value-of select="visual_parameters/medium_size"/></td>
                            <td><xsl:value-of select="growing_tips/temperature"/></td>
                            <td><xsl:value-of select="growing_tips/lighting"/></td>
                            <td><xsl:value-of select="growing_tips/watering"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>
