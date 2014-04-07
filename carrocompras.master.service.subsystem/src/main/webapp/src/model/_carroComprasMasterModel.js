define([], function() {
    App.Model._CarroComprasMasterModel = Backbone.Model.extend({
     
    });

    App.Model._CarroComprasMasterList = Backbone.Collection.extend({
        model: App.Model._CarroComprasMasterModel,
        initialize: function() {
        }

    });
    return App.Model._CarroComprasMasterModel;
    
});