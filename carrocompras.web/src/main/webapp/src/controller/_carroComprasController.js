define(['model/carroComprasModel'], function(carroComprasModel) {
    App.Controller._CarroComprasController = Backbone.View.extend({
        initialize: function(options) {
            this.modelClass = options.modelClass;
            this.listModelClass = options.listModelClass;
            this.showEdit = true;
            this.showDelete = true;
            this.editTemplate = _.template($('#carroCompras').html());
            this.listTemplate = _.template($('#carroComprasList').html());
            if (!options || !options.componentId) {
                this.componentId = _.random(0, 100) + "";
            }else{
				this.componentId = options.componentId;
		    }
            var self = this;
            Backbone.on(this.componentId + '-' + 'carroCompras-create', function(params) {
                self.create(params);
            });
            Backbone.on(this.componentId + '-' + 'carroCompras-list', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carroCompras-edit', function(params) {
                self.edit(params);
            });
            Backbone.on(this.componentId + '-' + 'carroCompras-delete', function(params) {
                self.destroy(params);
            });
            Backbone.on(this.componentId + '-' + 'post-carroCompras-delete', function(params) {
                self.list(params);
            });
            Backbone.on(this.componentId + '-' + 'carroCompras-save', function(params) {
                self.save(params);
            });
            if(self.postInit){
            	self.postInit();
            }
        },
        create: function() {
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carroCompras-create')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-create', {view: this});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-create', {view: this});
                this.currentCarroComprasModel = new this.modelClass();
                this._renderEdit();
                Backbone.trigger(this.componentId + '-' + 'post-carroCompras-create', {view: this});
            }
        },
        list: function(params) {
            if (params) {
                var data = params.data;
            }
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carroCompras-list')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-list', {view: this, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-list', {view: this, data: data});
                var self = this;
				if(!this.carroComprasModelList){
                 this.carroComprasModelList = new this.listModelClass();
				}
                this.carroComprasModelList.fetch({
                    data: data,
                    success: function() {
                        self._renderList();
                        Backbone.trigger(self.componentId + '-' + 'post-carroCompras-list', {view: self});
                    },
                    error: function(mode, error) {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-list', view: self, error: error});
                    }
                });
            }
        },
        edit: function(params) {
            var id = params.id;
            var data = params.data;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carroCompras-edit')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-edit', {view: this, id: id, data: data});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-edit', {view: this, id: id, data: data});
                if (this.carroComprasModelList) {
                    this.currentCarroComprasModel = this.carroComprasModelList.get(id);
                    this._renderEdit();
                    Backbone.trigger(this.componentId + '-' + 'post-carroCompras-edit', {view: this, id: id, data: data});
                } else {
                    var self = this;
                    this.currentCarroComprasModel = new this.modelClass({id: id});
                    this.currentCarroComprasModel.fetch({
                        data: data,
                        success: function() {
                            self._renderEdit();
                            Backbone.trigger(self.componentId + '-' + 'post-carroCompras-edit', {view: this, id: id, data: data});
                        },
                        error: function() {
                            Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-edit', view: self, id: id, data: data, error: error});
                        }
                    });
                }
            }
        },
        destroy: function(params) {
            var id = params.id;
            var self = this;
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carroCompras-delete')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-delete', {view: this, id: id});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-delete', {view: this, id: id});
                var deleteModel;
                if (this.carroComprasModelList) {
                    deleteModel = this.carroComprasModelList.get(id);
                } else {
                    deleteModel = new this.modelClass({id: id});
                }
                deleteModel.destroy({
                    success: function() {
                        Backbone.trigger(self.componentId + '-' + 'post-carroCompras-delete', {view: self, model: deleteModel});
                    },
                    error: function() {
                        Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-delete', view: self, error: error});
                    }
                });
            }
        },
		_loadRequiredComponentsData: function(callBack) {
            var self = this;
            var listReady = _.after(1, function(){
                callBack();
            }); 
            var listDataReady = function(componentName, model){
                self[componentName] = model;
                listReady();
            };
				App.Utils.getComponentList('clienteComponent',listDataReady);
        },
        save: function() {
            var self = this;
            var model = $('#' + this.componentId + '-carroComprasForm').serializeObject();
            if (App.Utils.eventExists(this.componentId + '-' +'instead-carroCompras-save')) {
                Backbone.trigger(this.componentId + '-' + 'instead-carroCompras-save', {view: this, model : model});
            } else {
                Backbone.trigger(this.componentId + '-' + 'pre-carroCompras-save', {view: this, model : model});
                this.currentCarroComprasModel.set(model);
                this.currentCarroComprasModel.save({},
                        {
                            success: function(model) {
                                Backbone.trigger(self.componentId + '-' + 'post-carroCompras-save', {model: self.currentCarroComprasModel});
                            },
                            error: function(error) {
                                Backbone.trigger(self.componentId + '-' + 'error', {event: 'carroCompras-save', view: self, error: error});
                            }
                        });
            }
        },
        _renderList: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.listTemplate({carroComprass: self.carroComprasModelList.models, componentId: self.componentId, showEdit : self.showEdit , showDelete : self.showDelete}));
                self.$el.slideDown("fast");
            });
        },
        _renderEdit: function() {
            var self = this;
            this.$el.slideUp("fast", function() {
                self.$el.html(self.editTemplate({carroCompras: self.currentCarroComprasModel, componentId: self.componentId , showEdit : self.showEdit , showDelete : self.showDelete
 
				    ,cliente: self.clienteComponent
 
				}));
                self.$el.slideDown("fast");
            });
        }
    });
    return App.Controller._CarroComprasController;
});