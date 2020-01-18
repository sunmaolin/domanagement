/**
 * 首页面设计
 */
Ext.define('Index.Main',{
    extend:'Ext.panel.Panel',
    requires:['Index.Function'],
    xtype:'main',
    id:'main',
    layout:'border',
    initComponent:function () {
        var main_me=this;
        main_me.callParent();
        main_me.loadData();

    },
    loadData:function () {
        var me=this;

        //动态加载宿舍机构数store
        var treeStore = Ext.create('Ext.data.TreeStore', {
            root:{
                text:'齐鲁工业大学宿舍',
                expanded: false
            },
            fields: ['text','did','fid'],
            proxy: {
                type: 'ajax',
                url: '/dormitory/find_all',
                extraParams  : {
                    flag : 0,
                    fid:0
                },
                actionMethods:{
                    create:'POST'
                },
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            listeners:{
                beforeexpand:function (node) {
                    if(!node.data.root){
                        this.proxy.extraParams.flag=1;
                        this.proxy.extraParams.fid=node.raw.fid;
                    }
                }
            },
            autoLoad: true
        });

        //加载用户名，拼接title
        Ext.Ajax.request({
            url: '/users/session_data',
            params: {
                flag:'title'
            },
            method:'POST',
            success: function(response){
                var username = response.responseText;
                me.loadComp('宿舍管理  ->管理员：'+username,treeStore);
            }
        });


    },
    loadComp:function(title,treeStore){
        var me=this;
        var north={
            title:title,
            region:'north',
            height: 100,
            bodyStyle: 'background-image: url(/images/th.jpg)',
            html: '<div style="font-size: 25px;text-align: center;margin-top: 5px"><b>齐鲁工业大学宿舍管理系统</b><br>' +
                '<span style="font-size: 15px">联系电话：010101</span></div>'
        };
        var west={
            title: '宿舍浏览',
            region: 'west',
            width: 180,
            split: true,
            collapsible: true,
            layout:'fit',
            items:{
                xtype:'treepanel',
                store:treeStore,
                rootVisible: false
            }
        };
        var east={
            title:'功能菜单',
            region: 'east',
            width: 180,
            collapsible: true,
            collapsed:true,
            split:true,
            items:{
                xtype:'fun'
            }
        };
        var center={
            region: 'center',
            id:'center',
            xtype:'tabpanel',
            layout: 'fit'
        };
        me.add([north,west,east,center]);
    }

});