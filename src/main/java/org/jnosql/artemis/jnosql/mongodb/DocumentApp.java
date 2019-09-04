/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jnosql.artemis.jnosql.mongodb;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jnosql.diana.api.Settings;
import org.jnosql.diana.api.document.Document;
import org.jnosql.diana.api.document.DocumentCollectionManager;
import org.jnosql.diana.api.document.DocumentCollectionManagerFactory;
import org.jnosql.diana.api.document.DocumentConfiguration;
import org.jnosql.diana.api.document.DocumentDeleteQuery;
import org.jnosql.diana.api.document.DocumentEntity;
import org.jnosql.diana.api.document.DocumentQuery;
import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.delete;
import static org.jnosql.diana.api.document.query.DocumentQueryBuilder.select;
import org.jnosql.diana.mongodb.document.MongoDBDocumentConfiguration;
/**
 *
 * @author WayneHu
 */
public class DocumentApp {
    public static void main(String args[]) {
        Map<String, Object> map = new HashMap<>();
        map.put("mongodb-server-host-1","localhost:27017");
        DocumentConfiguration configuration = new MongoDBDocumentConfiguration();   
        //DocumentCollectionManagerFactory managerFactory = configuration.get();
        DocumentCollectionManagerFactory managerFactory = configuration.get(Settings.of(map));
        DocumentCollectionManager manager = managerFactory.get("my-db");
        DocumentEntity documentEntity = DocumentEntity.of("books");
        documentEntity.add(Document.of("_id", "100"));
        documentEntity.add(Document.of("name", "JNoSQL in Action"));
        documentEntity.add(Document.of("pages", "620"));
        DocumentEntity saved = manager.insert(documentEntity);
        DocumentQuery query = select().from("books").where("_id").eq(100).build();
        List<DocumentEntity> entities = manager.select(query);
        saved.add(Document.of("author", "baeldung"));
        DocumentEntity updated = manager.update(saved);
        //DocumentDeleteQuery deleteQuery = delete().from("books").where("_id").eq("100").build();
        //manager.delete(deleteQuery);
    }
}
