define(['component/_CRUDComponent', 'controller/toolbarController', 'model/toolbarModel', 'model/carroComprasModel', 'controller/carroComprasController'], function() {
    App.Component.CarroComprasComponent = App.Component._CRUDComponent.extend({
        name: 'carroCompras',
        model: App.Model.CarroComprasModel,
        listModel: App.Model.CarroComprasList,
        controller : App.Controller.CarroComprasController
    });
    return App.Component.CarroComprasComponent;
});