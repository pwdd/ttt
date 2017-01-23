package com.github.pwdd.tttWebApp.serverEngine.responders

import java.io.InputStream

abstract class ResponseBuilder {
  def createResponse(board: List[Symbol], filePath: String): InputStream
}
