<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
<xsl:output method="html" encoding="UTF-8" />
        <xsl:template match="/">
              <html >
                        <head>
                            <link rel="stylesheet" type="css" href="cv.css" />
                                  <h1 >
                                    <xsl:value-of select="/curriculum/datos_personales/nombre"/>
                                    <tr></tr>
                                     <xsl:value-of select="/curriculum/datos_personales/apellidos"/>
                                  </h1>
                        </head>
                        <body>
                        <div>
                        </div>
                        
                        
                        
                        
                        
                        </body>
                    
              </html>
        </xsl:template>
</xsl:stylesheet>
