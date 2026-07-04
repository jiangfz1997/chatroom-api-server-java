package com.chatroom.repository;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.*;

@Component
public class DynamoDbInitializer {
    private final DynamoDbClient dynamoDbClient;

    public DynamoDbInitializer(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    @PostConstruct
    public void createTables(){
        createUserTable();
    }

    private void createUserTable() {
        try{
            dynamoDbClient.createTable(CreateTableRequest.builder()
                    .tableName("Users")
                    .attributeDefinitions(
                            AttributeDefinition.builder()
                                    .attributeName("username")
                                    .attributeType(ScalarAttributeType.S)
                                    .build()
                    )
                    .keySchema(
                            KeySchemaElement.builder()
                                    .attributeName("username")
                                    .keyType(KeyType.HASH)
                                    .build()
                    )
                    .billingMode(BillingMode.PAY_PER_REQUEST)
                    .build());
        } catch (ResourceInUseException e) {
            // Table already exists, ignore
        }
    }
}
