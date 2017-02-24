public class EntityController {
	private static String url = 'http://54.172.220.99:8080/jax-rs-service/services/entity';
    
    @AuraEnabled
    public static String getAll() {
       	Http h = new Http();
        HttpRequest req = new HttpRequest();
        req.setEndpoint(url);
        req.setMethod('GET');
        HttpResponse res = h.send(req);
        return res.getBody();
    }
    
    @AuraEnabled
    public static String doDelete(Integer id) {
        Http h = new Http();
        HttpRequest req = new HttpRequest();
        req.setEndpoint(url + '/' + id);
        req.setMethod('DELETE');
        HttpResponse res = h.send(req);
        return res.getBody();
    }
    
    @AuraEnabled
    public static String post(String entity) {        
        Http h = new Http();
        HttpRequest req = new HttpRequest();
        req.setEndpoint(url);
        req.setHeader('Content-type', 'application/json');
        req.setMethod('POST');	       
        req.setBody(entity);
        HttpResponse res = h.send(req);
        return res.getBody();	
    }
}