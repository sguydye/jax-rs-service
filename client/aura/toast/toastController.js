({
	initialize : function(component, event, helper) {        
        switch(component.get("v.type")) {
            case("success"):
                helper.initSuccess(component);                
                break;
            case("warning"):
                helper.initWarning(component);
                break;
            case("error"):
                helper.initError(component);
                break;
            default:
                break;
        }
	}
})