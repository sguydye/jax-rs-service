({
	createItem : function(component) {
		var item = component.get("v.newItem");
        var createEvent = component.getEvent("addItem");
        createEvent.setParams({"item" : item});
        createEvent.fire();
        item = { 'sobjectType' : 'Camping_Item__c',
                     'Name' : '',
                     'Quantity__c' : 0,
                     'Price__c' : 0,
                     'Packed__c' : false } ;
        component.set("v.newItem", item);
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