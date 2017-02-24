@isTest
private class HelloWorldTestClass {
    static testmethod void validateHelloWorld(){
        Book__c b = new Book__c(Name='Little prince', Price__c = 200);
        System.debug('Book price before insertion: ' + b.Price__c);
        insert b;
        
        b = [Select Name, Price__c from Book__c where Id = :b.id];
        System.debug('Book price after the insertion' + b.Price__c);
        
        System.assertEquals(180, b.Price__c);    
        
    } 
}