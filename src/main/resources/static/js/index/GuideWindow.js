/**
 * 导员面板窗口
 */
Ext.define('Index.GuideWindow', {
    extend: 'Ext.window.Window',
    requires:['Index.AddGuide'],
    xtype: 'guideWindow',
    layout: 'vbox',
    title:'导员信息',
    width: 530,
    height: 420,
    bodyStyle: {
        background: 'white'
    },
    initComponent: function () {
        var me = this;
        me.callParent();
        me.loadData();
        me.loadComponent();
    },
    loadData: function () {
        var me =this;
        me.guideStore = Ext.create('Ext.data.Store', {
            fields:['gid','gname','gphone','gsex','sprofessional','sclass'],
            proxy: {
                url:'/guide/findAllGuide',
                type: 'ajax',
                reader: {
                    type: 'json',
                    root: 'data'
                }
            },
            autoLoad:true
        });
    },
    loadComponent:function () {
        var me = this;
        //搜索框
        var search = Ext.widget('textfield',{
            width:250,
            margin:'10 20 10 10',
            fieldLabel:'查询条件',
            emptyText:'输入姓名或者专业',
            labelWidth:60
        });
        //查询框
        var searchButton = Ext.widget('button',{
            text:'搜索',
            margin:'10 0 0 0',
            handler:function () {
                var searchCondition = search.getValue();
                if(searchCondition == ""){
                  Ext.Msg.alert('提示信息','请输入查询条件');
                  return;
                };
                Ext.Ajax.request({
                    url: '/guide/findGuide',
                    params: {
                        searchCondition: searchCondition
                    },
                    success: function(response){
                        var responseText = Ext.decode(response.responseText);
                        //重新加载数据
                        if(responseText.data.length==0){
                          Ext.Msg.alert('提示信息','无数据！');
                          return;
                        };
                        me.guideStore.loadData(responseText.data);
                    }
                });
            }
        });
        //增加
        var addGuideButton = Ext.widget('button',{
           text:'增加导员',
           width:60,
           height:30,
           margin:'10 20 0 180',
           handler:function () {
                Ext.getCmp('fun').isControl(Ext.widget('addGuide'));
           }
        });
        //删除
        var deleteGuideButton = Ext.widget('button',{
            text:'删除导员',
            margin:'10 0 0 0',
            width:60,
            height:30,
            handler:function () {
                var selectGuide = gridPanel.getSelectionModel().getSelection();
                if(selectGuide.length == 0){
                    Ext.Msg.alert('提示信息','请选中要删除的导员！');
                    return;
                }
                var gid = selectGuide[0].getData(false).gid;
                Ext.Ajax.request({
                    url: '/guide/deleteGuide',
                    params: {
                        gid: gid
                    },
                    success: function(){
                        me.guideStore.load();
                        Ext.Msg.alert('提示信息','删除成功!');
                    }
                });
            }
        });
        var gridPanel = Ext.create('Ext.grid.Panel',{
            id:'guideGrid',
            width:520,
            height:300,
            store:me.guideStore,
            columns:[
                {header:'姓名',dataIndex:'gname'},
                {header:'电话',dataIndex:'gphone'},
                {header:'性别',dataIndex:'gsex'},
                {header:'专业',dataIndex:'sprofessional'},
                {header:'班级',dataIndex:'sclass'}
            ]
        });

        me.add([{
            layout:'hbox',
            border:0,
            items:[search,searchButton]
        },gridPanel,{
            layout:'hbox',
            border:0,
            items:[addGuideButton,deleteGuideButton]
        }]);
    }
});