package com.rainmore.cms.modules.core.system

import com.rainmore.cms.domains.system.SystemProperty
import com.rainmore.cms.modules.core.json.JsonService
import javax.inject.Inject
import org.springframework.stereotype.Service

import scala.jdk.CollectionConverters._
import scala.jdk.OptionConverters._

@Service
class SystemPropertyService @Inject()
(
    repository: SystemPropertyRepository,
    jsonService: JsonService
) {

    def findOne(id: String): Option[SystemProperty] = repository.findById(id).toScala

    def save(property: SystemProperty): SystemProperty = repository.save(property)

    def save(id: String, data: java.io.Serializable): SystemProperty = {
        val property = new SystemProperty(id, jsonService.toJson(data))
        save(property)
    }

    def findBy(id: String): Set[SystemProperty] = repository.findAll(
        SystemPropertySpecification.idPrefixCondition(id)
    ).asScala.toSet

    def getData[R](property: SystemProperty, clazz: Class[R]): R = jsonService.fromJson[R](property.getData, clazz)

    def getData[R](id: String, clazz: Class[R]): R = getData[R](findOne(id).get, clazz)

    def getSystemDocumentUrl: String = findOne("centus.document.url")
        .map(property => getData[String](property, classOf[String]))
        .orNull

}
