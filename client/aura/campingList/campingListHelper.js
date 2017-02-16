({
	createItem : function(component, item) {
		var action = component.get("c.saveItem");
       	console.log(item);
    	action.setParams({
        "item": item
    	});
    	action.setCallback(this, function(response){
        	var state = response.getState();
        	if (component.isValid() && state === "SUCCESS") {
           /* 	var items = component.get("v.items");
            	items.push(response.getReturnValue());
            	component.set("v.items", items); */
        	}
    });
    $A.enqueueAction(action);	
	},
    validateCampingItemForm : function(component) {
        var formIsValid = true;
        var nameField = component.find("itemname");
        var itemName = nameField.get("v.value");
        
        var quantityField = component.find("itemquantity");
        var quantity = quantityField.get("v.value");
        
        var priceField = component.find("itemprice");
        var price = priceField.get("v.value");        
               
        if ($A.util.isEmpty(itemName)) {
            formIsValid = false;
            nameField.set("v.errors", [{message : "Item name can't be blank"}]);
        } else {
            nameField.set("v.errors", null);
            
        }
        
        if($A.util.isEmpty(quantity) || isNaN(quantity) || (quantity <= 0.0)) {
            formIsValid = false;
            quantityField.set("v.errors", [{message : "Item quantity can't be 0 or less"}]);
        } else {
            quantityField.set("v.errors", null);
        }
        
        if($A.util.isEmpty(price) || isNaN(price) || (price <= 0.0)) {
            formIsValid = false;
            priceField.set("v.errors", [{message : "Iten price can't be 0 or less"}]);
        } else {
            priceField.set("v.errors", null);
        }
        return formIsValid;
    }
})