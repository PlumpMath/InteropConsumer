define([], function() {
    App.Model._ItemModel = Backbone.Model.extend({
        defaults: {
 
		 'cantidad' : ''
 ,  
		 'name' : ''
 ,  
		 'productsId' : ''
        },
        initialize: function() {
        },
        getDisplay: function(name) {
			 if(name=='productsId'){  
                 var value = App.Utils.getModelFromCache('productComponent',this.get('productsId'));
                 if(value) 
                 return value.get('name');
             }
         return this.get(name);
        }
    });

    App.Model._ItemList = Backbone.Collection.extend({
        model: App.Model._ItemModel,
        initialize: function() {
        }

    });
    return App.Model._ItemModel;
});