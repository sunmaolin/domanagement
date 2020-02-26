/**
 * 首页面设计
 */
Ext.define('Index.Main',{
    extend:'Ext.panel.Panel',
    requires:['Index.Function','Index.CenterPanel'],
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
        /**
         * 说明：节点root不显示是默认展开，展开加载数据时，如果
         * 不是root节点展开就将flag设置为1，进行子节点的查询
         * @type {Ext.data.TreeStore}
         */
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
        var center={
            region: 'center',
            id:'center',
            xtype:'tabpanel',
            layout: 'fit',
            scroll:true,
            activeTab:0,
            bodyStyle:'background-image:url(/images/qlu.jpg);background-repeat:no-repeat;background-size:100% 100%'
        };
        var west={
            title: '宿舍浏览',
            region: 'west',
            width: 180,
            split: true,
            // collapsible: true,//设置为可折叠
            layout:'fit',
            items:{
                xtype:'treepanel',
                id:'treep',
                store:treeStore,
                rootVisible: false,
                listeners:{
                    itemclick:function (view,record) {
                        if(!record.data.parentId){
                            //TODO 拼接宿舍名加楼号
                            var centerPanel=Ext.widget('centerPanel',{title:record.data.text,did:record.data.did});
                            Ext.getCmp('center').add(centerPanel);
                        }
                    }
                }
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

        me.add([north,west,east,center]);
    }

});